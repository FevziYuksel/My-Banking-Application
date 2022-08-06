package org.banking.mybankingapplication.controller;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.exception.EntityNotFoundException;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.service.AccountService;
import org.banking.mybankingapplication.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customer")

public class CustomerController {

    private final CustomerService customerService;
    private final AccountService accountService;



    @GetMapping("/all")
    public ResponseEntity getAllCustomers(){

        java.util.List<Customer> customers = customerService.getAllCustomers();
        if(customers.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any customer");


        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
    @GetMapping("/dto")
    public ResponseEntity getAllCustomersDTO(){

        List<CustomerDTO> allCustomersDTO = customerService.getAllCustomersDTO();
        if(allCustomersDTO.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any customer");

        return ResponseEntity.status(HttpStatus.OK).body(allCustomersDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getCustomerById(@PathVariable(name = "id") Long id){

//        if(id.isPresent())
//            customerById = customerService.getCustomerById(id.get());
//
        Customer customerById = customerService.getCustomerById(id);


        if(customerById == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find the customer with this id");

        return ResponseEntity.status(HttpStatus.OK).body(customerById);
    }
    @GetMapping("/{name}")
    public ResponseEntity getCustomerByName(@PathVariable String name){

        Customer checkCustomer = customerService.getCustomerByName(name);

        if(checkCustomer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user cannot be found!");

        return ResponseEntity.status(HttpStatus.OK).body(checkCustomer);
    }
    //Add duplicates problem !!!!!
    @PostMapping("/create")
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer addCustomer = customerService.createNewCustomerFromDTO(customerDTO);

        if(addCustomer == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be created..");

        return ResponseEntity.status(HttpStatus.OK).body("The customer was added successfully");
    }
    @PostMapping("/create2")
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        Customer addCustomer = customerService.createNewCustomer(customer);

        if(addCustomer == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be created..");

        return ResponseEntity.status(HttpStatus.OK).body("The customer was added successfully");
    }

    @PutMapping("/{customerId}/add/{accountId}")
    public ResponseEntity addAccountToCustomerById(@PathVariable Long customerId, @PathVariable Long accountId){
        Customer customer = customerService.addAccountToCustomerById(customerId, accountId);
        //SHOULD I CHECK EXCEPTION?????????
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    @PutMapping("/{customerId}/addEntity")
    public ResponseEntity addNewAccountToCustomerById(@PathVariable Long customerId, @RequestBody Account account){
        //Doesn't work with ID body
        Customer customer = customerService.addNewAccountToCustomer(customerId, account);
        if(customer == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The operation could not be performed.");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    @PutMapping("/{customerId}/addDTO")
    public ResponseEntity addNewAccountToCustomerById(@PathVariable Long customerId, @RequestBody AccountDTO account){
        Customer customer = customerService.addNewAccountDTOToCustomer(customerId,account);
        //SHOULD I CHECK EXCEPTION?????????
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    @PutMapping("/{customerName}")
    public ResponseEntity updateCustomerName(@PathVariable String customerName, @RequestBody CustomerDTO dto){
        Customer update = customerService.updateCustomerName(customerName, dto);
        if (update == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User could not be updated..");
        }
        return ResponseEntity.status(HttpStatus.OK).body(update);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomerById(@PathVariable("id") Long id){

        try {
            customerService.deleteCustomerById(id);
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
    }



}
