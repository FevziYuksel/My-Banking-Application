package org.banking.mybankingapplication.configuration;

import lombok.AllArgsConstructor;
import org.banking.mybankingapplication.model.entity.Account;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.entity.User;
import org.banking.mybankingapplication.model.enums.Currency;
import org.banking.mybankingapplication.repository.AccountRepository;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.banking.mybankingapplication.repository.UserRepository;
import org.banking.mybankingapplication.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component

@AllArgsConstructor
//public class DataLoader implements CommandLineRunner {
public class SampleDataInitializer implements ApplicationRunner {


    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final UserService userService;


//    @Override
//    public void run(String... args) throws Exception {
//
//        //Add exception ??
//
//        if (customerRepository.findAll().isEmpty()){
//
//
//            Customer fevzi = new Customer();
//            fevzi.setName("Fevzi");
//            fevzi.setSurname("Yüksel");
//            fevzi.setEmail("fevzi_fe@hotmail.com");
//            fevzi.setPhoneNo("+905312513462");
//            fevzi.setAddress("Istanbul");
//
//            Customer ahmet = new Customer();
//            ahmet.setName("Ahmet");
//            ahmet.setSurname("Yüksel");
//            ahmet.setEmail("ahmet@hotmail.com");
//            ahmet.setPhoneNo("+905312555462");
//            ahmet.setAddress("Ankara");
//
//
//            customerRepository.saveAll(List.of(fevzi, ahmet));
//
//        }
//
//        if (accountRepository.findAll().isEmpty()){
//
//            Account fevziAccount = new Account();
//
//            fevziAccount.setName("fevzi's Account");
//            fevziAccount.setCurrency(Currency.TURKISH_LIRA); //Change to string format
//
//
//            List<Account> accountList = new ArrayList<>();
//            accountList.add(fevziAccount);
//            accountList.add(new Account());
//            accountList.add(new Account());
//            accountRepository.saveAll(accountList);
//
//        }
//
//
//
//
//
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //        Role adminRole = new Role();
//        adminRole.setName("ROLE_ADMIN");
//        Role userRole = new Role();
//        userRole.setName("ROLE_USER");
//
//        // Creating ADMIN & USER roles
//        if (!roleRepository.existsByName(adminRole.getName())) {
//            roleRepository.save(adminRole);
//        }
//        if (!roleRepository.existsByName(userRole.getName())) {
//            roleRepository.save(userRole);
//        }

        // Creating a sample Admin USER
        User adminUser = new User("admin-user", "adminuser@mail.com", "pass1234");

        if(adminUser.getUsername() != null && !adminUser.getUsername().isEmpty()){
            // @NotNull && @NotEmpty = @NotBlank
        }

        if (!userRepository.existsByUsername(adminUser.getUsername())) {
            userService.signup(adminUser, true);
        }

        // Creating a sample USER
        User sampleUser = new User("sample-user", "sampleuser@mail.com", "pass1234");
        if (!userRepository.existsByUsername(sampleUser.getUsername())) {
            userService.signup(sampleUser, false);
        }
        if (customerRepository.findAll().isEmpty()){


            Customer fevzi = new Customer();
            fevzi.setName("Fevzi");
            fevzi.setSurname("Yüksel");
            fevzi.setEmail("fevzi_fe@hotmail.com");
            fevzi.setPhoneNo("+905312513462");
            fevzi.setAddress("Istanbul");

            Customer ahmet = new Customer();
            ahmet.setName("Ahmet");
            ahmet.setSurname("Yüksel");
            ahmet.setEmail("ahmet@hotmail.com");
            ahmet.setPhoneNo("+905312555462");
            ahmet.setAddress("Ankara");


            customerRepository.saveAll(List.of(fevzi, ahmet));

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
