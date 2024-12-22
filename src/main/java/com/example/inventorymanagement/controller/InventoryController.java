package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.Inventory;
import com.example.inventorymanagement.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@Slf4j
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllInventories() {
        log.info("Fetching all inventories");
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        log.info("Fetching inventory with id: {}", id);
        return inventoryService.getInventoryById(id);
    }

    @PostMapping
    public Inventory createInventory(@Valid @RequestBody Inventory inventory) {
        log.info("Creating new inventory: {}", inventory);
        return inventoryService.saveInventory(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @Valid @RequestBody Inventory inventory) {
        log.info("Updating inventory with id: {}, new data: {}", id, inventory);
        inventory.setId(id);
        return inventoryService.saveInventory(inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        log.info("Deleting inventory with id: {}", id);
        inventoryService.deleteInventory(id);
    }

    @PostMapping("/add-stock/{productId}")
    public ResponseEntity<?> addStock(@PathVariable Long productId, @RequestParam int quantity) {
        log.info("Adding stock for productId: {}, quantity: {}", productId, quantity);
        try {
            Inventory updatedInventory = inventoryService.addStock(productId, quantity);
            return ResponseEntity.ok(updatedInventory);
        } catch (RuntimeException e) {
            log.error("Error while adding stock: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/deduct-stock/{productId}")
    public ResponseEntity<?> deductStock(@PathVariable Long productId, @RequestParam int quantity) {
        log.info("Deducting stock for productId: {}, quantity: {}", productId, quantity);
        try {
            Inventory updatedInventory = inventoryService.deductStock(productId, quantity);
            return ResponseEntity.ok(updatedInventory);
        } catch (RuntimeException e) {
            log.error("Error while deducting stock: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-stock/{productId}")
    public ResponseEntity<?> checkStock(@PathVariable Long productId) {
        log.info("Checking stock for productId: {}", productId);
        try {
            int stockLevel = inventoryService.checkStock(productId);
            return ResponseEntity.ok("Current stock level: " + stockLevel);
        } catch (RuntimeException e) {
            log.error("Error while checking stock: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Inventory>> getLowStockProducts(@RequestParam(defaultValue = "10") int threshold) {
        log.info("Fetching products with stock below threshold: {}", threshold);
        List<Inventory> lowStockProducts = inventoryService.getLowStockProducts(threshold);
        log.info("Found {} products with stock below threshold: {}", lowStockProducts.size(), threshold);
        return ResponseEntity.ok(lowStockProducts);
    }
}

