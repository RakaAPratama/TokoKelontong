package com.toko.kelontong.dtos.transaction;

import com.toko.kelontong.models.Customer;
import com.toko.kelontong.models.Transaction;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class TransactionUpdateInsertDto implements Serializable {
    private Long customerId;

    public Transaction insertTransaction(Customer customer) {
        return new Transaction(
                customer
        );
    }

    public void setValue(Transaction transaction, Customer customer) {
        if (this.customerId != null) {
            transaction.setCustomerID(customer);
        }
    }
}
