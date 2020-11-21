package com.weeznha.leowallet.service;

import com.weeznha.leowallet.dto.UserDto;
import com.weeznha.leowallet.dto.WalletDto;
import com.weeznha.leowallet.model.Wallet;
import com.weeznha.leowallet.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
    WalletRepository walletRepository;
    /**
    public Wallet createWallet(WalletDto walletDto, ){
        Wallet wallet = new Wallet();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(walletDto, wallet);
        walletRepository.save(wallet);
        return wallet;
    }
     */
}
