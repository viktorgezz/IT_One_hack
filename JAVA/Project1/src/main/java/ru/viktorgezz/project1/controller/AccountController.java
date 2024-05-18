package ru.viktorgezz.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/get/{id}")
    public Account getAccount(@PathVariable int id) {
        return accountService.findOne(id);
    }

}
