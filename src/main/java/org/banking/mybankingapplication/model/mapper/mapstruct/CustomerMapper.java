package org.banking.mybankingapplication.model.mapper.mapstruct;


import org.banking.mybankingapplication.model.dto.AccountDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel  = "spring")
//@Component
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer account);

    List<CustomerDTO> toDTO(List<Customer> customerList);

    Customer toEntity(CustomerDTO customerDTO);

    List<Customer> toEntity(List<CustomerDTO> customerDTOList);
}
