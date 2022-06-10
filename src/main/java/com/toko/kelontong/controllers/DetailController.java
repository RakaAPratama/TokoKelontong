package com.toko.kelontong.controllers;

import com.toko.kelontong.dtos.transaction.DetailHeaderDto;
import com.toko.kelontong.dtos.transaction.DetailUpdateInsertDto;
import com.toko.kelontong.models.TransactionDetail;
import com.toko.kelontong.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("detail")
public class DetailController {

    @Autowired
    private DetailService detailService;

    @GetMapping("find-all-detail")
    public ResponseEntity<RestResponse<List<DetailHeaderDto>>> findAll(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        detailService.findAllDetail()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<List<DetailHeaderDto>>> insert(@RequestBody DetailUpdateInsertDto newDetail){
        return new ResponseEntity<>(
                new RestResponse<>(
                        detailService.insertDetail(newDetail)
                        , "Detail has been inserted"
                        ,200)
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestResponse<DetailHeaderDto>> update(@RequestBody DetailUpdateInsertDto updateDetail, @PathVariable Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        detailService.updateDetail(updateDetail, id)
                        , "Detail has been updated"
                        ,200)
                , HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<Boolean>> delete(@RequestParam Long id){
        return new ResponseEntity<>(
                new RestResponse<>(
                        detailService.deleteDetail(id)
                        , "Detail has been deleted"
                        ,410)
                , HttpStatus.OK);
    }
}
