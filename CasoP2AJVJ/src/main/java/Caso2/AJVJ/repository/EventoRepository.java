package Caso2.AJVJ.repository;

import Caso2.AJVJ.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    // Consulta Avanzada 1: Buscar eventos por estado
    List<Evento> findByActivoTrue();

    // Consulta Avanzada 2: Buscar eventos por coincidencia parcial en nombre
    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    // Consulta Avanzada 3: Contar eventos activos (¡ESTA ES LA LÍNEA QUE FALTABA!)
    long countByActivoTrue();
}