package com.weeznha.leowallet.repository;

import com.weeznha.leowallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserName(String username);
}
