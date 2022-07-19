package org.banking.mybankingapplication.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @GetMapping
    public ResponseEntity getAllCustomer(){

        List<Customer> customers = customerService.getAllCustomer();
        if(customers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empty");
        }

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}
