package org.banking.mybankingapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.banking.mybankingapplication.model.enums.TransactionType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
@Validated
@Entity
@Table(name = "transaction")

public class Transactions implements Serializable {

    @Id
    //@Column(name = "customer_id") //column cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    @CreationTimestamp
    @JsonFormat( pattern = "dd-MM-yyyy" )
    private LocalDate transactionDate;

    private BigDecimal amount;

    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="account_id", referencedColumnName = "id")
    private Account account;

}
