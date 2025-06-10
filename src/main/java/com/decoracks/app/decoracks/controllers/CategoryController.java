package com.decoracks.app.decoracks.controllers;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.decoracks.app.decoracks.models.entity.Categoria;
import com.decoracks.app.decoracks.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("")
    public String home(){
        return "category";
    }

    @PostMapping("/save")
    public String save(Categoria categoria){
        LOGGER.info("este es el objeto ctegoria {}", categoria);
        categoriaService.save(categoria);
        return "redirect:/categorias";
    }
}
