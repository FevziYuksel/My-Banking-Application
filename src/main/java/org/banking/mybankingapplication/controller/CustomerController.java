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
@RequestMapping("/v1/customer")

public class CustomerController {

    private final ICustomerService customerService;
    private final IAccountService accountService;



    @GetMapping("/all")
    public ResponseEntity getAllCustomers(){

        var customers = customerService.getAllCustomers();
        if(customers.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any customer");


        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable Long id){

        CustomerDTO customerById = customerService.getCustomerDTOById(id);

        if(customerById == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find the customer with this id");

        return ResponseEntity.status(HttpStatus.OK).body(customerById);
    }
    @PostMapping("/add")
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer addCustomer = customerService.createCustomer(customerDTO);

        return ResponseEntity.status(HttpStatus.OK).body("The customer was added successfully");
    }
    @PostMapping("/{name}")
    public ResponseEntity addAccountByUserName(@RequestBody AccountDTO accountDTO, @PathVariable String name){

        Customer checkCustomer = null;

        if(checkCustomer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user is not found!");

        return ResponseEntity.status(HttpStatus.OK).body("The account was added to user's account ");
    }

    @PostMapping("/addAccount/{id}")
    public ResponseEntity addAccountToCustomer2(@RequestBody AccountDTO accountDTO, @PathVariable Long id){

        return ResponseEntity.notFound().build();

    }
    @PutMapping("/{customerId}/add/{accountId}")
    public ResponseEntity addAccountToCustomerById(@PathVariable Long customerId, @PathVariable Long accountId){
        Customer customer = customerService.addAccountToCustomerById(customerId, accountId);
        //SHOULD I CHECK EXCEPTION?????????
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
//    @PutMapping("/{customerId}/add/{accountId}")
//    public ResponseEntity addNewAccountToCustomerById(@PathVariable Long customerId, @RequestBody Account account){
//        //Customer customer = customerService.addAccountToCustomerById(customerId, accountId);
//        //SHOULD I CHECK EXCEPTION?????????
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }



}
