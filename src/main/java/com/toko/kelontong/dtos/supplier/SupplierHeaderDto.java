package com.toko.kelontong.dtos.supplier;

import com.toko.kelontong.models.Supplier;
import lombok.*;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class SupplierHeaderDto implements Serializable {
    private Long id;
    private String name;
    private String contact;

    public static List<SupplierHeaderDto> toListStream(List<Supplier> suppliers){
        if (suppliers.isEmpty()) {
            throw new EntityNotFoundException("Data supplier not found");
        }

        List<SupplierHeaderDto> result = new ArrayList<>();

        suppliers.stream().forEach(supplier -> {
            result.add(setSupplierHeader(supplier));
        });
        return result;
    }

    public static SupplierHeaderDto setSupplierHeader(Supplier supplier) {
        return new SupplierHeaderDto(
                supplier.getId(),
                supplier.getName(),
                supplier.getContact()
        );
    }
}
