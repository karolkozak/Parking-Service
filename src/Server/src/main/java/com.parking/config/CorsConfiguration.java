package com.parking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfiguration extends WebMvcConfigurerAdapter {
    @Value("${cors.access.allowed.origins}")
    private String accessAllowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(accessAllowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(3600);
    }
}
