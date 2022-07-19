package org.banking.mybankingapplication.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data

public class Customer implements Serializable {

    private long id;

    private String customerName;

    //private String password;

    private String email;

    private String phoneNo;

    private String address;

    private List<Card> constumerCards;

    private List<Account> costumerAccounts;

    private List<Role> costumerRoles;


}
