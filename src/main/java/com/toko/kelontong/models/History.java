package com.toko.kelontong.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "History")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HistoryID", nullable = false)
    private Long id;

    @Column(name = "\"Date\"", nullable = false)
    private LocalDate date;

    @Column(name = "ProductName", nullable = false, length = 100)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customerID;

    @Column(name = "Quantity", nullable = false)
    private Long quantity;

    public History(String productName, Customer customerID, Long quantity) {
        this.date = LocalDate.now();
        this.productName = productName;
        this.customerID = customerID;
        this.quantity = quantity;
    }

}