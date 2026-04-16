package Caso2.AJVJ.controllers;

import Caso2.AJVJ.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {

    @Autowired
    private EventoService eventoService;

    // Ruta base: carga la página vacía o con todos los eventos activos por defecto
    @GetMapping("/eventos")
    public String paginaConsultas(Model model) {
        model.addAttribute("eventos", eventoService.getEventosActivos());
        model.addAttribute("totalActivos", eventoService.contarEventosActivos());
        return "consultas/listado";
    }

    // Consulta 1: Buscar por coincidencia parcial (POST)
    @PostMapping("/porNombre")
    public String buscarPorNombre(@RequestParam(value = "nombre") String nombre, Model model) {
        model.addAttribute("eventos", eventoService.getEventosPorNombre(nombre));
        model.addAttribute("busquedaAplicada", nombre); // Persistimos el valor en la vista
        return "consultas/listado";
    }

    // Consulta 2 y 3: Mostrar solo activos y contarlos
    @GetMapping("/activos")
    public String buscarActivos(Model model) {
        model.addAttribute("eventos", eventoService.getEventosActivos());
        model.addAttribute("totalActivos", eventoService.contarEventosActivos());
        model.addAttribute("filtroActivo", true); // Bandera visual
        return "consultas/listado";
    }
}