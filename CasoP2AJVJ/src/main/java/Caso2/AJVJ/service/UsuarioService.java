/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Caso2.AJVJ.service;



import Caso2.AJVJ.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> getUsuarios();
    Usuario getUsuario(Long id);
    void save(Usuario usuario);
    void delete(Long id);
}