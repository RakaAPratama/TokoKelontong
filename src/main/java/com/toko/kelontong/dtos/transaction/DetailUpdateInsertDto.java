package com.toko.kelontong.dtos.transaction;

import com.toko.kelontong.models.Product;
import com.toko.kelontong.models.Transaction;
import com.toko.kelontong.models.TransactionDetail;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class DetailUpdateInsertDto implements Serializable {
    private Long id;
    private Long transactionId;
    private Long productId;
    private Long quantity;

    public TransactionDetail insertDetail(Transaction transaction, Product product) {
        return new TransactionDetail(
                transaction,
                product,
                quantity
        );
    }

    public void setValue(TransactionDetail transactionDetail, Transaction transaction, Product product) {
        transactionDetail.setTransactionID(transaction);
        transactionDetail.setProductID(product);
        transactionDetail.setQuantity(quantity);
    }
}
