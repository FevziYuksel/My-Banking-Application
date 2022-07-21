package org.banking.mybankingapplication.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {

    private String name;

    private String surname;

    private String password;

    private String email;

    private String phoneNo;

    private String address;
}
