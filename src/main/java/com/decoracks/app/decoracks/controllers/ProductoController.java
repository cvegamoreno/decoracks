package com.decoracks.app.decoracks.controllers;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.decoracks.app.decoracks.models.entity.Categoria;
import com.decoracks.app.decoracks.models.entity.Producto;
import com.decoracks.app.decoracks.service.ProductoService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;
    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "products";
    }

    @PostMapping("/save")
    public String save(Producto producto){
        LOGGER.info("Este es el objeto producto {}", producto);
        Categoria c= new Categoria(1, "", "");
        producto.setCategoria(c);
        productoService.save(producto);
        return "redirect:/productos";
    }
}
