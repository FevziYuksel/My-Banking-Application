package org.banking.mybankingapplication.model.dto;

import lombok.Data;
import org.banking.mybankingapplication.model.entity.Role;

import java.io.Serializable;
import java.util.List;


@Data
public class UserResponseDTO  {

    private Integer id;
    private String username;
    private String email;
    private List<Role> roles;

}
