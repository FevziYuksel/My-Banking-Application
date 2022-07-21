package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "customer")



public class Customer extends ExtendBase {

    private String surname;

    private String password;

    private String email;

    private String phoneNo;

    private String address;




    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Account> costumerAccounts;

    //private List<Card> customerCards;

    //private List<Role> costumerRoles;


    public Customer(String id, String name, String surname, String password, String email, String phoneNo, String address) {
        super(id, name);
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;


    }
    public Customer(String name, String surname, String password, String email, String phoneNo, String address) {
        super(name);
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;

    }

}
