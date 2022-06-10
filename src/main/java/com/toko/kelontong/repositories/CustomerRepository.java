package com.toko.kelontong.repositories;

import com.toko.kelontong.dtos.customer.CustomerHeaderDto;
import com.toko.kelontong.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
            SELECT COUNT(c.id)
            FROM Customer c
            """)
    public Customer countCustomer();
}
