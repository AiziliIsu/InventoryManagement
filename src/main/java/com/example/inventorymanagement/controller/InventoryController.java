package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.Inventory;
import com.example.inventorymanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping
    public Inventory createInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @Valid @RequestBody Inventory inventory) {
        inventory.setId(id);
        return inventoryService.saveInventory(inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }

    @PostMapping("/add-stock/{productId}")
    public ResponseEntity<?> addStock(@PathVariable Long productId, @RequestParam int quantity) {
        try {
            Inventory updatedInventory = inventoryService.addStock(productId, quantity);
            return ResponseEntity.ok(updatedInventory);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/deduct-stock/{productId}")
    public ResponseEntity<?> deductStock(@PathVariable Long productId, @RequestParam int quantity) {
        try {
            Inventory updatedInventory = inventoryService.deductStock(productId, quantity);
            return ResponseEntity.ok(updatedInventory);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/check-stock/{productId}")
    public ResponseEntity<?> checkStock(@PathVariable Long productId) {
        try {
            int stockLevel = inventoryService.checkStock(productId);
            return ResponseEntity.ok("Current stock level: " + stockLevel);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/low-stock")
    public ResponseEntity<List<Inventory>> getLowStockProducts(@RequestParam(defaultValue = "10") int threshold) {
        return ResponseEntity.ok(inventoryService.getLowStockProducts(threshold));
    }
}
