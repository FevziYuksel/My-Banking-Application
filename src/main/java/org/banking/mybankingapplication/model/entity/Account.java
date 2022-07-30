package org.banking.mybankingapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.banking.mybankingapplication.model.entity.base.ExtendBase;
import org.banking.mybankingapplication.model.enums.Currency;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@ToString
//@EqualsAndHashCode
@Builder

@Entity
@Table(name = "account")

public class Account implements Serializable {

    @Id
    //@Column(name = "customer_id") //column cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    private String name;

    private LocalDate registerDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal balance = BigDecimal.ZERO;


    @ToString.Exclude
    //   @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name ="customer_id", referencedColumnName = "id")
    private Customer customer;

    @ToString.Exclude
    @OneToMany
    private Set<Transaction> transactions;



}
