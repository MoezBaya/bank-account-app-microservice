package net.moez.accountservice;

import net.moez.accountservice.client.CustomerRestClient;
import net.moez.accountservice.entities.Account;
import net.moez.accountservice.enums.AccountType;
import net.moez.accountservice.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository, CustomerRestClient customerRestClient) {
        return args -> {
            customerRestClient.findAllCustomers().forEach(c->{
                Account account1 = Account.builder()
                    .accountId(UUID.randomUUID().toString())
                    .balance(Math.random()*80000)
                    .createdAt(LocalDate.now())
                    .currency("MAD")
                    .type(AccountType.CURRENT_ACCOUNT)
                    .customerId(c.getId())
                    .build();
            Account account2 = Account.builder()
                    .accountId(UUID.randomUUID().toString())
                    .balance(Math.random()*65421)
                    .createdAt(LocalDate.now())
                    .currency("MAD")
                    .type(AccountType.SAVING_ACCOUNT)
                    .customerId(c.getId())
                    .build();
                    accountRepository.save(account1);
                    accountRepository.save(account2);
            });
            
            
        };
    }

}
