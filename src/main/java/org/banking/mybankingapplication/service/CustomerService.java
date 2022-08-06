package org.banking.mybankingapplication.service;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.exception.DuplicateEntityException;
import org.banking.mybankingapplication.exception.EntityNotFoundException;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.mapstruct.AccountMapper;
import org.banking.mybankingapplication.model.mapper.mapstruct.CustomerMapper;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service

//Turn return types to DTO /CustomResponseClass
public class CustomerService  {


    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final CustomerMapper customerMapper;



    //HTTP_GETS
    public List<Customer> getAllCustomers(){

        List<Customer> customers = customerRepository.findAll();
        //List<CustomerDTO> customerDTOs = customerMapper.toDTO(customers);

        if(customers.isEmpty()){

            throw new EntityNotFoundException("No customer found");
        }

        return customers;
    }
    public List<CustomerDTO> getAllCustomersDTO(){

        List<Customer> customers = customerRepository.findAll();


        if(customers.isEmpty()){

            throw new EntityNotFoundException("No customer found");
        }
        return  customerMapper.toDTO(customers);
    }

    public Customer getCustomerByName(String name){



        java.util.Optional<Customer> customer = customerRepository.findByNameContainingIgnoreCase(name); //call from user service

        return customer.orElseThrow(
                () -> new EntityNotFoundException(String.format("Customer named %s found  ",name))
        );

    }

    public CustomerDTO getCustomerDTOById(Long id){

        Optional<Customer> customer = customerRepository.findById(id);

        Customer customer1 = customer.orElseThrow(
                () -> new EntityNotFoundException(String.format("Customer not found by id : %d",id)));

        return customerMapper.toDTO(customer1);
    }


    public Customer getCustomerById(Long id){

        Optional<Customer> customer = customerRepository.findById(id);
        return  customer.orElseThrow(
                () -> new EntityNotFoundException(String.format("Customer not found by id : %d", id)));
    }


    //HTTP_POSTS

    public Customer createNewCustomer(Customer customer) {


        customerRepository.findCustomerByNameAndEmail(
                customer.getName(),customer.getEmail()).ifPresent(
                s-> {
                    throw new DuplicateEntityException("Customer is already entered");
                });

        try{
            return customerRepository.save(customer);
        }
        catch (RuntimeException e){
            throw new EntityNotFoundException(e.getMessage());
        }

    }

    public Customer createNewCustomerFromDTO(CustomerDTO customerDTO) {

        Customer addCustomer = customerMapper.toEntity(customerDTO);
        //If empty ?

//        customerRepository.findAll().stream().filter(
//                customer -> customer.getName().equalsIgnoreCase(customerDTO.getName()) &&
//                customer.getEmail().equalsIgnoreCase(customerDTO.getEmail())
//                ).findAny().ifPresent( s -> { //There has to be parameter
//            throw new DuplicateEntityException("Customer is already entered");
//        });

        customerRepository.findCustomerByNameAndEmail(
                customerDTO.getName(),customerDTO.getEmail()).ifPresent(
                s-> {
                    throw new DuplicateEntityException("Customer is already entered");
                });

        try{
            return customerRepository.save(addCustomer);
        }
        catch (RuntimeException e){
            throw new EntityNotFoundException(e.getMessage());
        }

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
    public Customer updateCustomerName(String customerName, CustomerDTO dto) {

        Optional<Customer> customer = customerRepository.findByNameContainingIgnoreCase(customerName);
        if(customer.isEmpty())  //!customer.isPresent() == customer.isEmpty()
            throw new EntityNotFoundException("Customer name : " + customerName);
        Customer customer1 = customer.get();
        if (!StringUtils.isEmpty(dto.getName())) {
            customer1.setName(dto.getName());
        }
        if (!StringUtils.isEmpty(dto.getSurname())) {
            customer1.setSurname(dto.getSurname());
        }
        return customerRepository.save(customer1);
    }


    //HTTP_DELETES

    public void deleteCustomerById( Long id){
        getCustomerById(id); //In order to check id record
        customerRepository.deleteById(id);
    }


}
