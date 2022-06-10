package com.toko.kelontong.services;

import com.toko.kelontong.dtos.transaction.TransactionHeaderDto;
import com.toko.kelontong.dtos.transaction.TransactionUpdateInsertDto;
import com.toko.kelontong.models.Customer;
import com.toko.kelontong.models.Transaction;
import com.toko.kelontong.repositories.CustomerRepository;
import com.toko.kelontong.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TransactionService {

    private CustomerRepository customerRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionHeaderDto> findAllTransaction() {
        return TransactionHeaderDto.toListStream(transactionRepository.findAll());
    }

    public List<TransactionHeaderDto> insertTransaction(TransactionUpdateInsertDto newTransaction) {
        Customer customer = customerRepository.findById(newTransaction.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Stream.of(newTransaction).forEach(field -> {
            if (field != null) {
                transactionRepository.save(newTransaction.insertTransaction(customer));
            }
        });
        return findAllTransaction();
    }

    public boolean deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
        return true;
    }
}
