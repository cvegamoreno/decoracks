package com.decoracks.app.decoracks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.decoracks.app.decoracks.models.entity.Cliente;
import com.decoracks.app.decoracks.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class CustomerController {
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("")
    public String customer(){
        return "clientes";
    }

    @PostMapping("/save")
    public String save(Cliente cliente){
        clienteService.save(cliente);
        return "redirect:/clientes";
    }
}
