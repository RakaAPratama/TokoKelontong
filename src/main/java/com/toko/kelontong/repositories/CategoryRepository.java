package com.toko.kelontong.repositories;

import com.toko.kelontong.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
            SELECT COUNT(c.id)
            FROM Category c
            """)
    public Category countCategory();
}
