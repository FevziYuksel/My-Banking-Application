package org.banking.mybankingapplication.service;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.CustomerMapper;
import org.banking.mybankingapplication.model.mapper.ICustomerMapper;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service

public class CustomerService {


    private final CustomerRepository customerRepository;

    //private ICustomerMapper iCustomerMapper;
    private final CustomerMapper customerMapper;

    public List<CustomerDTO> getAllCustomers(){

        var customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = customerMapper.toDTO(customers);


        //Send custom response ??
        if(customerDTOs.isEmpty()){
            //throw new RuntimeException("");
        }
        return customerDTOs;
    }

    public void addCustomer(Customer customer){

        customerRepository.save(customer);

    }
    public Customer getCustomerByName(String name){

        var customer = customerRepository.getByName(name); //call from user service


        return customerRepository.save(customer);

    }
/*
    public CustomerDTO getCustomerById(Long id){

        var customers = customerRepository.getAll();

        return customerMapper.toDTO(customer);
    }

 */


}
