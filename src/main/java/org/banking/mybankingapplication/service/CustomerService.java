package org.banking.mybankingapplication.service;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){

        var customers = customerRepository.findAll();


        //Send custom response ??
        if(customers.isEmpty()){
            //throw new RuntimeException("");
        }
        return customers;
    }

}
