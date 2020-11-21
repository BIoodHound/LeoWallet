package com.weeznha.leowallet.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "leo_coin_balance")
    private Integer leoCoinBalance;

    @OneToOne(mappedBy = "wallet")
    private User user;

}
