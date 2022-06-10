package com.toko.kelontong.services;

import com.toko.kelontong.dtos.transaction.DetailHeaderDto;
import com.toko.kelontong.dtos.transaction.DetailUpdateInsertDto;
import com.toko.kelontong.models.Product;
import com.toko.kelontong.models.Transaction;
import com.toko.kelontong.models.TransactionDetail;
import com.toko.kelontong.repositories.DetailRepository;
import com.toko.kelontong.repositories.ProductRepository;
import com.toko.kelontong.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DetailService {

    private ProductRepository productRepository;
    private TransactionRepository transactionRepository;
    private DetailRepository detailRepository;
    
    @Autowired
    public DetailService(ProductRepository productRepository, TransactionRepository transactionRepository, DetailRepository detailRepository) {
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
        this.detailRepository = detailRepository;
    }
    
    public List<DetailHeaderDto> findAllDetail() {
        return DetailHeaderDto.toListStream(detailRepository.findAll());
    }
    
    public List<DetailHeaderDto> insertDetail(DetailUpdateInsertDto newDetail) {
        Transaction transaction = transactionRepository.findById(newDetail.getTransactionId()).orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
        Product product = productRepository.findById(newDetail.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Stream.of(newDetail).forEach(field -> {
            if (field != null) {
                detailRepository.save(newDetail.insertDetail(transaction, product));
            }
        });
        return findAllDetail();
    }

    public DetailHeaderDto updateDetail(DetailUpdateInsertDto newDetail, Long id) {
        Transaction transaction = transactionRepository.findById(newDetail.getTransactionId()).orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
        TransactionDetail detail = detailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Detail not found"));
        Product product = productRepository.findById(newDetail.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Stream.of(newDetail).forEach(field -> {
            if (field != null) {
                field.setValue(detail, transaction, product);
                detailRepository.save(detail);
            }
        });
        return DetailHeaderDto.setDetailHeader(detail);
    }

    public boolean deleteDetail(Long id) {
        detailRepository.deleteById(id);
        return true;
    }
}
