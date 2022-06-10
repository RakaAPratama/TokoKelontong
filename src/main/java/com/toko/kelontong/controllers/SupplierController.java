package com.toko.kelontong.controllers;

import com.toko.kelontong.dtos.supplier.SupplierHeaderDto;
import com.toko.kelontong.dtos.supplier.SupplierUpdateInsertDto;
import com.toko.kelontong.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("find-all-supplier")
    public ResponseEntity<RestResponse<List<SupplierHeaderDto>>> findAll(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        supplierService.findAllSupplier()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<SupplierHeaderDto>> insert(@RequestBody SupplierUpdateInsertDto newSupplier){
        return new ResponseEntity<>(
                new RestResponse<>(
                        supplierService.insertSupplier(newSupplier)
                        , "Supplier has been inserted"
                        ,200)
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestResponse<SupplierHeaderDto>> update(@RequestBody SupplierUpdateInsertDto updateSupplier, @PathVariable Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        supplierService.updateSupplier(updateSupplier, id)
                        , "Supplier has been updated"
                        ,200)
                , HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<Boolean>> delete(@RequestParam Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        supplierService.deleteSupplier(id)
                        , "Supplier has been deleted"
                        ,410)
                , HttpStatus.OK);
    }

    @GetMapping("count-supplier")
    public ResponseEntity<RestResponse<Long>> countSupplier(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        supplierService.countSupplier()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }
}
