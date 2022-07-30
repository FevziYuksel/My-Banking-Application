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
import java.util.*;


@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final IAccountMapper accountMapper;

    //If empty return EntityNotFoundException as Response
    @Override
    public List<Account> getAllAccount() {
        List<Account> allAccount = accountRepository.findAll();
        return allAccount;
    }

    public List<AccountDTO> getAllAccountDTO(){
        List<Account> allAccount = getAllAccount();
        return accountMapper.toDTO(allAccount);
    }

    @Override
    public Optional<List<Account>> findAllAccount() {
        //return Optional.empty();
        return Optional.of(accountRepository.findAll());
    }

    @Override
    public Optional<List<AccountDTO>> findAllAccountDTO() {
        List<Account> all = accountRepository.findAll();
        return Optional.of(accountMapper.toDTO(all));
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> byId = accountRepository.findById(id);

        return byId.orElse(null);
    }

    @Override
    public Account findAccountById(Long id) {
        //return Optional.empty();
        Optional<Account> byId = accountRepository.findById(id);

        return byId.orElseThrow(() -> new RuntimeException("NOT FOUND !"));

    }

}
