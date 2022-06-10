package com.toko.kelontong.services;

import com.toko.kelontong.dtos.product.ProductHeaderDto;
import com.toko.kelontong.dtos.product.ProductUpdateInsertDto;
import com.toko.kelontong.models.Category;
import com.toko.kelontong.models.Product;
import com.toko.kelontong.models.Supplier;
import com.toko.kelontong.repositories.CategoryRepository;
import com.toko.kelontong.repositories.ProductRepository;
import com.toko.kelontong.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<ProductHeaderDto> findAllProduct() {
        return ProductHeaderDto.toListStream(productRepository.findAll());
    }

    public List<ProductHeaderDto> insertProduct(ProductUpdateInsertDto newProduct) {
        Category category = categoryRepository.findById(newProduct.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        Supplier supplier = supplierRepository.findById(newProduct.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
        Stream.of(newProduct).forEach(field -> {
            if (field != null) {
                productRepository.save(newProduct.insertProduct(category, supplier));
            }
        });
        return findAllProduct();
    }

    public ProductHeaderDto updateProduct(ProductUpdateInsertDto updateProduct, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Category category = categoryRepository.findById(updateProduct.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        Supplier supplier = supplierRepository.findById(updateProduct.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

        Stream.of(updateProduct).forEach(field -> {
            if (field != null) {
                field.setValue(product, category, supplier);
                productRepository.save(product);
            }
        });
        return ProductHeaderDto.setProductHeader(product);
    }

    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    public Long countProduct(){
        return productRepository.count();
    }
}
