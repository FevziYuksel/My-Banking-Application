package org.banking.mybankingapplication.model.entity.base;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data

@NoArgsConstructor
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Embedded
//@Embeddable


public abstract class BaseModel implements Serializable {

    @Id
    //@Column(name = "customer_id") //column cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    public BaseModel(long id) {
        this.id = id;
    }

/**    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

        public Base(String id) {
        this.id = id;
    }*/




}
