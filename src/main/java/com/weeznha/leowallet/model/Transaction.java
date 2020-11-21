package com.weeznha.leowallet.model;

import lombok.Data;

@Data
public class Transaction {
    String sender;
    String recipient;
    Integer balance;
}
