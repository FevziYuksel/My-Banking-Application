package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter

@NoArgsConstructor
@MappedSuperclass



public abstract class Base implements Serializable {


    @Id
    //@Column(name = "customer_id") //columb cannot be named
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cannot generate unique id
    private long id;

    public Base(long id) {
        this.id = id;
    }
    /*
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

        public Base(String id) {
        this.id = id;
    }

 */


}
