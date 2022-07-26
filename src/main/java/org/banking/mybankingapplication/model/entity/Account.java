package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.banking.mybankingapplication.model.entity.base.ExtendBase;
import org.banking.mybankingapplication.model.enums.Currency;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@EqualsAndHashCode
@Builder

@Entity
@Table(name = "account")

public class Account extends ExtendBase {

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal balance;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(
            name ="customer_id",
            referencedColumnName = "id"
    )
    private Customer customer;

    @OneToMany
    private Set<Transaction> transactions;


    public Account(Long id, String name, Currency currency) {
        super(id, name);
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
    }
    public Account(String name, Currency currency) {
        super(name);
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
    }
    public Account(Long id, String name, int num) {
        super(id, name);
        this.currency = Currency.intToEnum(num);
        this.balance = BigDecimal.ZERO;
    }
    public Account(String name, int num) {
        super(name);
        this.currency = Currency.intToEnum(num);
        this.balance = BigDecimal.ZERO;
    }

}
