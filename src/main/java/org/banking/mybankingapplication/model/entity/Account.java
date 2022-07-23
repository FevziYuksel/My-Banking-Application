package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.banking.mybankingapplication.model.enums.Currency;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "account")

public class Account extends ExtendBase implements Serializable {

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "account_id"
            //referencedColumnName = "customer_id",

            //unique = true,
            //nullable = false
    )
    private Customer customer;



    public Account(Long id, String name, Currency currency) {
        super(id, name);
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
    }
    public Account(String name, Currency currency) {
        super(name);
        this.currency = Currency.TURKISH_LIRA;
        this.balance = BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Account{" +
                "currency=" + currency +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }


}
