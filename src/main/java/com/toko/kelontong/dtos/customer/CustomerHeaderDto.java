package com.toko.kelontong.dtos.customer;

import com.toko.kelontong.models.Customer;
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
public class CustomerHeaderDto implements Serializable {
    private Long id;
    private String name;
    private String contact;

    public static List<CustomerHeaderDto> toListStream(List<Customer> customers){
        if (customers.isEmpty()) {
            throw new EntityNotFoundException("Data customer not found");
        }

        List<CustomerHeaderDto> result = new ArrayList<>();

        customers.stream().forEach(customer -> {
            result.add(setCustomerHeader(customer));
        });
        return result;
    }

    public static CustomerHeaderDto setCustomerHeader(Customer customer){
        return new CustomerHeaderDto(
                customer.getId(),
                customer.getName(),
                customer.getContact()
        );
    }
}
