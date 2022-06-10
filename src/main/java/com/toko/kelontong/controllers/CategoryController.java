package com.toko.kelontong.controllers;

import com.toko.kelontong.dtos.category.CategoryHeaderDto;
import com.toko.kelontong.dtos.category.CategoryUpdateInsertDto;
import com.toko.kelontong.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("find-all-category")
    public ResponseEntity<RestResponse<List<CategoryHeaderDto>>> findAll(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        categoryService.findAllCategory()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<List<CategoryHeaderDto>>> insert(@RequestBody CategoryUpdateInsertDto newCategory){
        return new ResponseEntity<>(
                new RestResponse<>(
                        categoryService.insertCategory(newCategory)
                        , "Category has been inserted"
                        ,200)
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestResponse<CategoryHeaderDto>> update(@RequestBody CategoryUpdateInsertDto updateCategory, @PathVariable Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        categoryService.updateCategory(updateCategory, id)
                        , "Category has been updated"
                        ,200)
                , HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<Boolean>> delete(@RequestParam Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        categoryService.deleteCategory(id)
                        , "Category has been deleted"
                        ,410)
                , HttpStatus.GONE);
    }

    @GetMapping("count-category")
    public ResponseEntity<RestResponse<Long>> countCategory(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        categoryService.countCategory()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }
}