package org.banking.mybankingapplication.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.AccountMapper;
import org.banking.mybankingapplication.model.mapper.IAccountMapper;
import org.banking.mybankingapplication.repository.AccountRepository;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.banking.mybankingapplication.service.ServiceInterface.IAccountService;
import org.banking.mybankingapplication.service.ServiceInterface.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final IAccountMapper accountMapper;


    public List<AccountDTO> getAllAccount(){
        List<Account> all = accountRepository.findAll();
        return accountMapper.toDTO(all);
    }

    public Customer addAccountByUserName(AccountDTO accountDTO, String name){
        //name = "Fevzi";
        return new Customer();

    }
    public Account addAccount(AccountDTO accountDTO){
        Account account = accountMapper.toEntity(accountDTO);
        return accountRepository.save(account);

    }
}
