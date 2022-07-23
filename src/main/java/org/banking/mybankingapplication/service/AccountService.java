package org.banking.mybankingapplication.service;

import lombok.AllArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.AccountMapper;
import org.banking.mybankingapplication.repository.AccountRepository;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountMapper = accountMapper;
    }

    public List<AccountDTO> getAllAccount(){
        List<Account> all = accountRepository.findAll();
        return accountMapper.toDTO(all);
    }
    @Transactional
    public Customer addAccount(AccountDTO accountDTO, String name){
        //name = "Fevzi";
        Account newAccount = accountMapper.toEntity(accountDTO);

        Customer customer = customerService.getCustomerByName(name);

        customer.getCostumerAccounts().add(newAccount);

        System.out.println("*********************************");


        for(var v : customer.getCostumerAccounts())
            System.out.println(v);

        if(customer == null)
            return null;
        /*
        try{
            customerRepository.save(customer);
        }
        catch (Exception e){
            e.getCause();
        }

         */
        customerService.addCustomer(customer);

        //accountRepository.save(newAccount);

        //Custom exception

        return customer;

    }
}
