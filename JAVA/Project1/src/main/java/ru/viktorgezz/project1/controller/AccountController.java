package ru.viktorgezz.project1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.project1.dto.AccountDTO;
import ru.viktorgezz.project1.services.AccountService;
import ru.viktorgezz.project1.util.ValidCashFlow;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final ValidCashFlow validCashFlow;

    @Autowired
    public AccountController(AccountService accountService, ValidCashFlow validCashFlow) {
        this.accountService = accountService;
        this.validCashFlow = validCashFlow;
    }


    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid AccountDTO accountDTO,
                                             BindingResult bindingResult) {
        validCashFlow.checkError(bindingResult);

        accountService.save(
                accountDTO.getLogin(),
                accountDTO.getPassword(),
                accountDTO.getEmail()
        );
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
