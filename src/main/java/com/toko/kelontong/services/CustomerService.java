package com.toko.kelontong.services;

import com.toko.kelontong.dtos.category.CategoryUpdateInsertDto;
import com.toko.kelontong.dtos.customer.CustomerHeaderDto;
import com.toko.kelontong.dtos.customer.CustomerUpdateInsertDto;
import com.toko.kelontong.models.Customer;
import com.toko.kelontong.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerHeaderDto> findAllCustomer(){
        return CustomerHeaderDto.toListStream(customerRepository.findAll());
    }

    public CustomerHeaderDto insertCustomer(CustomerUpdateInsertDto newCustomer) {
        Customer customer = newCustomer.insertCustomer();
        customerRepository.save(customer);
        return CustomerHeaderDto.setCustomerHeader(customer);
    }

    public CustomerHeaderDto updateCustomer(CustomerUpdateInsertDto updateCustomer, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Stream.of(updateCustomer).forEach(field -> {
            if (field != null) {
                field.setValue(customer);
                customerRepository.save(customer);
            }
        });
        return CustomerHeaderDto.setCustomerHeader(customer);
    }

    public boolean deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return true;
    }

    public Long countCustomer() {
        return customerRepository.count();
    }
}
