package com.example.inventorymanagement.service;

import com.example.inventorymanagement.model.Product;
import com.example.inventorymanagement.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        log.info("Successfully fetched {} products", products.size());
        return products;
    }

    public Product getProductById(Long id) {
        log.info("Fetching product by ID: {}", id);
        return productRepository.findById(id).orElseGet(() -> {
            log.warn("Product with ID: {} not found", id);
            return null;
        });
    }

    public Product saveProduct(Product product) {
        log.info("Saving product with name: {}", product.getName());
        Product savedProduct = productRepository.save(product);
        log.info("Successfully saved product with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            log.info("Successfully deleted product with ID: {}", id);
        } else {
            log.warn("Cannot delete product. No product found with ID: {}", id);
        }
    }
    public List<Product> searchProductsByName(String name) {
        log.info("Searching products with name containing: {}", name);
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        log.info("Found {} products matching the name: {}", products.size(), name);
        return products;
    }

    public List<Product> filterProductsByCategory(String categoryName) {
        log.info("Filtering products by category: {}", categoryName);
        List<Product> products = productRepository.findByCategory_Name(categoryName);
        log.info("Found {} products in category: {}", products.size(), categoryName);
        return products;
    }
}
