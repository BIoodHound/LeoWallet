package com.weeznha.leowallet.controller;

import com.weeznha.leowallet.dto.TransactionDto;
import com.weeznha.leowallet.model.Wallet;
import com.weeznha.leowallet.service.TransactionService;
import com.weeznha.leowallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.response.MessageResponse;

@CrossOrigin(origins = "*")
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    WalletService walletService;

    @PutMapping("api/transfer/")
    public ResponseEntity<?> transferCoin(@RequestBody TransactionDto transactionDto){
        try {
            transactionService.transferCoin(transactionDto);
            return ResponseEntity.ok(new MessageResponse("nice"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }
    //TODO find a way to only give balance and transaction data to the appropriate user
    //UserDetails
    @GetMapping("api/balance/{username}")
    public ResponseEntity<?> getBalance(@PathVariable("username") String username){
        System.out.println(username);
        try {
            Wallet walletByUsername = walletService.getWalletByUsername(username);
            return ResponseEntity.ok(new MessageResponse("Balance: " + walletByUsername.getLeoCoinBalance()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }
    @PutMapping("api/god_send/{username}/{amount}")
    public ResponseEntity<?> sendGift(@PathVariable("username") String username, @PathVariable("amount") Integer amount){
        try {
            Wallet walletByUsername = walletService.getWalletByUsername(username);
            walletService.giftCoin(walletByUsername, amount);
            return ResponseEntity.ok(new MessageResponse("New Balance: " + walletByUsername.getLeoCoinBalance()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }

}
