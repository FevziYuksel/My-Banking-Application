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

@ToString
//@EqualsAndHashCode
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


    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name ="account_id", referencedColumnName = "id")
    private List<Account> costumerAccounts;

    @ManyToMany
    @ToString.Exclude
    private Set<Card> customerCards;


//    @JsonIgnore
//    @ManyToMany
//    @ToString.Exclude
//    private List<Company> companies;


}
