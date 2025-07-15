package com.decoracks.app.decoracks.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.decoracks.app.decoracks.models.entity.Cliente;
import com.decoracks.app.decoracks.models.entity.DetalleVenta;
import com.decoracks.app.decoracks.models.entity.Producto;
import com.decoracks.app.decoracks.models.entity.Sede;
import com.decoracks.app.decoracks.models.entity.StockProductoSede;
import com.decoracks.app.decoracks.models.entity.Usuario;
import com.decoracks.app.decoracks.models.entity.Venta;
import com.decoracks.app.decoracks.service.ClienteService;
import com.decoracks.app.decoracks.service.DetalleVentaService;
import com.decoracks.app.decoracks.service.ProductoService;
import com.decoracks.app.decoracks.service.SedeService;
import com.decoracks.app.decoracks.service.StockProductoSedeService;
import com.decoracks.app.decoracks.service.UsuarioService;
import com.decoracks.app.decoracks.service.VentaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/pedidos")
public class OrdersController {
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final VentaService ventaService;
    private final DetalleVentaService detalleVentaService;
    private final StockProductoSedeService stockService;

    public OrdersController(ClienteService clienteService,
            ProductoService productoService,
            VentaService ventaService,
            DetalleVentaService detalleVentaService,
            StockProductoSedeService stockService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.ventaService = ventaService;
        this.detalleVentaService = detalleVentaService;
        this.stockService = stockService;
    }

    @PostMapping("/save")
    public String guardarVenta(
            @RequestParam String dni_cliente,
            @RequestParam("productos[]") List<Integer> productosIds,
            @RequestParam("cantidades[]") List<Integer> cantidades) {

        // Buscar cliente
        var clienteOpt = clienteService.findByDni(dni_cliente);
        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado con DNI: " + dni_cliente);
        }
        Cliente cliente = clienteOpt.get();

        // Asumimos sede ID 2 por ahora
        Sede sede = new Sede();
        sede.setId(2);

        // Crear nueva venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDateTime.now());
        venta.setSede(sede);
        venta = ventaService.save(venta); // Guardamos y obtenemos ID

        double totalVenta = 0;

        for (int i = 0; i < productosIds.size(); i++) {
            int productoId = productosIds.get(i);
            int cantidad = cantidades.get(i);

            Producto producto = productoService.findById(productoId);
            double precio = producto.getPrecio();
            double subtotal = cantidad * precio;
            totalVenta += subtotal;

            // Guardar detalle
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precio);
            detalleVentaService.save(detalle);

            // Descontar del stock
            StockProductoSede stock = stockService.findByProductoAndSede(productoId, sede.getId());
            if (stock != null) {
                stock.setStock(stock.getStock() - cantidad);
                stockService.save(stock);
            }
        }

        // Actualiza total de la venta si es necesario
        venta.setTotal(totalVenta);
        ventaService.save(venta);

        return "redirect:/pedidos"; // O como sea tu ruta de éxito
    }

    @GetMapping("")
    public String listar(Model model) throws JsonProcessingException {
        int sedeId = 2; // Para pruebas

        List<StockProductoSede> stockProductos = stockService.findBySedeConStock(sedeId);

        // Filtrar y convertir a DTO plano para evitar bucles infinitos
        List<Map<String, Object>> productosDTO = stockProductos.stream().map(s -> {
            Map<String, Object> prod = new HashMap<>();
            prod.put("id", s.getProducto().getId());
            prod.put("nombre", s.getProducto().getNombre());
            prod.put("precio", s.getProducto().getPrecio());
            prod.put("stock", s.getStock());
            return prod;
        }).toList();

        ObjectMapper mapper = new ObjectMapper();
        String productosJson = mapper.writeValueAsString(productosDTO);

        model.addAttribute("productos", productosDTO);
        model.addAttribute("productosJson", productosJson);

        return "orders";
    }

}
