package org.banking.mybankingapplication.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.CustomerMapper;
import org.banking.mybankingapplication.model.mapper.ICustomerMapper;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.banking.mybankingapplication.service.ServiceInterface.IAccountService;
import org.banking.mybankingapplication.service.ServiceInterface.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service


public class CustomerService implements ICustomerService {


    private final CustomerRepository customerRepository;

    private final ICustomerMapper customerMapper;

    private final IAccountService accountService;


    public List<Customer> getAllCustomers(){

        var customers = customerRepository.findAll();
        //List<CustomerDTO> customerDTOs = customerMapper.toDTO(customers);


        //Send custom response ??
        if(customers.isEmpty()){
            //throw new RuntimeException("");
        }
        return customers;
    }
    @Override
    public void addCustomer(Customer customer){

        customerRepository.save(customer);

    }
    @Override
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
    @Override
    public Customer getCustomerByName(String name){

        var customer = customerRepository.getByName(name); //call from user service


        return customerRepository.save(customer);

    }
    @Override
    public CustomerDTO getCustomerDTOById(Long id){

        var customer = customerRepository.findById(id);

        //Customer customer1 = customer.orElseThrow(() -> new RuntimeException("Cannot find the customer with this id"));

        return customerMapper.toDTO(customer.get());
    }

    @Override
    public Customer getCustomerById(Long id){

        var customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new RuntimeException("Cannot find the customer with this id"));


    }
    @Override
    public Customer addAccountToCustomerById(Long customerId, Long accountId) {

        Customer customer = this.getCustomerById(customerId);

        Account account = accountService.findAccountById(accountId);

//        account.setCustomer(customer); //Doesn't work !

        List<Account> costumerAccounts = customer.getCostumerAccounts();

        costumerAccounts.add(account);

        return customerRepository.save(customer);



    }

}
