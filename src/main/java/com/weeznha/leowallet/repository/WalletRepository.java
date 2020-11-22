package com.weeznha.leowallet.repository;

import com.weeznha.leowallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> getById(String id);
}
