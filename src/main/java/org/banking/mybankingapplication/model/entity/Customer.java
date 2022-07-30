package org.banking.mybankingapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.banking.mybankingapplication.model.entity.base.ExtendBase;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder


@Entity
@Table(name = "customer", schema = "public")

public class Customer implements Serializable {

    @Id
    //@Column(name = "customer_id") //column cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    private String name;

    private String surname;

    private LocalDate registerDate = LocalDate.now();

    private String password;

    private String email;

    private String phoneNo;

    private String address;


    //Cascade All vs Merge ?????
    //Mapped by doesn't work ??
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY),
//    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name ="customer_id", referencedColumnName = "id") //Create real customer_id column but cause stackoverflow when is called
    private List<Account> costumerAccounts;

    @OneToMany(mappedBy = "cardHolder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Card> customerCards;


//    @JsonIgnore
//    @ManyToMany
//    private List<Company> companies;


}
