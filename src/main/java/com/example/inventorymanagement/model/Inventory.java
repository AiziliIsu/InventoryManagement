package com.example.inventorymanagement.model;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int stock; 

    // Constructors, Getters, and Setters
    public Inventory() {}

    public Inventory(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id= this.id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}


