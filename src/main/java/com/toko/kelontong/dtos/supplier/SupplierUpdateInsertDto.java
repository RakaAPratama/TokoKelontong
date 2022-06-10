package com.toko.kelontong.dtos.supplier;

import com.toko.kelontong.models.Supplier;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Getter
@Setter
public class SupplierUpdateInsertDto implements Serializable {
    private String name;
    private String contact;

    public Supplier insertSupplier() {
        return new Supplier(
                name,
                contact
        );
    }

    public void setValue(Supplier supplier) {
        supplier.setName(name);
        supplier.setContact(contact);
    }

}
