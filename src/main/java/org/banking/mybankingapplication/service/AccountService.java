package org.banking.mybankingapplication.service;

import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;

import org.banking.mybankingapplication.model.mapper.mapstruct.AccountMapper;
import org.banking.mybankingapplication.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@RequiredArgsConstructor
@Service
public class AccountService  {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    //If empty return EntityNotFoundException as Response

    public List<Account> getAllAccount() {
        List<Account> allAccount = accountRepository.findAll();
        return allAccount;
    }

    public List<AccountDTO> getAllAccountDTO(){
        List<Account> allAccount = getAllAccount();
        return accountMapper.toDTO(allAccount);
    }

//    @Override
//    public Optional<List<Account>> findAllAccount() {
//        //return Optional.empty();
//        return Optional.of(accountRepository.findAll());
//    }
//
//    @Override
//    public Optional<List<AccountDTO>> findAllAccountDTO() {
//        List<Account> all = accountRepository.findAll();
//        return Optional.of(accountMapper.toDTO(all));
//    }


    public Account getAccountById(Long id) {
        Optional<Account> byId = accountRepository.findById(id);

        return byId.orElse(null);
    }


    public Account findAccountById(Long id) {
        //return Optional.empty();
        Optional<Account> byId = accountRepository.findById(id);

        return byId.orElseThrow(() -> new RuntimeException("NOT FOUND!"));

    }

}
