package org.banking.mybankingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
@SpringBootApplication
public class MyBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBankingApplication.class, args);
    }

}
