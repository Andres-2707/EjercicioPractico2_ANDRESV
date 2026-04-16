/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Caso2.AJVJ.repository;



import Caso2.AJVJ.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Necesario para que el UsuarioDetailsService busque al usuario para el Login
    Usuario findByEmailAndActivoTrue(String email);

    // Consulta Avanzada 1: Buscar usuarios por rol
    List<Usuario> findByRol_NombreRol(String nombreRol);
}