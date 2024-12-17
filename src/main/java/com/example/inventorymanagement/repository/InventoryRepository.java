package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}