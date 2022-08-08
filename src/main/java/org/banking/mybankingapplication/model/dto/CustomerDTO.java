package org.banking.mybankingapplication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {

    private String name;

    private String surname;

    private String email;

    private String phoneNo;

    private String address;

}
