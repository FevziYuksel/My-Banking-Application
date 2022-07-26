package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.banking.mybankingapplication.model.entity.base.BaseModel;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@EqualsAndHashCode
@Builder

@Entity
@Table(name = "card")
public class Card extends BaseModel {



    @ManyToOne
    private Customer cardHolder;

    @ManyToMany
    private Set<Account> cardAccounts;


}
