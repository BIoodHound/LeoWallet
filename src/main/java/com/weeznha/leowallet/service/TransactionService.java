package com.weeznha.leowallet.service;

import com.weeznha.leowallet.dto.TransactionDto;
import com.weeznha.leowallet.model.User;
import com.weeznha.leowallet.model.Wallet;
import com.weeznha.leowallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    @Autowired
    WalletService walletService;

    public void transferCoin(TransactionDto transactionDto){
        List<User> users = membersExist(transactionDto);
        if(Objects.nonNull(users) && walletService.senderHasBalance(transactionDto)){
            Wallet senderWallet = walletService.getWalletByUser(users.get(0));
            Wallet recipientWallet = walletService.getWalletByUser(users.get(1));

            safeTransaction(transactionDto.getBalance(), senderWallet, recipientWallet);
        }
    }

    private void safeTransaction(Integer balance, Wallet... wallets){
        Wallet senderWallet = wallets[0];
        Wallet recipientWallet = wallets[1];

        senderWallet.setLeoCoinBalance(senderWallet.getLeoCoinBalance() - balance);
        recipientWallet.setLeoCoinBalance(recipientWallet.getLeoCoinBalance() + balance);
        walletService.updateBalance(senderWallet);
        walletService.updateBalance(recipientWallet);
    }

    private List<User> membersExist(TransactionDto transactionDto){
        List<User> res = new ArrayList<>();

        User sender = memberExist(transactionDto.getSender());
        User recipient = memberExist(transactionDto.getRecipient());
        if (Objects.nonNull(sender) && Objects.nonNull(recipient)){
            res.add(sender);
            res.add(recipient);
            return res;
        }
        return null;
    }

    private User memberExist(String username){
        User member = userService.getUserByUsername(username);
        if (Objects.nonNull(member)){
            return member;
        }
        return null;
    }

}
