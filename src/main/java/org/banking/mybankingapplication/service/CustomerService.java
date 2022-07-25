package org.banking.mybankingapplication.service;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.CustomerMapper;
import org.banking.mybankingapplication.model.mapper.ICustomerMapper;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Customer createCustomer(CustomerDTO customerDTO) {

        Customer addCustomer = customerMapper.toEntity(customerDTO);
        //If empty ?

        try{
            return customerRepository.save(addCustomer);
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }
    public Customer getCustomerByName(String name){

        var customer = customerRepository.getByName(name); //call from user service


        return customerRepository.save(customer);

    }

    public CustomerDTO getCustomerById(Long id){

        var customer = customerRepository.findById(id);

        //Customer customer1 = customer.orElseThrow(() -> new RuntimeException("Cannot find the customer with this id"));

        return customerMapper.toDTO(customer.get());
    }




}
