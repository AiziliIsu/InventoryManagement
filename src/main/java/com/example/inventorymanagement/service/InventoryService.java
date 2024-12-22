
package com.example.inventorymanagement.service;

import com.example.inventorymanagement.model.Inventory;
import com.example.inventorymanagement.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Service
@Slf4j
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {
        log.info("Fetching all inventories");
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        log.info("Fetching inventory with id: {}", id);
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory saveInventory(Inventory inventory) {
        log.info("Saving inventory: {}", inventory);
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        log.info("Deleting inventory with id: {}", id);
        inventoryRepository.deleteById(id);
    }

    public Inventory addStock(Long productId, int quantity) {
        log.info("Adding stock for productId: {}, quantity: {}", productId, quantity);

        Inventory inventory = inventoryRepository.findByProduct_Id(productId)
                .orElseThrow(() -> {
                    log.error("Product with productId: {} not found in inventory", productId);
                    return new RuntimeException("Product not found in inventory");
                });

        int updatedStock = inventory.getStock() + quantity;
        inventory.setStock(updatedStock);
        Inventory updatedInventory = inventoryRepository.save(inventory);

        log.info("Successfully added stock for productId: {}. Updated stock: {}", productId, updatedStock);
        return updatedInventory;
    }

    public Inventory deductStock(Long productId, int quantity) {
        log.info("Deducting stock for productId: {}, quantity: {}", productId, quantity);

        Inventory inventory = inventoryRepository.findByProduct_Id(productId)
                .orElseThrow(() -> {
                    log.error("Product with productId: {} not found in inventory", productId);
                    return new RuntimeException("Product not found in inventory");
                });

        if (inventory.getStock() < quantity) {
            log.error("Not enough stock available for productId: {}. Current stock: {}, requested: {}", productId, inventory.getStock(), quantity);
            throw new IllegalArgumentException("Not enough stock available");
        }

        int updatedStock = inventory.getStock() - quantity;
        inventory.setStock(updatedStock);
        Inventory updatedInventory = inventoryRepository.save(inventory);

        log.info("Successfully deducted stock for productId: {}. Updated stock: {}", productId, updatedStock);
        return updatedInventory;
    }

    public int checkStock(Long productId) {
        log.info("Checking stock level for productId: {}", productId);

        Inventory inventory = inventoryRepository.findByProduct_Id(productId)
                .orElseThrow(() -> {
                    log.error("Product with productId: {} not found in inventory", productId);
                    return new RuntimeException("Product not found in inventory");
                });

        int stockLevel = inventory.getStock();
        log.info("Current stock level for productId: {} is {}", productId, stockLevel);
        return stockLevel;
    }

    public List<Inventory> getLowStockProducts(int threshold) {
        log.info("Fetching products with stock less than threshold: {}", threshold);

        List<Inventory> lowStockProducts = inventoryRepository.findByStockLessThan(threshold);
        log.info("Found {} products with stock less than {}", lowStockProducts.size(), threshold);

        return lowStockProducts;
    }
}









