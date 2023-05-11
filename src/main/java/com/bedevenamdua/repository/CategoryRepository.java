package com.bedevenamdua.repository;

import com.bedevenamdua.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "INSERT INTO categories (alias, title, fk_business_id) VALUES (?2, ?3, ?1) RETURNING *", nativeQuery = true)
    Category addCategory(UUID businessId, String alias, String title);

    @Query(value = "SELECT * FROM categories WHERE fk_business_id = ?1", nativeQuery = true)
    Iterable<Category> findByBusinessId(UUID businessId);
}
