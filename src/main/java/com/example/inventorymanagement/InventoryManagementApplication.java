package com.example.inventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class InventoryManagementApplication {
    private static final Logger logger = LoggerFactory.getLogger(InventoryManagementApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementApplication.class, args);
        logger.info("Inventory Management Application has started successfully!");
    }
}
