package org.banking.mybankingapplication.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.enums.CardType;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter

public class CardDTO {

    private String cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardType cardType;


}
