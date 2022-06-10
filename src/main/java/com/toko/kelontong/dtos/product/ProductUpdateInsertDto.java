package com.toko.kelontong.dtos.product;

import com.toko.kelontong.models.Category;
import com.toko.kelontong.models.Product;
import com.toko.kelontong.models.Supplier;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class ProductUpdateInsertDto implements Serializable {
    private String name;
    private Long categoryId;
    private Long supplierId;
    private BigDecimal price;
    private Integer stock;

    public Product insertProduct(Category category, Supplier supplier) {
        return new Product(
                name,
                category,
                supplier,
                price,
                stock
        );
    }

    public void setValue(Product product, Category category, Supplier supplier) {
        product.setName(name);
        product.setCategoryID(category);
        product.setSupplierID(supplier);
        product.setPrice(price);
        product.setStock(stock);
    }

}
