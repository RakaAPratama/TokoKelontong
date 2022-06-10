package com.toko.kelontong.dtos.customer;

import com.toko.kelontong.models.Customer;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class CustomerUpdateInsertDto implements Serializable {
    private String name;
    private String contact;

    public Customer insertCustomer() {
        return new Customer(
                name,
                contact
        );
    }

    public void setValue(Customer customer) {
        customer.setName(name);
        customer.setContact(contact);
    }
}
