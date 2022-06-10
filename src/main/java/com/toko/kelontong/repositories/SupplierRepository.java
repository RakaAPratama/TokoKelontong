package com.toko.kelontong.repositories;

import com.toko.kelontong.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("""
            SELECT COUNT(s.id)
            FROM Supplier s
            """)
    public Supplier countSupplier();
}
