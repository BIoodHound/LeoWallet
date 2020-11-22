package com.weeznha.leowallet.service;

import com.weeznha.leowallet.dto.TransactionDto;
import com.weeznha.leowallet.model.User;
import com.weeznha.leowallet.model.Wallet;
import com.weeznha.leowallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WalletService {
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserService userService;

    public Boolean senderHasBalance(TransactionDto transactionDto){
        User user = userService.getUserByUsername(transactionDto.getSender());
        Wallet wallet = getWalletByUser(user);
        //TODO add exception
        return wallet.getLeoCoinBalance() > transactionDto.getBalance();
    }
    public void updateBalance(Wallet wallet){
        walletRepository.save(wallet);
    }

    public Wallet getWalletByUsername(String username){
        User user = userService.getUserByUsername(username);
        return getWalletByUser(user);
    }

    public Wallet getWalletById(String id){
        return walletRepository.getById(id).get(0);
    }

    public Wallet getWalletByUser(User user){
        return getWalletById(user.getWallet().getId());
    }

    public void giftCoin(Wallet wallet, Integer giftAmount) {
        wallet.setLeoCoinBalance(wallet.getLeoCoinBalance() +  giftAmount);
        updateBalance(wallet);
    }
}
