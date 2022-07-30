package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.banking.mybankingapplication.model.entity.base.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString
//@EqualsAndHashCode
@Builder

@Entity
@Table(name = "card")
public class Card implements Serializable {


    @Id
    //@Column(name = "customer_id") //column cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    private String name;

    private LocalDate registerDate;


    @ManyToOne
    private Customer cardHolder;

    @ManyToMany
    private Set<Account> cardAccounts;


}
