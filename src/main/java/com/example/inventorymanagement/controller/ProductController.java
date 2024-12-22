package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.Product;
import com.example.inventorymanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        List<Product> products = productService.getAllProducts();
        log.info("Successfully fetched {} products", products.size());
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        log.info("Fetching product with ID: {}", id);
        Product product = productService.getProductById(id);
        if (product == null) {
            log.warn("Product with ID: {} not found", id);
        } else {
            log.info("Successfully fetched product with ID: {}", id);
        }
        return product;
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        log.info("Creating new product with name: {}", product.getName());
        Product savedProduct = productService.saveProduct(product);
        log.info("Successfully created product with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        log.info("Updating product with ID: {}", id);
        product.setId(id);
        Product updatedProduct = productService.saveProduct(product);
        log.info("Successfully updated product with ID: {}", id);
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        log.info("Successfully deleted product with ID: {}", id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        log.info("Searching products by name: {}", name);
        List<Product> products = productService.searchProductsByName(name);
        log.info("Found {} products matching the name: {}", products.size(), name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filter-by-category")
    public ResponseEntity<List<Product>> filterByCategory(@RequestParam String categoryName) {
        log.info("Filtering products by category name: {}", categoryName);
        List<Product> products = productService.filterProductsByCategory(categoryName);
        log.info("Found {} products in category: {}", products.size(), categoryName);
        return ResponseEntity.ok(products);
    }
}

