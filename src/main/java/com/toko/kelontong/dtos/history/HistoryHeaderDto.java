package com.toko.kelontong.dtos.history;

import com.toko.kelontong.models.History;
import lombok.*;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class HistoryHeaderDto implements Serializable {
    private Long id;
    private LocalDate date;
    private String productName;
    private String customerName;
    private Long quantity;

    public static List<HistoryHeaderDto> toListStream(List<History> histories) {
        if (histories.isEmpty()) {
            throw new EntityNotFoundException("Data history not found");
        }

        List<HistoryHeaderDto> result = new ArrayList<>();

        histories.stream().forEach(history -> {
            result.add(setHistoryHeader(history));
        });
        return result;
    }

    public static HistoryHeaderDto setHistoryHeader(History history) {
        return new HistoryHeaderDto(
                history.getId(),
                LocalDate.now(),
                history.getProductName(),
                history.getCustomerID().getName(),
                history.getQuantity()
        );
    }
}
