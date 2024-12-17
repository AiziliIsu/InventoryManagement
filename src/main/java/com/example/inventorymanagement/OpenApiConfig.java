package com.example.inventorymanagement;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI inventoryManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Management API")
                        .description("API which manages inventory, products, and categories")
                        .version("1.0"));
    }
}