package org.banking.mybankingapplication.service.ServiceInterface;

import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;

import java.util.List;

public interface ICustomerService {

     void addCustomer(Customer customer);

     List<CustomerDTO> getAllCustomers();

     Customer createCustomer(CustomerDTO customerDTO);

     Customer getCustomerByName(String name);

     Customer getCustomerById(Long id);

     CustomerDTO getCustomerDTOById(Long id);

     Customer addAccountToCustomerById(AccountDTO accountDTO, Long id);




}
