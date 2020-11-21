package com.weeznha.leowallet.service;

import com.weeznha.leowallet.dto.TransactionDto;
import com.weeznha.leowallet.model.User;
import com.weeznha.leowallet.model.Wallet;
import com.weeznha.leowallet.repository.UserRepository;
import com.weeznha.leowallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WalletService {
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    public Boolean senderHasBalance(TransactionDto transactionDto){
        Optional<User> userName = userRepository.findByUserName(transactionDto.getSender());
        Optional<Wallet> wallet = walletRepository.getById(userName.get().getId());
        //TODO add exception
        return wallet.get().getLeoCoinBalance() > transactionDto.getBalance();
    }
    public Boolean updateBalance(Wallet wallet) throws IOException {
        walletRepository.save(wallet);
        return true;
    }
    public Wallet getWalletById(String id){
        //TODO add exception
        List<Wallet> collect = walletRepository.findAll()
                .stream()
                .filter(wallet -> wallet.getId().equals(id)).collect(Collectors.toList());
        return collect.get(0);
    }

    public Wallet getWalletByUsername(String username){
        String id = userRepository.findByUserName(username).get().getWallet().getId();
        return getWalletById(id);
    }
}
