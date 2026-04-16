package Caso2.AJVJ.service;

import jakarta.mail.MessagingException;

public interface CorreoService {
    // Definimos el método básico para enviar correos
    public void enviarCorreo(String to, String subject, String body) throws MessagingException;
}