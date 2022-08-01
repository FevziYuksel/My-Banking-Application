package org.banking.mybankingapplication.repository;

import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRespository extends JpaRepository<Transactions,Long> {


}
