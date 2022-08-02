package org.banking.mybankingapplication.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.banking.mybankingapplication.model.enums.TransactionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter

public class TransactionsDTO implements Serializable {


    private BigDecimal amount ;

    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

}
