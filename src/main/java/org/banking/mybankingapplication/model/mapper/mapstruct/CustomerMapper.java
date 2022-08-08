package org.banking.mybankingapplication.model.mapper.mapstruct;


import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer account);

    Customer toEntity(CustomerDTO customerDTO);

}