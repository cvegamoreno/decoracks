package com.decoracks.app.decoracks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.decoracks.app.decoracks.models.entity.Cliente;
import com.decoracks.app.decoracks.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class CustomerController {
    public String vistaClientes(Model model) {
        List<String> distritos = List.of(
                "Ancón", "Ate", "Barranco", "Breña", "Carabayllo", "Chaclacayo", "Chorrillos",
                "Cieneguilla", "Comas", "El Agustino", "Independencia", "Jesús María",
                "La Molina", "La Victoria", "Lima", "Lince", "Los Olivos", "Lurigancho",
                "Lurín", "Magdalena del Mar", "Miraflores", "Pachacámac", "Pucusana",
                "Pueblo Libre", "Puente Piedra", "Punta Hermosa", "Punta Negra",
                "Rímac", "San Bartolo", "San Borja", "San Isidro", "San Juan de Lurigancho",
                "San Juan de Miraflores", "San Luis", "San Martín de Porres", "San Miguel",
                "Santa Anita", "Santa María del Mar", "Santa Rosa", "Santiago de Surco",
                "Surquillo", "Villa El Salvador", "Villa María del Triunfo");
        model.addAttribute("distritos", distritos);
        return "clientes";
    }

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        vistaClientes(model);
        return "clientes";
    }

    @PostMapping("/save")
    public String save(@RequestParam("nombre_cliente") String nombre,
            @RequestParam("correo") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("direccion") String direccion,
            @RequestParam("distrito") String distrito,
            @RequestParam("dni") String dni) {

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCorreo(email);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        cliente.setDistrito(distrito);
        cliente.setDni(dni);

        clienteService.save(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id,
            @RequestParam("nombre_cliente") String nombre,
            @RequestParam("correo") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("direccion") String direccion,
            @RequestParam("distrito") String distrito,
            @RequestParam("dni") String dni) {

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(nombre);
        cliente.setCorreo(email);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        cliente.setDistrito(distrito);
        cliente.setDni(dni);

        clienteService.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<?> buscarPorDni(@RequestParam String dni) {
        var clienteOpt = clienteService.findByDni(dni);
        if (clienteOpt.isPresent()) {
            return ResponseEntity.ok(clienteOpt.get());
        } else {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
    }
}
