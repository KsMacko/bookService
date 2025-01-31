package com.example.bookstorageservice.swagger;

import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        ClassPathResource resource = new ClassPathResource("swag.yaml");
        try {
            return Yaml.mapper().readValue(resource.getInputStream(), OpenAPI.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

