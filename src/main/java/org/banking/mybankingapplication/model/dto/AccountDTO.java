package org.banking.mybankingapplication.model.dto;

import org.banking.mybankingapplication.model.enums.Currency;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

public class AccountDTO {

    private String name;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
