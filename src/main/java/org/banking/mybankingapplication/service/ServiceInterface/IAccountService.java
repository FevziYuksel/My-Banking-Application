package org.banking.mybankingapplication.service.ServiceInterface;

import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

    List<Account> getAllAccount();
    List<AccountDTO> getAllAccountDTO();

//    Optional<List<Account>> findAllAccount();
//    Optional<List<AccountDTO>> findAllAccountDTO();

    Account getAccountById(Long id);

    Account findAccountById(Long id);








}
