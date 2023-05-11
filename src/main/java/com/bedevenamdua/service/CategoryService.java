package com.bedevenamdua.service;

import com.bedevenamdua.dto.CategoryData;
import com.bedevenamdua.entity.Category;
import com.bedevenamdua.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category addCategory(CategoryData categoryData) {
        return categoryRepository.addCategory(categoryData.getBusinessId(), categoryData.getAlias(), categoryData.getTitle());
    }

    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Iterable<Category> findCategoriesByBusinessId(UUID businessId) {
        return categoryRepository.findByBusinessId(businessId);
    }

    public Category findCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
