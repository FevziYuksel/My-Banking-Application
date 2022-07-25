package org.banking.mybankingapplication.controller;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/Account")
public class AccountController {


    private final AccountService accountService;


    @GetMapping
    public ResponseEntity getAllAccount(){

        List<AccountDTO> allAccount = accountService.getAllAccount();

        if(allAccount.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("There aren't any accounts created!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(allAccount);
    }

    @PostMapping("/{name}")
    public ResponseEntity addAccount(@RequestBody AccountDTO accountDTO, @PathVariable String name){

        var checkCustomer = accountService.addAccount(accountDTO, name);

        if(checkCustomer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user is not found!");

        return ResponseEntity.status(HttpStatus.OK).body("The account was added to user's account ");
    }

}
