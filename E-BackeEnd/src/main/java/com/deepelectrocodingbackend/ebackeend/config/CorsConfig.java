package com.deepelectrocodingbackend.ebackeend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permet à toutes les routes d'accepter des requêtes CORS
                .allowedOrigins("http://localhost:4200")  // Autorise le frontend Angular exécuté sur le port 4200
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Méthodes autorisées
                .allowedHeaders("*")  // Autorise tous les headers
                .allowCredentials(true);  // Autorise l'envoi de cookies
    }
}
