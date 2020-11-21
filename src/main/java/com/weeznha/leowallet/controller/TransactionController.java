package com.weeznha.leowallet.controller;

import com.weeznha.leowallet.dto.TransactionDto;
import com.weeznha.leowallet.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class TransactionController {
    @PutMapping("api/transfer/")
    public ResponseEntity<?> tranferCoin(@RequestBody TransactionDto transactionDto){

        return null;
    }

}
