package com.weeznha.leowallet.service;

import com.weeznha.leowallet.dto.TransactionDto;
import com.weeznha.leowallet.model.Wallet;
import com.weeznha.leowallet.repository.TransactionRepository;
import com.weeznha.leowallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletService walletService;

    public void transferCoin(TransactionDto transactionDto) throws IOException {
        if(membersExist(transactionDto) && walletService.senderHasBalance(transactionDto)){
            String senderUserId = userRepository.findByUserName(transactionDto.getSender()).get().getId();
            String recipientUserId = userRepository.findByUserName(transactionDto.getRecipient()).get().getId();
            Wallet senderWallet = walletService.getWalletById(senderUserId);
            Wallet recipientWallet = walletService.getWalletById(recipientUserId);

            safeTransaction(transactionDto.getBalance(), senderWallet, recipientWallet);
        }
    }

    private void safeTransaction(Integer balance, Wallet... wallets) throws IOException {
        Wallet senderWallet = wallets[0];
        Wallet recipientWallet = wallets[1];

        senderWallet.setLeoCoinBalance(senderWallet.getLeoCoinBalance() - balance);
        recipientWallet.setLeoCoinBalance(recipientWallet.getLeoCoinBalance() + balance);
        try {
            walletService.updateBalance(senderWallet);
            walletService.updateBalance(recipientWallet);
        }catch (Exception e){
            e.getMessage();
        }
    }

    private Boolean membersExist(TransactionDto transactionDto){
        return senderExist(transactionDto.getSender()) && recipientExist(transactionDto.getRecipient());
    }

    private Boolean senderExist(String username){
        return userRepository.findByUserName(username).isPresent();
    }

    private Boolean recipientExist(String username){
        return userRepository.findByUserName(username).isPresent();
    }
}
