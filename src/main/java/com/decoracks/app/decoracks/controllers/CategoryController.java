package com.decoracks.app.decoracks.controllers;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.decoracks.app.decoracks.models.entity.Categoria;
import com.decoracks.app.decoracks.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "category";
    }

    @PostMapping("/save")
    public String save(Categoria categoria){
        LOGGER.info("este es el objeto ctegoria {}", categoria);
        categoriaService.save(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/update")
    public String updateCategoria(@RequestParam("id") int id,
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("descripcion") String descripcion) {
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        categoriaService.save(categoria);
        return "redirect:/categorias";
    }
    
    @PostMapping("/delete")
    public String deleteCategoria(@RequestParam("id") int id) {
        categoriaService.deleteById(id);
        return "redirect:/categorias";
    }
}
