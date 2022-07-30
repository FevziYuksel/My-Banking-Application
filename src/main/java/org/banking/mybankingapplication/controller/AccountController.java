package org.banking.mybankingapplication.controller;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity getAllAccounts(){

        List<Account> allAccount = accountService.getAllAccount();

        return ResponseEntity.status(HttpStatus.OK).body(allAccount);
    }
    @GetMapping(path = "/all")
    public ResponseEntity getAllAccountsDTO(){

        List<AccountDTO> allAccountDTO = accountService.getAllAccountDTO();

        if(allAccountDTO.isEmpty())
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("There aren't any accounts created!");


        return ResponseEntity.status(HttpStatus.OK).body(allAccountDTO);
    }
//    @GetMapping("/findAll")
//    public ResponseEntity findAllAccounts(){
//
//        Optional<List<Account>> optionalAccounts = accountService.findAllAccount();
//
//        //optionalAccounts.orElseThrow(() -> new RuntimeException("There aren't any accounts created!"));
//
////        optionalAccounts.ifPresentOrElse(
////                accounts -> ResponseEntity.status(HttpStatus.OK).body(accounts),
////                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("There aren't any accounts created!")
////        );
//
//        return optionalAccounts.isPresent() ?
//                ResponseEntity.status(HttpStatus.OK).body(optionalAccounts) :
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body("There aren't any accounts created!");
//
//    }

    @GetMapping("/{id}")
    public ResponseEntity getAccountById(@PathVariable(name = "id") Long id){
        Account byId;
        try {
            byId = accountService.getAccountById(id);
        } catch (RuntimeException exception) {  //custom exception
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(byId);

    }

        @GetMapping("/?id= {id}")
        public ResponseEntity findAccountById(@RequestParam("id") Long id) throws RuntimeException{
            //Delay exception
            //Try catch block necessity
            //Check isPresent in service than response not found here ????
            Account byId;
            try {
                byId = accountService.findAccountById(id);
            } catch (RuntimeException exception) {  //custom exception
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(HttpStatus.OK).body(byId);

    }







}
