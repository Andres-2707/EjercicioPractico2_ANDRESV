package Caso2.AJVJ.service.impl;

import Caso2.AJVJ.domain.Usuario;
import Caso2.AJVJ.repository.UsuarioRepository;
import Caso2.AJVJ.service.CorreoService;
import Caso2.AJVJ.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CorreoService correoService;

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
        // Determinamos si es un usuario nuevo antes de realizar el save
        // Si el id es nulo, es una inserción (nuevo registro)
        boolean esNuevo = (usuario.getId() == null);
        
        // Guardamos el usuario en la base de datos (Insert o Update)
        usuarioRepository.save(usuario);

        // Si el usuario era nuevo, disparamos el envío del correo de bienvenida
        if (esNuevo) {
            try {
                String mensaje = "<html><body>"
                        + "<h1>¡Bienvenido(a), " + usuario.getNombre() + "!</h1>"
                        + "<p>Tu cuenta ha sido creada exitosamente en la Plataforma de Eventos AJVJ.</p>"
                        + "<p>Ahora puedes iniciar sesión con tu correo: <b>" + usuario.getEmail() + "</b></p>"
                        + "<br/><p>Saludos cordiales,<br/>El equipo de administración.</p>"
                        + "</body></html>";
                
                correoService.enviarCorreo(
                    usuario.getEmail(), 
                    "Bienvenido a la Plataforma de Eventos AJVJ", 
                    mensaje
                );
            } catch (Exception e) {
                // Registramos el error en consola si falla el correo (ej. sin internet o mala config)
                // pero permitimos que el flujo continúe para que el usuario sí quede guardado
                System.err.println("Error al enviar el correo de bienvenida: " + e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}