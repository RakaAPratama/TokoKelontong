package com.toko.kelontong.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

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
    private Integer id;

    @Column(name = "\"Date\"", nullable = false)
    private Instant date;

    @Column(name = "ProductName", nullable = false, length = 100)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customerID;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "TotalPrice", nullable = false, precision = 19, scale = 4)
    private BigDecimal totalPrice;

}