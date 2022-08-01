package org.banking.mybankingapplication.service;

import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.exception.EntityNotFoundException;
import org.banking.mybankingapplication.model.dto.TransactionsDTO;
import org.banking.mybankingapplication.model.entity.Transactions;
import org.banking.mybankingapplication.model.mapper.mapstruct.TransactionMapper;
import org.banking.mybankingapplication.repository.TransactionsRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final TransactionsRespository transactionsRespository;
    private final TransactionMapper transactionMapper;

    public List<Transactions> getAllTransactions(){
        return transactionsRespository.findAll();
    }
    public Transactions getTransactionsById(Long id){
        Optional<Transactions> byId = transactionsRespository.findById(id);

        return byId.orElseThrow(() -> new EntityNotFoundException(String.format("Transaction not found by id : %d",id))); //ID not found exception
    }
    //Redundant
    public Transactions createTransactions(TransactionsDTO transactionsDTO){
        Transactions transactions = transactionMapper.toEntity(transactionsDTO);
        return transactionsRespository.save(transactions);

    }
    public void deleteTransationById(Long id){
        Transactions transactionsById = getTransactionsById(id);
        transactionsById = null;
        transactionsRespository.deleteById(id);
    }

}
