package com.toko.kelontong.dtos.category;

import com.toko.kelontong.models.Category;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
public class CategoryUpdateInsertDto implements Serializable {
    private String name;

    public Category insertCategory() {
        return new Category(
                name
        );
    }

    public void setValue(Category category) {
        category.setName(name);
    }

}
