package org.banking.mybankingapplication.model.mapper;

import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public interface ICustomerMapper {

    public Customer toEntity (CustomerDTO customerDTO);

    public List<Customer> toEntity ( List<CustomerDTO> customerDTO);

    public CustomerDTO toDTO (Customer customer);

    public List<CustomerDTO> toDTO (List<Customer> customer);
}
