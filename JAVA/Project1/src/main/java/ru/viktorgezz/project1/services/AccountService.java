package ru.viktorgezz.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.project1.exception.AccountNotFoundException;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.model.Expense;
import ru.viktorgezz.project1.repositories.AccountRepositories;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepositories accountRepositories;

    @Autowired
    public AccountService(AccountRepositories accountRepositories) {
        this.accountRepositories = accountRepositories;
    }

    public Account findOne(int id) {
        Optional<Account> foundAccount = accountRepositories.findById(id);
        return foundAccount.orElseThrow(AccountNotFoundException::new);
    }

    public List<Expense> findAllOwnerExpense(int id) {
        Account account = findOne(id);
        return account.getExpenses();
    }

    public Boolean findGoal(int id) {
        Account account = findOne(id);
        return account.getGoal() != null;
    }

}
