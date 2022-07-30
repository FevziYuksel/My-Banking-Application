package org.banking.mybankingapplication.model.entity.base;


import lombok.*;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public abstract class ExtendBase extends BaseModel {

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
