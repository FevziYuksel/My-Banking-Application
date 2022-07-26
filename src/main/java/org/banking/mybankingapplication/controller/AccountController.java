package org.banking.mybankingapplication.controller;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.service.AccountService;
import org.banking.mybankingapplication.service.ServiceInterface.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final IAccountService accountService;

    @GetMapping("/getAll")
    public ResponseEntity getAllAccounts(){

        List<Account> allAccount = accountService.getAllAccount();

        if(allAccount.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("There aren't any accounts created!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(allAccount);
    }
    @GetMapping("/getAllDTO")
    public ResponseEntity getAllAccountsDTO(){

        List<AccountDTO> allAccountDTO = accountService.getAllAccountDTO();

        accountService.getAllAccount();

        if(allAccountDTO.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("There aren't any accounts created!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(allAccountDTO);
    }
    @GetMapping("/findAll")
    public ResponseEntity findAllAccounts(){

        Optional<List<Account>> optionalAccounts = accountService.findAllAccount();

        //optionalAccounts.orElseThrow(() -> new RuntimeException("There aren't any accounts created!"));

//        optionalAccounts.ifPresentOrElse(
//                accounts -> ResponseEntity.status(HttpStatus.OK).body(accounts),
//                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("There aren't any accounts created!")
//        );

        return optionalAccounts.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(optionalAccounts) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("There aren't any accounts created!");

    }





}
