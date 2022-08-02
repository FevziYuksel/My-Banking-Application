package org.banking.mybankingapplication.repository;

import org.banking.mybankingapplication.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByNameContainingIgnoreCase(String name);

    Customer getByName(String name);

    Optional<Customer> findCustomerByNameAndEmail(String name, String email);

    Optional<Customer> findCustomerByNameEqualsIgnoreCaseAndAddressEqualsIgnoreCase(String name, String email);

    Optional<Customer> findById(Long id);








}
