package ru.viktorgezz.project1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.viktorgezz.project1.exception.AccountNotFoundException;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.repositories.AccountRepositories;

import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;

@Component
public class CashFlowOperations {

    private final AccountRepositories accountRepositories;

    @Autowired
    public CashFlowOperations(AccountRepositories accountRepositories) {
        this.accountRepositories = accountRepositories;
    }

    public Account findOneAccount(int idAccount) {
        Optional<Account> foundAccount = accountRepositories.findById(idAccount);
        return foundAccount.orElseThrow(AccountNotFoundException::new);
    }

    public  <T> double getSumCashFlow(List<T> items, ToDoubleFunction<T> mapper) {
        return items.stream()
                .mapToDouble(mapper)
                .sum();
    }
}
