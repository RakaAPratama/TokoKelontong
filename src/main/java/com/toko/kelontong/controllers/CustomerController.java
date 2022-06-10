package com.toko.kelontong.controllers;

import com.toko.kelontong.dtos.customer.CustomerHeaderDto;
import com.toko.kelontong.dtos.customer.CustomerUpdateInsertDto;
import com.toko.kelontong.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("find-all-customer")
    public ResponseEntity<RestResponse<List<CustomerHeaderDto>>> findAll(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        customerService.findAllCustomer()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<CustomerHeaderDto>> insert(@RequestBody CustomerUpdateInsertDto newCustomer){
        return new ResponseEntity<>(
                new RestResponse<>(
                        customerService.insertCustomer(newCustomer)
                        , "Customer has been inserted"
                        ,200)
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestResponse<CustomerHeaderDto>> update(@RequestBody CustomerUpdateInsertDto updateCustomer, @PathVariable Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        customerService.updateCustomer(updateCustomer, id)
                        , "Customer has been updated"
                        ,200)
                , HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<Boolean>> delete(@RequestParam Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        customerService.deleteCustomer(id)
                        , "Customer has been deleted"
                        ,410)
                , HttpStatus.OK);
    }

    @GetMapping("count-customer")
    public ResponseEntity<RestResponse<Long>> countCustomer(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        customerService.countCustomer()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }
}
