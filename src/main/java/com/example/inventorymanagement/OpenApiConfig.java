package com.example.inventorymanagement;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                        .info(new Info()
                                .title("Inventory Management System API")
                                .description("REST API for managing inventory, products, and categories")
                                .version("1.0")
                                .contact(new Contact()
                                        .name("API Support")
                                        .email("support@example.com")));
        }
}