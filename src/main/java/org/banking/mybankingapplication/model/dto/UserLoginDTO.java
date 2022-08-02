package org.banking.mybankingapplication.model.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
public class UserLoginDTO{

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
