package com.decoracks.app.decoracks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoryController {

    @GetMapping("")
    public String home(){
        return "category";
    }   
}
