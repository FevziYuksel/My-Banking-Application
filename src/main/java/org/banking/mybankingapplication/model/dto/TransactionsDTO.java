package org.banking.mybankingapplication.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.banking.mybankingapplication.model.enums.TransactionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;


@Getter
@Setter

public class TransactionsDTO {


    private BigDecimal amount ;

    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

}
