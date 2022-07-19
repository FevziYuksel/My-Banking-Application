package org.banking.mybankingapplication.model.entity;

import org.banking.mybankingapplication.model.enums.Currency;

import java.io.Serializable;

public class Account implements Serializable {

    private long id;

    private String accountName;

    private Currency currency;

    private Customer customer;




}
