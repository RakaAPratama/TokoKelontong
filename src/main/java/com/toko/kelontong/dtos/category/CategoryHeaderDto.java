package com.toko.kelontong.dtos.category;

import com.toko.kelontong.models.Category;
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
public class CategoryHeaderDto implements Serializable {
    private Long id;
    private String name;

    public static List<CategoryHeaderDto> toListStream(List<Category> categories){
        if (categories.isEmpty()) {
            throw new EntityNotFoundException("Data category not found");
        }

        List<CategoryHeaderDto> result = new ArrayList<>();

        categories.stream().forEach(category -> {
            result.add(setCategoryHeader(category));
        });
        return result;
    }

    public static CategoryHeaderDto setCategoryHeader(Category category){
        return new CategoryHeaderDto(
                category.getId(),
                category.getName()
        );
    }
}
