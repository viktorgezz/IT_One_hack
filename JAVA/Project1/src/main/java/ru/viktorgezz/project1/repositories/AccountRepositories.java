package ru.viktorgezz.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorgezz.project1.model.Account;

import java.util.List;

@Repository
public interface AccountRepositories extends JpaRepository<Account, Integer> {
    List<Account> findAccountsByLoginStartingWith(String searchByLogin);

    Account findByLogin(String login);
}
