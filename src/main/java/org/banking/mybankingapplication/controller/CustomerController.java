package org.banking.mybankingapplication.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.CustomerMapper;
import org.banking.mybankingapplication.model.mapper.ICustomerMapper;
import org.banking.mybankingapplication.service.CustomerService;
import org.banking.mybankingapplication.service.ServiceInterface.IAccountService;
import org.banking.mybankingapplication.service.ServiceInterface.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/Customer")

public class CustomerController {

    private final ICustomerService customerService;
    private final IAccountService accountService;



    @GetMapping("/All")
    public ResponseEntity getAllCustomers(){

        var customers = customerService.getAllCustomers();
        if(customers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any customer");
        }

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable Long id){

        CustomerDTO customerById = customerService.getCustomerDTOById(id);

        if(customerById == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find the customer with this id");

        return ResponseEntity.status(HttpStatus.OK).body(customerById);
    }
    @PostMapping("/Add")
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer addCustomer = customerService.createCustomer(customerDTO);

        return ResponseEntity.status(HttpStatus.OK).body("The customer was added successfully");
    }
    @PostMapping("/{name}")
    public ResponseEntity addAccountByUserName(@RequestBody AccountDTO accountDTO, @PathVariable String name){

        var checkCustomer = accountService.addAccountByUserName(accountDTO, name);

        if(checkCustomer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user is not found!");

        return ResponseEntity.status(HttpStatus.OK).body("The account was added to user's account ");
    }

    @PostMapping("/addAccount/{id}")
    public ResponseEntity addAccountToCustomerById(@RequestBody AccountDTO accountDTO, @PathVariable Long id){

        Customer customer = customerService.addAccountToCustomerById(accountDTO, id);

        return ResponseEntity.status(HttpStatus.OK).body(customer);

    }



}
