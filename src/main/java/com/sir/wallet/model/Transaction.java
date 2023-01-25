package com.sir.wallet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Wallet wallet;

    private long amount;
    private String type;

    public Transaction(Wallet wallet, int i, String normal) {
    }

    // Constructors, getters, and setters

}
