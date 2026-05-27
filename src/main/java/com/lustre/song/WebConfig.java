package com.lustre.song;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Value("${ALLOWED_ORIGINS:http://localhost:5173,https://song-ui-r7d7.onrender.com}")
            private String allowedOrigins;
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String[] origins = java.util.Arrays.stream(allowedOrigins.split(","))
                        .map(String::trim)
                        .toArray(String[]::new);
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins(origins) // Support multiple origins separated by commas
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true); // If using cookies or authentication headers
            }
        };
    }
}