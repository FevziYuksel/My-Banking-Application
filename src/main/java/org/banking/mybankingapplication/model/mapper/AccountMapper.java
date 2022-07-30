package org.banking.mybankingapplication.model.mapper;

import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.enums.Currency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public Account toEntity(AccountDTO accountDTO) {

        Account account = new Account();
        account.setName(accountDTO.getName());
        account.setCurrency(accountDTO.getCurrency());

        return account;
    }


    public List<Account> toEntity(List<AccountDTO> accountDTO) {
        return accountDTO.stream().map(this::toEntity)
                .collect(Collectors.toList());
    }


    public AccountDTO toDTO(Account account) {
        return AccountDTO.builder().
                name(account.getName())
                .currency(account.getCurrency()).build();

    }


    public List<AccountDTO> toDTO(List<Account> account) {
        return account.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
}
