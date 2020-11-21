package com.weeznha.leowallet.controller;

import com.weeznha.leowallet.dto.TransactionDto;
import com.weeznha.leowallet.model.Wallet;
import com.weeznha.leowallet.service.TransactionService;
import com.weeznha.leowallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import payload.response.MessageResponse;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    WalletService walletService;

    @PutMapping("api/transfer/")
    public ResponseEntity<?> transferCoin(@RequestBody TransactionDto transactionDto) throws IOException {
        try {
            transactionService.transferCoin(transactionDto);
            return ResponseEntity.ok(new MessageResponse("nice"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }
    //TODO find a way to only give balance and transaction data to the appropriate user
    //this get is temp
    @GetMapping("api/balance/{username}")
    public ResponseEntity<?> getBalance(@PathVariable("username") String username) throws IOException{
        try {
            Wallet walletByUsername = walletService.getWalletByUsername(username);
            return ResponseEntity.ok(new MessageResponse("Balance: " + walletByUsername.getLeoCoinBalance()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }

}
