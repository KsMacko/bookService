package com.example.bookstorageservice.swagger;


import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Collections;

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
//        return new OpenAPI()
//                .info(new Info().title("API Documentation")
//                .version("1.0")
//                .description("API documentation for your application"))
//                .servers(Collections.singletonList(
//                        new Server().url("http://localhost:8080").description("book-storage-service")));

    }
}

