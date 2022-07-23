package org.banking.mybankingapplication.model.mapper;

import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper implements IAccountMapper{
    @Override
    public Account toEntity(AccountDTO accountDTO) {
        Account account = new Account(
                accountDTO.getName(),
                accountDTO.getCurrency()
        );
        return account;
    }

    @Override
    public List<Account> toEntity(List<AccountDTO> accountDTO) {
        return accountDTO.stream().map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO toDTO(Account Account) {
        return AccountDTO.builder().
                name(Account.getName()).
                currency(Account.getCurrency()).build();

    }

    @Override
    public List<AccountDTO> toDTO(List<Account> Account) {
        return Account.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
}
