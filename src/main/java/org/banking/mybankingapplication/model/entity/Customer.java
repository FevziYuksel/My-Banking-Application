package org.banking.mybankingapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.banking.mybankingapplication.model.entity.base.ExtendBase;
import org.banking.mybankingapplication.model.entity.base.Person;

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

public class Customer extends Person implements Serializable {

    @Id
    //@Column(name = "customer_id") //column cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

//    @Lob
//    @Column(columnDefinition="text")
    private String name;

//    @Lob
//    @Column(columnDefinition="text")
    private String surname;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate registerDate = LocalDate.now();

    private String password;

    private String email;

    private String phoneNo;

    private String address;


    //Cascade All -> Delete customer delete account
    //Mapped by doesn't work ??
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY),
//    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name ="customer_id", referencedColumnName = "id") //Create real customer_id column but cause stackoverflow when is called
    private List<Account> costumerAccounts;

//    @JsonIgnore
//    @OneToMany(mappedBy = "cardHolder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Card> customerCards;



}
