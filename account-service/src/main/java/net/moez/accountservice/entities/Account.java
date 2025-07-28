package net.moez.accountservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.moez.accountservice.enums.AccountType;
import net.moez.accountservice.model.Customer;

import java.time.LocalDate;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String accountId;
    private double balance;
    private LocalDate createdAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;

}
