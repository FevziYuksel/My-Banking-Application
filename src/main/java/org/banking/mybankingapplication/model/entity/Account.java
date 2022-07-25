package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.banking.mybankingapplication.model.entity.base.ExtendBase;
import org.banking.mybankingapplication.model.enums.Currency;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private Customer customer;



    public Account(Long id, String name, Currency currency) {
        super(id, name);
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
    }
    public Account(String name, int currency) {
        super(name);
        this.currency = chooseCurrency(currency);
        this.balance = BigDecimal.ZERO;
    }

    Currency chooseCurrency(int type){

        return switch (type) {
            case 1 -> Currency.DOLLAR;
            case 2 -> Currency.EURO;
            case 3 -> Currency.TURKISH_LIRA;
            case 4 -> Currency.POUND;
            default -> throw new RuntimeException("Currency is not present in data"); //Throw custom exception
        };
    }




}
