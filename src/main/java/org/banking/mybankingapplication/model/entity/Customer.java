package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.banking.mybankingapplication.model.entity.base.ExtendBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder


@Entity
@Table(name = "customer", schema = "public")

public class Customer extends ExtendBase {

    private String surname;

    private String password;

    private String email;

    private String phoneNo;

    private String address;


    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    //@JoinColumn(name = "customer_id")
    private Set<Account> costumerAccounts;

//    private Set<Card> customerCards;

    //private Set<Role> costumerRoles;



    public Customer(Long id, String name, String surname, String password, String email, String phoneNo, String address) {
        super(id, name);
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.costumerAccounts = new HashSet<>();


    }
    public Customer(String name, String surname, String password, String email, String phoneNo, String address) {
        super(name);
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.costumerAccounts = new HashSet<>();

    }


}
