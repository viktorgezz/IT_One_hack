package ru.viktorgezz.project1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.project1.dto.ExpenseDTO;
import ru.viktorgezz.project1.dto.ExpenseDTOResponse;
import ru.viktorgezz.project1.model.Expense;
import ru.viktorgezz.project1.repositories.CategoryRepositories;
import ru.viktorgezz.project1.services.ExpenseService;
import ru.viktorgezz.project1.util.ValidCashFlow;

import java.util.List;


@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final CategoryRepositories categoryRepositories;
    private final ValidCashFlow cashFlow;

    @Autowired
    public ExpenseController(ExpenseService expenseService, CategoryRepositories categoryRepositories, ValidCashFlow cashFlow) {
        this.expenseService = expenseService;
        this.categoryRepositories = categoryRepositories;
        this.cashFlow = cashFlow;
    }

    @GetMapping("/get_total_amount/{idAccount}")
    public Double getTotalAmount(@PathVariable int idAccount) {
        return expenseService.getSumExpenses(idAccount);
    }

    @GetMapping("/get_expenses/{idAccount}")
    public List<ExpenseDTOResponse> getExpenses(@PathVariable int idAccount) {
        return expenseService.getListExpenseDTO(idAccount);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ExpenseDTO expenseDTO,
                                             BindingResult bindingResult) {
        cashFlow.checkError(bindingResult);

        expenseService.save(convertToExpense(expenseDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        expenseService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Expense convertToExpense(ExpenseDTO expenseDTO) {
        return new Expense(
                expenseService.findOneAccount(expenseDTO.getAccountId()),
                categoryRepositories.findCategoriesByTitleEquals(expenseDTO.getCategoryTitle()),
                expenseDTO.getTitle(),
                expenseDTO.getAmount()
        );
    }
}
