package com.toko.kelontong.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID", nullable = false)
    private Integer id;

    @Column(name = "ProductName", nullable = false, length = 100)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category categoryID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SupplierID", nullable = false)
    private Supplier supplierID;

    @Column(name = "Price", nullable = false, precision = 19, scale = 4)
    private BigDecimal price;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "productID")
    private Set<TransactionDetail> transactionDetails = new LinkedHashSet<>();

}