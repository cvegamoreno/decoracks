package com.decoracks.app.decoracks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.decoracks.app.decoracks.models.entity.Usuario;
import com.decoracks.app.decoracks.models.entity.Venta;
import com.decoracks.app.decoracks.service.UsuarioService;
import com.decoracks.app.decoracks.service.VentaService;

@Controller
@RequestMapping("/tecnico")
public class TecController {

    private final VentaService ventaService;
    private final UsuarioService usuarioService;

    public TecController(VentaService ventaService, UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String listarVentasSinTecnico(Model model) {
        List<Venta> ventasSinTecnico = ventaService.findVentasSinTecnico();
        model.addAttribute("ventas", ventasSinTecnico);

        List<Usuario> tecnicos = usuarioService.findAll();
        model.addAttribute("tecnicos", tecnicos);

        return "asigTecnico";
    }

    @PostMapping("/asignar-tecnico")
    public String asignarTecnico(@RequestParam("ventaId") int ventaId,
            @RequestParam("usuarioId") int usuarioId) {

        Optional<Venta> ventaOpt = ventaService.findById(ventaId);
        Optional<Usuario> usuarioOpt = usuarioService.findById(usuarioId);

        if (ventaOpt.isPresent() && usuarioOpt.isPresent()) {
            Venta venta = ventaOpt.get();
            venta.setUsuario(usuarioOpt.get());
            ventaService.save(venta);
        }

        return "redirect:/pedidos/asignar-tecnico";
    }
}
