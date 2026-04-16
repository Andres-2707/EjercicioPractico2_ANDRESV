package Caso2.AJVJ.controllers;

import Caso2.AJVJ.domain.Rol;
import Caso2.AJVJ.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("roles", rolService.getRoles());
        return "roles/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "roles/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(Rol rol) {
        rolService.save(rol);
        return "redirect:/roles/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, Model model) {
        Rol rol = rolService.getRol(id);
        model.addAttribute("rol", rol);
        return "roles/modifica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        rolService.delete(id);
        return "redirect:/roles/listado";
    }
}