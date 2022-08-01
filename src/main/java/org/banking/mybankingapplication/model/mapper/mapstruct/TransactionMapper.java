package org.banking.mybankingapplication.model.mapper.mapstruct;


import org.banking.mybankingapplication.model.dto.TransactionsDTO;
import org.banking.mybankingapplication.model.entity.Transactions;
import org.hibernate.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel  = "spring")
public interface TransactionMapper {

    //TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionsDTO toDTO(Transaction transaction);

    List<TransactionsDTO> toDTO(List<Transaction> transactionList);

    Transactions toEntity(TransactionsDTO transactionsDTO);

    List<Transactions> toEntity(List<TransactionsDTO> transactionsDTOList);
}
