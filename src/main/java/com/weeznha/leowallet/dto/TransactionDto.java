package com.weeznha.leowallet.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String sender;
    private String recipient;
    private Integer balance;
}
