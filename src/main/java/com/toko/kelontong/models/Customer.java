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
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID", nullable = false)
    private Integer id;

    @Column(name = "CustomerName", nullable = false, length = 100)
    private String customerName;

    @Column(name = "Contact", nullable = false, length = 30)
    private String contact;

    @OneToMany(mappedBy = "customerID")
    private Set<Transaction> transactions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "customerID")
    private Set<History> histories = new LinkedHashSet<>();

}