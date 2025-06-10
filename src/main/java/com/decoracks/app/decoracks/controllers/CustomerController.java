package com.decoracks.app.decoracks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class CustomerController {
    
    @GetMapping("")
    public String customer(){
        return "clientes";
    }
}
