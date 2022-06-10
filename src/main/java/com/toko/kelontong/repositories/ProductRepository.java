package com.toko.kelontong.repositories;

import com.toko.kelontong.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT COUNT(p.id)
            FROM Product p
            """)
    public Product countProduct();

}
