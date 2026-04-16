/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Caso2.AJVJ.service;

import Caso2.AJVJ.domain.Evento;
import java.util.List;

public interface EventoService {
    // CRUD Básico
    List<Evento> getEventos();
    Evento getEvento(Long id);
    void save(Evento evento);
    void delete(Long id);

    // Consultas Avanzadas
    List<Evento> getEventosActivos();
    List<Evento> getEventosPorNombre(String nombre);
    long contarEventosActivos();
}