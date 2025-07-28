package net.moez.accountservice.web;

import net.moez.accountservice.client.CustomerRestClient;
import net.moez.accountservice.entities.Account;
import net.moez.accountservice.model.Customer;
import net.moez.accountservice.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AccountRestController {

    private AccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

   

    public AccountRestController(AccountRepository accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();
        accountList.forEach(acc->{
            acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
        });
        return ResponseEntity.ok(accountList);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        return accountRepository.findById(id)
                .map(account -> {
                    Customer customer = customerRestClient.findCustomerById(account.getCustomerId());
                    account.setCustomer(customer);
                    return ResponseEntity.ok(account);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
