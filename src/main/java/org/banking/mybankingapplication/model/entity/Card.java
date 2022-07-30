package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.banking.mybankingapplication.model.entity.base.BaseModel;
import org.banking.mybankingapplication.model.enums.CardType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder

@Entity
@Table(name = "card")
public class Card implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    private LocalDate registerDate = LocalDate.now();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="card_id", referencedColumnName = "id")
    private Customer cardHolder;

    @ManyToMany(mappedBy = "registeredCards",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Account> cardAccounts;


}
