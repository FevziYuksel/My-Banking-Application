package org.banking.mybankingapplication.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "customer")

public class Customer implements Serializable {

    /*
    @Id
    @Column(name = "customer_id") //columb cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;


    private String customerName;

    //private String password;

    private String email;

    private String phoneNo;

    private String address;

    //private List<Card> constumerCards;

    //private List<Account> costumerAccounts;

    //private List<Role> costumerRoles;


}
