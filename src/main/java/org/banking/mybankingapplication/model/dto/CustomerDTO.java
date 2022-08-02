package org.banking.mybankingapplication.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class CustomerDTO implements Serializable {

    private String name;

    private String surname;

    private String email;

    private String phoneNo;

    private String address;

}
