package org.banking.mybankingapplication.service;

import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.exception.EntityNotFoundException;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.TransactionsDTO;
import org.banking.mybankingapplication.model.entity.Account;

import org.banking.mybankingapplication.model.entity.Transactions;
import org.banking.mybankingapplication.model.enums.TransactionType;
import org.banking.mybankingapplication.model.mapper.mapstruct.AccountMapper;
import org.banking.mybankingapplication.model.mapper.mapstruct.TransactionMapper;
import org.banking.mybankingapplication.repository.AccountRepository;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@RequiredArgsConstructor
@Service
public class AccountService  {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final TransactionMapper transactionMapper;

    private final TransactionsService transactionsService;


    //HTTP_GETS
    //If empty return EntityNotFoundException as Response

    public List<Account> getAllAccount() {
        List<Account> allAccount = accountRepository.findAll();
        return allAccount;
    }

    public List<AccountDTO> getAllAccountDTO(){
        List<Account> allAccount = getAllAccount();
        return accountMapper.toDTO(allAccount);
    }


    public Account getAccountById(Long id) {
        Optional<Account> byId = accountRepository.findById(id);

        return byId.orElseThrow(() -> new EntityNotFoundException(String.format("Account not found by id : %d",id)));
    }


    public Account findAccountById(Long id) {
        //return Optional.empty();
        Optional<Account> byId = accountRepository.findById(id);

        //Entity not found exception
        return byId.orElseThrow(() -> new EntityNotFoundException(String.format("Account not found by id : %d",id)));

    }
    //HTTP_POSTS
    public Account createNewAccountFromDTO(AccountDTO accountDTO) {

        Account account = accountMapper.toEntity(accountDTO);

        try{
            return accountRepository.save(account);
        }
        catch (RuntimeException e){
            throw new EntityNotFoundException(e.getMessage());
        }

    }
    //HTTP_PUTS
    public Account createTransactionByCustomerId(Long id , TransactionsDTO transactionsDTO){

        if(transactionsDTO.getAmount().equals(BigDecimal.ZERO))
            throw new RuntimeException("Unnecessary Transaction"); //Change its own exception

        Account accountById = getAccountById(id);
        Transactions transaction1 = transactionMapper.toEntity(transactionsDTO);
        //Transaction record
        accountById.getTransactions().add(transaction1);
        //Balance change
        BigDecimal newBalance = calculateNewBalance(accountById, transactionsDTO);

        //transactionsService.createTransactions(transactionsDTO); //Already created when added with account

        accountById.setBalance(newBalance);
        return accountRepository.save(accountById);

    }
    private boolean transactionCheck(TransactionType type){
        return switch(type) {
            case POSITIVE -> true;
            default -> false;
        };

    }
    protected BigDecimal calculateNewBalance(Account account, TransactionsDTO transactionsDTO){
        if(transactionsDTO.getTransactionType() == TransactionType.POSITIVE){
            return account.getBalance().add(transactionsDTO.getAmount());
        }
        //Add credit limit implementation and Insufficient amount exception
        return account.getBalance().subtract(transactionsDTO.getAmount());
    }


    //HTTP_DELETES

    public void deleteAccountById( Long id){

        //Null check
        Account accountById = findAccountById(id);

        accountById = null; //To help compiler ?

        accountRepository.deleteById(id);
    }
}
