package org.banking.mybankingapplication.model.mapper;

import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;

import java.util.List;

public interface IAccountMapper {

    public Account toEntity (AccountDTO AccountDTO);

    public List<Account> toEntity (List<AccountDTO> AccountDTO);

    public AccountDTO toDTO (Account Account);

    public List<AccountDTO> toDTO (List<Account> Account);
}
