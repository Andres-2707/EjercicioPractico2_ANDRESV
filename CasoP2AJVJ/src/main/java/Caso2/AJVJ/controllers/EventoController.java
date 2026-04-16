/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso2.AJVJ.controllers;

import Caso2.AJVJ.domain.Evento;
import Caso2.AJVJ.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Listar todos los eventos
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("eventos", eventoService.getEventos());
        model.addAttribute("totalActivos", eventoService.contarEventosActivos());
        return "eventos/listado";
    }

    // Cargar formulario para nuevo evento
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("evento", new Evento());
        return "eventos/modifica";
    }

    // Guardar (Insertar o Actualizar)
    @PostMapping("/guardar")
    public String guardar(Evento evento) {
        eventoService.save(evento);
        return "redirect:/eventos/listado";
    }

    // Cargar evento específico para editar
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, Model model) {
        Evento evento = eventoService.getEvento(id);
        model.addAttribute("evento", evento);
        return "eventos/modifica";
    }

    // Eliminar evento
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        eventoService.delete(id);
        return "redirect:/eventos/listado";
    }
}