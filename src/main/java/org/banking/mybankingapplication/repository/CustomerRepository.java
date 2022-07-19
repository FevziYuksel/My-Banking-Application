package org.banking.mybankingapplication.repository;

import org.banking.mybankingapplication.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByCustomerName(String name);



}
