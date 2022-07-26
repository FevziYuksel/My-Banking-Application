package org.banking.mybankingapplication.service.ServiceInterface;

import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;

import java.util.List;

public interface IAccountService {

    List<AccountDTO> getAllAccount();

    Customer addAccountByUserName(AccountDTO accountDTO, String name);

    Account addAccount(AccountDTO accountDTO);


}
