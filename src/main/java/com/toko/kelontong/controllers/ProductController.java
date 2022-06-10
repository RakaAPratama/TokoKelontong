package com.toko.kelontong.controllers;

import com.toko.kelontong.dtos.product.ProductHeaderDto;
import com.toko.kelontong.dtos.product.ProductUpdateInsertDto;
import com.toko.kelontong.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("find-all-product")
    public ResponseEntity<RestResponse<List<ProductHeaderDto>>> findAll(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        productService.findAllProduct()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<List<ProductHeaderDto>>> insert(@RequestBody ProductUpdateInsertDto newProduct){
        return new ResponseEntity<>(
                new RestResponse<>(
                        productService.insertProduct(newProduct)
                        , "Product has been inserted"
                        ,200)
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestResponse<ProductHeaderDto>> update(@RequestBody ProductUpdateInsertDto updateProduct, @PathVariable Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        productService.updateProduct(updateProduct, id)
                        , "Product has been updated"
                        ,200)
                , HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<Boolean>> delete(@RequestParam Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        productService.deleteProduct(id)
                        , "Product has been deleted"
                        ,410)
                , HttpStatus.OK);
    }

    @GetMapping("count-product")
    public ResponseEntity<RestResponse<Long>> countProduct(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        productService.countProduct()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }
}
