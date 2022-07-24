package org.banking.mybankingapplication.configuration;

import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.enums.Currency;
import org.banking.mybankingapplication.repository.AccountRepository;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public void run(String... args) throws Exception {

        //Add exception ??
        if (customerRepository.findAll().isEmpty()){

            Customer fevzi = new Customer(
                    "Fevzi",
                    "Yüksel",
                    "123",
                    "fevzi_fe@hotmail.com",
                    "+905312513462",
                    "Istanbul"
            );

            customerRepository.save(fevzi);

        }
        if (accountRepository.findAll().isEmpty()){

            Account fevziAccount = new Account(
                    "fevzi",
                    Currency.TURKISH_LIRA
            );
            accountRepository.save(fevziAccount);

        }



        /*

         */

    }
}
