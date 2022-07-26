package org.banking.mybankingapplication.model.entity;

import org.banking.mybankingapplication.model.entity.base.BaseModel;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Transaction extends BaseModel {

    @ManyToOne
    private Account account;

}
