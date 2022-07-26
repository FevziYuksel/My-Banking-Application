package org.banking.mybankingapplication.service.ServiceInterface;

import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;

import java.util.List;

public interface ICustomerService {

     void addCustomer(Customer customer);

     public List<CustomerDTO> getAllCustomers();

     Customer createCustomer(CustomerDTO customerDTO);

     Customer getCustomerByName(String name);

     CustomerDTO getCustomerById(Long id);
}
