package com.toko.kelontong.services;

import com.toko.kelontong.dtos.category.CategoryHeaderDto;
import com.toko.kelontong.dtos.category.CategoryUpdateInsertDto;
import com.toko.kelontong.models.Category;
import com.toko.kelontong.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryHeaderDto> findAllCategory(){
        return CategoryHeaderDto.toListStream(categoryRepository.findAll());
    }

    public List<CategoryHeaderDto> insertCategory(CategoryUpdateInsertDto newCategory){
        Stream.of(newCategory).forEach(field -> {
            if (field != null) {
                categoryRepository.save(newCategory.insertCategory());
            }
        });
        return findAllCategory();
    }

    public CategoryHeaderDto updateCategory(CategoryUpdateInsertDto updateCategory, Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Stream.of(updateCategory).forEach(field -> {
            if (field != null) {
                field.setValue(category);
                categoryRepository.save(category);
            }
        });
        return CategoryHeaderDto.setCategoryHeader(category);
    }

    public boolean deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return true;
    }

    public Long countCategory(){
        return categoryRepository.count();
    }
}
