package org.banking.mybankingapplication.model.entity;


import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

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

    private LocalDate registerDate;

    public ExtendBase(Long id, String name) {
        super(id);
        this.name = name;
        this.registerDate = LocalDate.now();
    }

    public ExtendBase(String name) {
        super();
        this.name = name;
        this.registerDate = LocalDate.now();
    }
}
