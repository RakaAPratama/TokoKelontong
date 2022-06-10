package com.toko.kelontong.dtos.transaction;

import com.toko.kelontong.models.Transaction;
import lombok.*;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class TransactionHeaderDto implements Serializable {
    private Long id;
    private String customerId;

    public static List<TransactionHeaderDto> toListStream(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            throw new EntityNotFoundException("Data transaction not found");
        }

        List<TransactionHeaderDto> result = new ArrayList<>();

        transactions.stream().forEach(transaction -> {
            result.add(setTransactionHeader(transaction));
        });
        return result;
    }

    public static TransactionHeaderDto setTransactionHeader(Transaction transaction) {
        return new TransactionHeaderDto(
                transaction.getId(),
                transaction.getCustomerID().getName()
        );
    }

}
