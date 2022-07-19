package org.banking.mybankingapplication.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "customer")

public class Customer implements Serializable {


    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String customerName;

    //private String password;

    private String email;

    private String phoneNo;

    private String address;

    //private List<Card> constumerCards;

    //private List<Account> costumerAccounts;

    //private List<Role> costumerRoles;


}
