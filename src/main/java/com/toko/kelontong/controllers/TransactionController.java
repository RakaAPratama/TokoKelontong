package com.toko.kelontong.controllers;

import com.toko.kelontong.dtos.transaction.TransactionHeaderDto;
import com.toko.kelontong.dtos.transaction.TransactionUpdateInsertDto;
import com.toko.kelontong.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("find-all-transaction")
    public ResponseEntity<RestResponse<List<TransactionHeaderDto>>> findAll(){
        return new ResponseEntity(
                new RestResponse<>(
                        transactionService.findAllTransaction()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<List<TransactionHeaderDto>>> insert(@RequestBody TransactionUpdateInsertDto newTransaction){
        return new ResponseEntity(
                new RestResponse<>(
                        transactionService.insertTransaction(newTransaction)
                        , "Transaction has been inserted"
                        ,200)
                , HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<Boolean>> delete(@RequestParam Long id){
        return new ResponseEntity(
                new RestResponse<>(
                        transactionService.deleteTransaction(id)
                        , "Transaction has been deleted"
                        ,410)
                , HttpStatus.OK);
    }
}
