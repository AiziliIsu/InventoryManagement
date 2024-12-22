package com.example.inventorymanagement.service;

import com.example.inventorymanagement.model.Category;
import com.example.inventorymanagement.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        log.info("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        log.info("Successfully fetched {} categories", categories.size());
        return categories;
    }

    public Category getCategoryById(Long id) {
        log.info("Fetching category by ID: {}", id);
        return categoryRepository.findById(id).orElseGet(() -> {
            log.warn("Category with ID: {} not found", id);
            return null;
        });
    }

    public Category saveCategory(Category category) {
        log.info("Saving category with name: {}", category.getName());
        Category savedCategory = categoryRepository.save(category);
        log.info("Successfully saved category with ID: {}", savedCategory.getId());
        return savedCategory;
    }

    public void deleteCategory(Long id) {
        log.info("Deleting category with ID: {}", id);
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            log.info("Successfully deleted category with ID: {}", id);
        } else {
            log.warn("Cannot delete category. No category found with ID: {}", id);
        }
    }
}
