package net.moez.accountservice.client;

import net.moez.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@FeignClient("CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customer/{id}")
    @CircuitBreaker(name="customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable("id") Long id) ;

    @GetMapping("/customers")
    @CircuitBreaker(name="customerService" , fallbackMethod = "getAllCustomers")
    List<Customer> findAllCustomers();

    default Customer getDefaultCustomer(Long id , Exception exception){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Not Vailable");
        customer.setLastName("Not Vailable");
        customer.setEmail("Not Vailable");
        return customer;
    }
    default List<Customer> getAllCustomers(Exception exception) {
        return List.of();
    }
}
