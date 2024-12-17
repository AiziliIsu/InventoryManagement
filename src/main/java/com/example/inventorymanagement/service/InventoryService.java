
package com.example.inventorymanagement.service;

import com.example.inventorymanagement.model.Inventory;
import com.example.inventorymanagement.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory addStock(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProduct_Id(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));

        inventory.setStock(inventory.getStock() + quantity);
        return inventoryRepository.save(inventory);
    }

    // Deduct stock for a product with validation
    public Inventory deductStock(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProduct_Id(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));

        if (inventory.getStock() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        inventory.setStock(inventory.getStock() - quantity);
        return inventoryRepository.save(inventory);
    }

    // Check stock level for a product
    public int checkStock(Long productId) {
        Inventory inventory = inventoryRepository.findByProduct_Id(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));

        return inventory.getStock();
    }

    // Filter low-stock products
    public List<Inventory> getLowStockProducts(int threshold) {
        return inventoryRepository.findByStockLessThan(threshold);
    }
}
