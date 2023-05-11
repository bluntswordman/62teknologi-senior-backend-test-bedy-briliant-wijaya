package com.bedevenamdua.controllers;

import com.bedevenamdua.dto.CategoryData;
import com.bedevenamdua.entity.Business;
import com.bedevenamdua.service.BusinessService;
import com.bedevenamdua.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BusinessService businessService;

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody CategoryData categoryData) {
        Business existingBusiness = businessService.getBusinessById(categoryData.getBusinessId());
        if (existingBusiness == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Can't add category because the business ID you input was not found"));
        }
        return ResponseEntity.ok(categoryService.addCategory(categoryData));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<Object> getCategoriesByBusinessId(@PathVariable("businessId") UUID businessId) {
        return ResponseEntity.ok(categoryService.findCategoriesByBusinessId(businessId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(Map.of("message", "Category with ID " + id + " has been deleted"));
    }
}
