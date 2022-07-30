package org.banking.mybankingapplication.service;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.AccountMapper;
import org.banking.mybankingapplication.model.mapper.CustomerMapper;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service

//Turn return types to DTO /CustomResponseClass
public class CustomerService  {


    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final AccountMapper accountMapper;

    private final AccountService accountService;


    //HTTP_GETS
    public List<Customer> getAllCustomers(){

        List<Customer> customers = customerRepository.findAll();
        //List<CustomerDTO> customerDTOs = customerMapper.toDTO(customers);


        //Send custom response ??
        if(customers.isEmpty()){
            //throw new RuntimeException("");
        }
        return customers;
    }

    public Customer getCustomerByName(String name){



        java.util.Optional<Customer> customer = customerRepository.findByNameContainingIgnoreCase(name); //call from user service

        return customer.orElseThrow(RuntimeException::new);

    }

    public CustomerDTO getCustomerDTOById(Long id){

        Optional<Customer> customer = customerRepository.findById(id);

        //Customer customer1 = customer.orElseThrow(() -> new RuntimeException("Cannot find the customer with this id"));

        return customerMapper.toDTO(customer.get());
    }


    public Customer getCustomerById(Long id){

        java.util.Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new RuntimeException("Cannot find the customer with this id"));


    }


    //HTTP_POSTS

    public Customer createNewCustomerFromDTO(CustomerDTO customerDTO) {

        Customer addCustomer = customerMapper.toEntity(customerDTO);
        //If empty ?

        try{
            return customerRepository.save(addCustomer);
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public Customer createNewCustomer(Customer customer) {
        return new Customer();
    }

    //HTTP_PUTS

    public Customer addAccountToCustomerById(Long customerId, Long accountId) {

        Customer customer = this.getCustomerById(customerId);

        Account account = accountService.findAccountById(accountId);

//        account.setCustomer(customer); //Doesn't work !

        List<Account> costumerAccounts = customer.getCostumerAccounts();

        costumerAccounts.add(account);

        return customerRepository.save(customer);



    }
    public Customer addNewAccountToCustomer(Long customerId, Account account) {

        Customer customer = this.getCustomerById(customerId);

        List<Account> costumerAccounts = customer.getCostumerAccounts();

        costumerAccounts.add(account);

        return customerRepository.save(customer);

    }
    public Customer addNewAccountDTOToCustomer(Long customerId, AccountDTO dto) {

        Customer customer = this.getCustomerById(customerId);

        List<Account> costumerAccounts = customer.getCostumerAccounts();

        Account account = accountMapper.toEntity(dto);

        costumerAccounts.add(account);

        return customerRepository.save(customer);


    }

    //HTTP_DELETES

    public void deleteCustomerById( Long id){
        //getCustomerById(id);
        customerRepository.deleteById(id);
    }


}
