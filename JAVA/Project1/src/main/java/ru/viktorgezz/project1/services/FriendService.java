package ru.viktorgezz.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.repositories.AccountRepositories;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final AccountRepositories accountRepositories;

    @Autowired
    public FriendService(AccountRepositories accountRepositories) {
        this.accountRepositories = accountRepositories;
    }

    public List<String> findFriendsByLogin(String loginSearch) {
        return accountRepositories.findAccountsByLoginStartingWith(loginSearch)
                .stream()
                .map(Account::getLogin)
                .collect(Collectors.toList());
    }
}
