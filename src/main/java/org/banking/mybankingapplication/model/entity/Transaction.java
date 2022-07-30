package org.banking.mybankingapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.banking.mybankingapplication.model.entity.base.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString
//@EqualsAndHashCode
@Builder

@Entity
@Table(name = "transaction")

public class Transaction implements Serializable {

    @Id
    //@Column(name = "customer_id") //column cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    private LocalDate transactionDate = LocalDate.now();

    private BigDecimal amount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="account_id", referencedColumnName = "id")
    private Account account;

}
