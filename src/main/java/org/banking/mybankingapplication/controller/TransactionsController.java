package org.banking.mybankingapplication.controller;


import lombok.RequiredArgsConstructor;
import org.banking.mybankingapplication.model.entity.Transactions;
import org.banking.mybankingapplication.service.TransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/transaction")

public class TransactionsController {

    private final TransactionsService transactionsService;


    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions(){
        List<Transactions> allTransactions = transactionsService.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(allTransactions);

    }
}
