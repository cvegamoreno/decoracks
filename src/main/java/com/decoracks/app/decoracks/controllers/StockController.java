package com.decoracks.app.decoracks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.decoracks.app.decoracks.models.entity.Sede;
import com.decoracks.app.decoracks.models.entity.StockProductoSede;
import com.decoracks.app.decoracks.service.ProductoService;
import com.decoracks.app.decoracks.service.StockProductoSedeService;
import com.decoracks.app.decoracks.service.UsuarioService;

@Controller
@RequestMapping("/stock")
public class StockController {

    private final StockProductoSedeService stockService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService; // para obtener la sede

    public StockController(StockProductoSedeService stockService,
            ProductoService productoService,
            UsuarioService usuarioService) {
        this.stockService = stockService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String listar(Model model) {
        // Simula el login con un correo mientras no tengas login
        String correo = "carlosvegasba@gmail.com";
        var usuarioOpt = usuarioService.findByEmail(correo);

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con correo: " + correo);
        }

        var usuario = usuarioOpt.get();
        int sedeId = usuario.getSede().getId();

        model.addAttribute("sedeActual", usuario.getSede());
        model.addAttribute("stockSede", stockService.findBySedeId(sedeId)
                .stream()
                .filter(s -> s.getStock() > 0)
                .toList());
        model.addAttribute("productos", productoService.findAll());
        return "stock";
    }

    @PostMapping("/guardar")
    public String guardarStock(@RequestParam int productoId,
            @RequestParam int stock) {
        // ⚠️ Temporal: asumimos sede con ID 2
        Sede sede = new Sede();
        sede.setId(2); // Reemplazar con usuario.getSede() luego

        // Verifica si ya existe stock para este producto en esta sede
        StockProductoSede existente = stockService.findByProductoAndSede(productoId, sede.getId());

        if (existente != null) {
            // Si ya existe, suma el stock
            existente.setStock(existente.getStock() + stock);
            stockService.save(existente);
        } else {
            // Si no existe, crea nuevo
            StockProductoSede nuevo = new StockProductoSede();
            nuevo.setProducto(productoService.findById(productoId));
            nuevo.setSede(sede);
            nuevo.setStock(stock);
            stockService.save(nuevo);
        }

        return "redirect:/stock";
    }

    @PostMapping("/editar")
    public String editarStock(@RequestParam int id,
            @RequestParam int stock) {
        StockProductoSede existente = stockService.findById(id);
        if (existente != null) {
            existente.setStock(stock);
            stockService.save(existente);
        }
        return "redirect:/stock";
    }
}