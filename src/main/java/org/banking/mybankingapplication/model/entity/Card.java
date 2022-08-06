package org.banking.mybankingapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.banking.mybankingapplication.model.entity.base.BaseModel;
import org.banking.mybankingapplication.model.enums.CardType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
@Validated
@Entity
@Table(name = "card")
public class Card implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    @Builder.Default
    private String cardNo = Double.toString(Math.random());

    @Enumerated(value = EnumType.STRING)
    private CardType cardType;
    @CreationTimestamp
    @JsonFormat( pattern = "dd-MM-yyyy" )
    private Date registerDate;

//@ElementCollection(fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "registeredCards",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Account> cardAccounts;


    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name ="card_id", referencedColumnName = "id")
//    private Customer cardHolder;

}
