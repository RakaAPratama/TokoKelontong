package com.toko.kelontong.models;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID", nullable = false)
    private Long id;

    @Column(name = "SupplierName", nullable = false, length = 50)
    private String name;

    @Column(name = "Contact", nullable = false, length = 30)
    private String contact;

    @OneToMany(mappedBy = "supplierID")
    private Set<Product> products = new LinkedHashSet<>();

    public Supplier(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }
}