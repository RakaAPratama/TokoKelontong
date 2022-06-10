package com.toko.kelontong.dtos.transaction;

import com.toko.kelontong.models.TransactionDetail;
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
public class DetailHeaderDto implements Serializable {
    private Long id;
    private Long transactionId;
    private String productId;
    private Long quantity;

    public static List<DetailHeaderDto> toListStream(List<TransactionDetail> transactionDetails) {
        if (transactionDetails.isEmpty()) {
            throw new EntityNotFoundException("Data transaction detail not found");
        }

        List<DetailHeaderDto> result = new ArrayList<>();

        transactionDetails.stream().forEach(transactionDetail -> {
            result.add(setDetailHeader(transactionDetail));
        });
        return result;
    }

    public static DetailHeaderDto setDetailHeader(TransactionDetail transactionDetail) {
        return new DetailHeaderDto(
                transactionDetail.getId(),
                transactionDetail.getTransactionID().getId(),
                transactionDetail.getProductID().getName(),
                transactionDetail.getQuantity()
        );
    }
}
