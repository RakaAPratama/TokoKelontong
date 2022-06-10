package com.toko.kelontong.dtos.history;

import com.toko.kelontong.models.Customer;
import com.toko.kelontong.models.History;
import com.toko.kelontong.models.Product;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class HistoryUpdateInsertDto implements Serializable {

    private String productName;
    private Long customerId;
    private Long quantity;

    public History insertHistory(Customer customer) {
        return new History(
                productName,
                customer,
                quantity
        );
    }

    public void setValue(History history, Customer customer){
        history.setProductName(productName);
        history.setCustomerID(customer);
        history.setQuantity(quantity);
    }
}
