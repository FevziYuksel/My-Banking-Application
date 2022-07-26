package org.banking.mybankingapplication.model.dto;

import lombok.*;
import org.banking.mybankingapplication.model.enums.Currency;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter

public class AccountDTO implements Serializable {


    private String name;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal balance;


}
