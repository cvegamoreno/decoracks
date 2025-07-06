package com.decoracks.app.decoracks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.decoracks.app.decoracks.models.entity.Sede;
import com.decoracks.app.decoracks.models.entity.Usuario;
import com.decoracks.app.decoracks.service.SedeService;
import com.decoracks.app.decoracks.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsersController {
    @Autowired
    private UsuarioService usuarioService;
    public UsersController(SedeService sedeService) {
        this.sedeService = sedeService;
    }
    @Autowired
    private SedeService sedeService;
    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("sedes", sedeService.findAll());
        return "users";
    }

    @PostMapping("/save")
    public String save(@RequestParam("nombre_usuario") String nombre,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("sede_id") int sedeId,
                       @RequestParam("rol") String rol) {

        Sede sede = new Sede();
        sede.setId(sedeId);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setSede(sede);
        usuario.setRol(Usuario.Rol.valueOf(rol.toLowerCase()));

        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }
    @PostMapping("/update")
    public String update(@RequestParam("id") int id,
                         @RequestParam("nombre_usuario") String nombre,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("sede_id") int sedeId,
                         @RequestParam("rol") String rol) {

        Sede sede = new Sede();
        sede.setId(sedeId);

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setSede(sede);
        usuario.setRol(Usuario.Rol.valueOf(rol.toLowerCase()));

        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("id_usuario_eliminar") int id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios";
    }

}
