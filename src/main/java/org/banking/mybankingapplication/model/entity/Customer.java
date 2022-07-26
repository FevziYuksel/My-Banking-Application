package org.banking.mybankingapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.banking.mybankingapplication.model.entity.base.ExtendBase;
import org.banking.mybankingapplication.model.entity.base.Person;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
@Validated
@Entity
@Table(name = "customer", schema = "public")


//Keep most of the fields final/const
//Keep field types as Object/Class to make them nullable.
public class Customer extends Person implements Serializable {

    @Id
    //@Column(name = "customer_id") //column cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private Long id;

//    @Lob
//    @Column(columnDefinition="text")

    @NotEmpty(message = "Cannot be empty")
    private String name;

//    @Lob
//    @Column(columnDefinition="text")
//    @NotNull(message = "Cannot be empty")
    @NotEmpty(message = "Cannot be empty")
    private String surname;

    @CreationTimestamp
    @JsonFormat( pattern = "dd-MM-yyyy" )
    private Date registerDate;

    @Email(message = "Email format is not correct!")
    private String email;


    private String phoneNo;

    private String address;


    //Cascade All -> Delete customer delete account
    //Mapped by doesn't work ??
    //    @JsonIgnore
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name ="customer_id", referencedColumnName = "id") //Create real customer_id column but cause stackoverflow when is called
    private List<Account> costumerAccounts;

//    @JsonIgnore
//    @OneToMany(mappedBy = "cardHolder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Card> customerCards;



}
