package org.banking.mybankingapplication.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.banking.mybankingapplication.model.enums.Currency;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountDTO {

    private String name;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
