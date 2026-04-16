/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso2.AJVJ.service.impl;



import Caso2.AJVJ.domain.Usuario;
import Caso2.AJVJ.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscamos al usuario por su correo electrónico (que es único en tu BD)
        Usuario usuario = usuarioRepository.findByEmailAndActivoTrue(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("El usuario no existe o está inactivo: " + email);
        }

        // Guardamos el nombre en sesión para mostrarlo en el Navbar con Thymeleaf
        session.setAttribute("nombreUsuario", usuario.getNombre());

        // Extraemos el rol y le agregamos el prefijo "ROLE_" que exige Spring Security
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombreRol()));

        // Retornamos el objeto User nativo de Spring Security
        return new User(usuario.getEmail(), "{noop}" + usuario.getPassword(), roles);
    }
}