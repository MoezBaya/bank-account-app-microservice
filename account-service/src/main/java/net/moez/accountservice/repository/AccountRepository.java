package net.moez.accountservice.repository;

import net.moez.accountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
