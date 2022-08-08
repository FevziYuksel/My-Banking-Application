package org.banking.mybankingapplication.controller;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.exception.EntityNotFoundException;
import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.mapstruct.CustomerMapper;
import org.banking.mybankingapplication.service.CustomerService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customer")
//@RequestMapping(value = "/v1/customer", produces = { "application/json;**charset=UTF-8**" })

public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    @GetMapping("/all")
    public ResponseEntity getAllCustomers() {

        java.util.List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any customer");


        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/dto")
    public ResponseEntity getAllCustomersDTO() {

        List<Customer> allCustomers = customerService.getAllCustomersDTO();
        if (allCustomers.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any customer");

        List<CustomerDTO> allCustomerDTOs = allCustomers.stream().map(CUSTOMER_MAPPER::toDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allCustomerDTOs);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getCustomerById(@PathVariable(name = "id") Long id) {
        Customer customerById = customerService.getCustomerById(id);
        if (customerById == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find the customer with this id");

        return ResponseEntity.status(HttpStatus.OK).body(customerById);
    }

    @GetMapping("/{name}")
    public ResponseEntity getCustomerByName(@PathVariable String name) {

        Customer checkCustomer = customerService.getCustomerByName(name);

        if (checkCustomer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user cannot be found!");

        return ResponseEntity.ok(checkCustomer);
    }

    //Add duplicates problem !!!!!
    @PostMapping("/create")
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO) {

        Customer addCustomer = customerService
                .createNewCustomerFromDTO(CUSTOMER_MAPPER.toEntity(customerDTO));

        if (addCustomer == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be created..");
//        return ResponseEntity.created();
        return ResponseEntity.status(HttpStatus.CREATED).body(addCustomer);
    }

    @PostMapping("/create2")
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        Customer addCustomer = customerService.createNewCustomer(customer);

        if (addCustomer == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be created..");

//        return ResponseEntity.status(HttpStatus.CREATED).body("The customer was added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(addCustomer);
    }

    @PutMapping("/{customerId}/add/{accountId}")
    public ResponseEntity addAccountToCustomerById(@PathVariable Long customerId, @PathVariable Long accountId) {
        Customer customer = customerService.addAccountToCustomerById(customerId, accountId);
        //SHOULD I CHECK EXCEPTION?????????
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PutMapping("/{customerId}/addEntity")
    public ResponseEntity addNewAccountToCustomerById(@PathVariable Long customerId, @RequestBody Account account) {
        //Doesn't work with ID body
        Customer customer = customerService.addNewAccountToCustomer(customerId, account);
        if (customer == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The operation could not be performed.");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PutMapping("/{customerId}/addDTO")
    public ResponseEntity addNewAccountToCustomerById(@PathVariable Long customerId, @RequestBody AccountDTO account) {
        Customer customer = customerService.addNewAccountDTOToCustomer(customerId, account);
        //SHOULD I CHECK EXCEPTION?????????
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    // Bu işlemi body update den de yapabilirsin :)
//    @PutMapping("/{customerName}")
//    public ResponseEntity updateCustomerName(@PathVariable String customerName, @RequestBody CustomerDTO dto) {
//        Customer update = customerService.updateCustomerName(customerName, dto);
//        if (update == null) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("User could not be updated..");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(update);
//
//    }

    @PutMapping("/update")
    public ResponseEntity updateCustomerBody(@RequestBody CustomerDTO customerDTO) {
        Customer update = customerService.updateCustomerByBody(CUSTOMER_MAPPER.toEntity(customerDTO));
        if (update == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User could not be updated..");
        }
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomerById(@PathVariable("id") Long id) {

        try {
            customerService.deleteCustomerById(id);
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Customer deleted successfully");
    }

}