package com.toko.kelontong.dtos.product;

import com.toko.kelontong.models.Product;
import lombok.*;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class ProductHeaderDto implements Serializable {
    private Long id;
    private String name;
    private String category;
    private String supplier;
    private BigDecimal price;
    private Integer stock;

    public static List<ProductHeaderDto> toListStream(List<Product> products){
        if(products.isEmpty()){
            throw new EntityNotFoundException("Data product not found");
        }

        List<ProductHeaderDto> result = new ArrayList<>();

        products.stream().forEach(product -> {
            result.add(setProductHeader(product));
        });
        return result;
    }

    public static ProductHeaderDto setProductHeader(Product product){
        return new ProductHeaderDto(
                product.getId(),
                product.getName(),
                product.getCategoryID().getName(),
                product.getSupplierID().getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}
