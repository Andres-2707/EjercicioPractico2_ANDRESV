package Caso2.AJVJ.controllers;

import Caso2.AJVJ.domain.Usuario;
import Caso2.AJVJ.repository.RolRepository;
import Caso2.AJVJ.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Inyectamos el repositorio de roles directamente para llenar el <select> del formulario
    @Autowired
    private RolRepository rolRepository;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("usuarios", usuarioService.getUsuarios());
        return "usuarios/listado"; // Llama a listado.html en la carpeta usuarios
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolRepository.findAll()); // Pasamos los roles a la vista
        return "usuarios/modifica"; // Reutilizamos la vista de modificación
    }

    @PostMapping("/guardar")
    public String guardar(Usuario usuario) {
        // Al guardar aquí, el UsuarioServiceImpl detectará si es nuevo y enviará el correo
        usuarioService.save(usuario);
        return "redirect:/usuarios/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getUsuario(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolRepository.findAll()); // Pasamos los roles a la vista
        return "usuarios/modifica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return "redirect:/usuarios/listado";
    }
}