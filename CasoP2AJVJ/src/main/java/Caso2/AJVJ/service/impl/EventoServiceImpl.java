package Caso2.AJVJ.service.impl;

import Caso2.AJVJ.domain.Evento;
import Caso2.AJVJ.repository.EventoRepository;
import Caso2.AJVJ.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> getEventos() {
        return eventoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Evento getEvento(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Evento evento) {
        eventoRepository.save(evento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eventoRepository.deleteById(id);
    }

    // --- Implementación de Consultas Avanzadas ---
    
    @Override
    @Transactional(readOnly = true)
    public List<Evento> getEventosActivos() {
        return eventoRepository.findByActivoTrue(); // Consulta 1
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> getEventosPorNombre(String nombre) {
        return eventoRepository.findByNombreContainingIgnoreCase(nombre); // Consulta 2
    }

    @Override
    @Transactional(readOnly = true)
    public long contarEventosActivos() {
        return eventoRepository.countByActivoTrue(); // Consulta 3
    }
}