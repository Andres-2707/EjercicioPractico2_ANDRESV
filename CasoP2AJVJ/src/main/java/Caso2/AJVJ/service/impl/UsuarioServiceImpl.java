/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso2.AJVJ.service.impl;



import Caso2.AJVJ.domain.Usuario;
import Caso2.AJVJ.repository.UsuarioRepository;
import Caso2.AJVJ.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender; // Inyección de Spring Mail

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Usuario usuario) {
        // Validamos si es un usuario nuevo (no tiene ID asignado aún)
        boolean esNuevo = (usuario.getId() == null);
        
        usuarioRepository.save(usuario);

        // Si es nuevo, disparamos el correo de bienvenida
        if (esNuevo) {
            enviarCorreoBienvenida(usuario.getEmail(), usuario.getNombre());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método privado para manejar la lógica del correo
    private void enviarCorreoBienvenida(String destinatario, String nombreUsuario) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destinatario);
            mensaje.setSubject("¡Bienvenido a la Plataforma de Eventos!");
            mensaje.setText("Hola " + nombreUsuario + ",\n\n"
                    + "Tu cuenta ha sido creada exitosamente. Ya puedes ingresar al sistema para gestionar o visualizar los eventos disponibles.\n\n"
                    + "Saludos,\nEl equipo de administración.");
            
            mailSender.send(mensaje);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}