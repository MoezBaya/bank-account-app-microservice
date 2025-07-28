package net.moez.customerservice;

import net.moez.customerservice.config.GlobalConfig;
import net.moez.customerservice.entities.Customer;
import net.moez.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {

        return args -> {
            List<Customer> customers = List.of(Customer
                    .builder()
                    .firstName("Moez")
                    .lastName("Baya")
                    .email("moez.baya@gmail.com")
                    .build(), Customer
                    .builder()
                    .firstName("Manel")
                    .lastName("saanouni")
                    .email("manel.saanouni@gmail.com")
                    .build());

            customerRepository.saveAll(customers);
        };
    }

}
