package net.moez.customerservice.repository;

import net.moez.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
