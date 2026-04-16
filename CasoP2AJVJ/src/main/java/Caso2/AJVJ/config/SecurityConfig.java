/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso2.AJVJ.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                // Rutas públicas
                .requestMatchers("/login", "/registro", "/css/**", "/js/**", "/webjars/**").permitAll()
                
                // Restricciones según el enunciado
                .requestMatchers("/usuarios/**", "/roles/**").hasRole("ADMIN")
                .requestMatchers("/eventos/nuevo", "/eventos/guardar", "/eventos/modificar/**", "/eventos/eliminar/**").hasAnyRole("ADMIN", "ORGANIZADOR")
                .requestMatchers("/eventos/listado", "/consultas/**").hasAnyRole("ADMIN", "ORGANIZADOR", "CLIENTE")
                
                .anyRequest().authenticated()
        )
        .formLogin((form) -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/eventos/listado", true)
        )
        .logout((logout) -> logout.permitAll());
        
        return http.build();
    }
}