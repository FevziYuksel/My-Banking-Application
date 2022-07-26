package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.banking.mybankingapplication.model.entity.base.ExtendBase;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Account> costumerAccounts;

    @ManyToMany
     private Set<Card> customerCards;

    @ManyToMany
    private Set<Role> costumerRoles;



    public Customer(Long id, String name, String surname, String password, String email, String phoneNo, String address) {
        super(id, name);
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
//        this.costumerAccounts = new HashSet<>();
        this.costumerAccounts = new ArrayList<>();


    }
    public Customer(String name, String surname, String password, String email, String phoneNo, String address) {
        super(name);
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
//        this.costumerAccounts = new HashSet<>();
        this.costumerAccounts = new ArrayList<>();

    }


}
