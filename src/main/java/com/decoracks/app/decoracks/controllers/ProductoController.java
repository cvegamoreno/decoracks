package com.decoracks.app.decoracks.controllers;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.decoracks.app.decoracks.models.entity.Categoria;
import com.decoracks.app.decoracks.models.entity.Producto;
import com.decoracks.app.decoracks.service.CategoriaService;
import com.decoracks.app.decoracks.service.ProductoService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    private final CategoriaService categoriaService;

    public ProductoController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        return "products";
    }

    @PostMapping("/save")
    public String save(@RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") double precio,
            @RequestParam("categoria_id") int categoriaId) {

        Optional<Categoria> optionalCategoria = categoriaService.findById(categoriaId);

        if (optionalCategoria.isEmpty()) {
            return "redirect:/productos?errorCategoria";
        }

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCategoria(optionalCategoria.get());

        productoService.save(producto);

        return "redirect:/productos?success";
    }

    @PostMapping("/edit")
    public String updateProducto(@RequestParam("id") int id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") double precio,
            @RequestParam("categoria") int categoriaId) {

        Optional<Producto> optionalProducto = productoService.get(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();

            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            categoriaService.findById(categoriaId).ifPresent(producto::setCategoria);

            productoService.save(producto);
        }

        return "redirect:/productos";
    }

    @PostMapping("/delete")
    public String eliminarProducto(@RequestParam("id") int id) {
        try {
            productoService.deleteById(id);
            return "redirect:/productos?deleted";
        } catch (Exception e) {
            return "redirect:/productos?deleteError";
        }
    }
}
