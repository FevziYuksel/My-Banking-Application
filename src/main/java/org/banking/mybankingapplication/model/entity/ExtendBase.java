package org.banking.mybankingapplication.model.entity;


import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
//@Inheritance
//@Embedded
//@Embeddable
public abstract class ExtendBase extends Base{

    private String name;

    public ExtendBase(String id, String name) {
        super(id);
        this.name = name;
    }
}
