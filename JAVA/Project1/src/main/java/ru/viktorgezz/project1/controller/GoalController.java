package ru.viktorgezz.project1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.project1.dto.GoalDTO;
import ru.viktorgezz.project1.exception.ErrorResponse;
import ru.viktorgezz.project1.exception.GoalNotCreatedException;
import ru.viktorgezz.project1.model.Goal;
import ru.viktorgezz.project1.services.AccountService;
import ru.viktorgezz.project1.services.GoalService;
import ru.viktorgezz.project1.util.ValidCashFlow;

@RestController
@RequestMapping("/goal")
public class GoalController {

    private final GoalService goalService;
    private final AccountService accountService;
    private final ValidCashFlow validCashFlow;

    @Autowired
    public GoalController(GoalService goalService, AccountService accountService, ValidCashFlow validCashFlow) {
        this.goalService = goalService;
        this.accountService = accountService;
        this.validCashFlow = validCashFlow;
    }

    @GetMapping("/get_net_savings/{idAccount}")
    public Double getNetSavings(@PathVariable int idAccount) {
        return goalService.getDiffExpenseSavings(idAccount);
    }

    @GetMapping("/get_percent_savings_expense/{idAccount}")
    public Double getNetPercentSavings(@PathVariable int idAccount) {
        return goalService.getPercentExpenseSavings(idAccount);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid GoalDTO goalDTO,
                                             BindingResult bindingResult) {
        if (accountService.findGoal(goalDTO.getAccountId())) {
            throw new GoalNotCreatedException("The goal has already been created");
        }
        validCashFlow.checkError(bindingResult);

        goalService.save(convertToGoal(goalDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        goalService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Goal convertToGoal(GoalDTO goalDTO) {
        return new Goal(
                goalService.findOneAccount(goalDTO.getAccountId()),
                goalDTO.getMonthlyIncome(),
                goalDTO.getMonthlySavings()
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExpense(GoalNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
