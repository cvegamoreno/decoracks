package com.decoracks.app.decoracks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.decoracks.app.decoracks.models.entity.Sede;
import com.decoracks.app.decoracks.models.entity.Usuario;
import com.decoracks.app.decoracks.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsersController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "users";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        Sede s= new Sede(1,"","","",0,0);
        usuario.setSede(s);
        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }
}
