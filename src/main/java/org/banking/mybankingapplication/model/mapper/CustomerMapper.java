package org.banking.mybankingapplication.model.mapper;

import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper  {


    public Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setPassword(dto.getPassword());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNo(dto.getPhoneNo());
        customer.setAddress(dto.getAddress());

        return customer;
    }


    public List<Customer> toEntity(List<CustomerDTO> customerDTOs) {
        return customerDTOs.stream().map(this::toEntity)
                .collect(Collectors.toList());

    }



    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = CustomerDTO.builder().name(customer.getName())
                .surname(customer.getSurname()).password(customer.getPassword())
                .email(customer.getEmail()).address(customer.getAddress())
                .phoneNo(customer.getPhoneNo()).build();

        return dto;
    }


    public List<CustomerDTO> toDTO(List<Customer> customers) {
        return customers.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
