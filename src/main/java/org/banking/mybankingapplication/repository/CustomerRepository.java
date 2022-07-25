package org.banking.mybankingapplication.repository;

import org.banking.mybankingapplication.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByName(String name);

    Customer getByName(String name);

    Optional<Customer> findById(Long id);

    //Customer getById(Long id);

    //List<Optional<Customer>> getAll(); //Clash with .findAll()

    //Optional<Customer> getCustomerBy



}
