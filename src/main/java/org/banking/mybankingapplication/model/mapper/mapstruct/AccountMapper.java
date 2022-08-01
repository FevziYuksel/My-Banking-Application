package org.banking.mybankingapplication.model.mapper.mapstruct;


import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Mapper(componentModel  = "spring")
//@Component
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO toDTO(Account account);

    List<AccountDTO> toDTO(List<Account> accountList);

    Account toEntity(AccountDTO accountDTO);

    List<Account> toEntity(List<AccountDTO> accountDTOList);
}
