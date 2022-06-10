package com.toko.kelontong.controllers;

import com.toko.kelontong.dtos.history.HistoryHeaderDto;
import com.toko.kelontong.dtos.history.HistoryUpdateInsertDto;
import com.toko.kelontong.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping("find-all-history")
    public ResponseEntity<RestResponse<List<HistoryHeaderDto>>> findAll(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        historyService.findAllHistory()
                        , "Data has been found"
                        ,200)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<HistoryHeaderDto>> insert(@RequestBody HistoryUpdateInsertDto newHistory){
        return new ResponseEntity(
                new RestResponse<>(
                        historyService.insertHistory(newHistory)
                        , "History has been inserted"
                        ,200)
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestResponse<HistoryHeaderDto>> update(@RequestBody HistoryUpdateInsertDto updateHistory, @PathVariable Long id) {
        return new ResponseEntity<>(
                new RestResponse<>(
                        historyService.updateHistory(updateHistory, id)
                        , "History has been updated"
                        , 200)
                , HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<Boolean>> delete(@RequestParam Long id){
        return new ResponseEntity(
                new RestResponse<>(
                        historyService.deleteHistory(id)
                        , "History has been deleted"
                        ,410)
                , HttpStatus.OK);
    }
}
