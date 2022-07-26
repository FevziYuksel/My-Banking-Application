package org.banking.mybankingapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.banking.mybankingapplication.model.enums.Currency;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
@Validated
@Entity
@Table(name = "account")

//Cannot be existed without customer fix it
public class Account implements Serializable {

    @Id
    //@Column(name = "account_id",updatable = false,nullable = false) //column cannot be named when inherited
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
//    private long id = UUID.randomUUID();
    private long id;

    private String name;


    @CreationTimestamp
    @JsonFormat( pattern = "dd-MM-yyyy" )
    private Date registerDate;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Builder.Default //Default if null lombok
    private BigDecimal balance = BigDecimal.ZERO;

    //@NotNull(message = "")
    @JsonIgnore //prevent stackoverflow

//    @ManyToOne
//    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name ="accoutnt_id", referencedColumnName = "id") //Doesn't work , doesn't create real customer_id and create separate id-id table
    private Customer customer;

//@ElementCollection(fetch = FetchType.EAGER)
//    https://stackoverflow.com/questions/36803306/should-jointable-be-specified-in-both-sides-of-a-manytomany-relationship
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_cards",
            joinColumns = {
                    @JoinColumn(name = "account_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "card_id")
            }
    )
    private List<Card> registeredCards;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //transactionsDTO.getAmount().equals(BigDecimal.ZERO)
    @JoinColumn(name ="account_id", referencedColumnName = "id")
    private List<Transactions> transactions;


}
