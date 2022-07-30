package org.banking.mybankingapplication.configuration;

import lombok.AllArgsConstructor;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.enums.Currency;
import org.banking.mybankingapplication.repository.AccountRepository;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component

@AllArgsConstructor
public class DataLoader implements CommandLineRunner {


    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;


    @Override
    public void run(String... args) throws Exception {

        //Add exception ??

        if (customerRepository.findAll().isEmpty()){


            Customer fevzi = new Customer();
            fevzi.setName("Fevzi");
            fevzi.setSurname("Yüksel");
            fevzi.setPassword("123");
            fevzi.setEmail("fevzi_fe@hotmail.com");
            fevzi.setPhoneNo("+905312513462");
            fevzi.setAddress("Istanbul");

            customerRepository.save(fevzi);

        }

        if (accountRepository.findAll().isEmpty()){

            Account fevziAccount = new Account();

            fevziAccount.setName("fevzi's Account");
            fevziAccount.setCurrency(Currency.TURKISH_LIRA); //Change to string format


            List<Account> accountList = new ArrayList<>();
            accountList.add(fevziAccount);
            accountList.add(new Account());
            accountList.add(new Account());
            accountRepository.saveAll(accountList);

        }





    }
}
