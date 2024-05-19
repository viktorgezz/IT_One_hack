package ru.viktorgezz.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.project1.dto.ExpenseDTO;
import ru.viktorgezz.project1.dto.ExpenseDTOResponse;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.model.Expense;
import ru.viktorgezz.project1.repositories.ExpenseRepositories;
import ru.viktorgezz.project1.util.CashFlowOperations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ExpenseService {

    private final ExpenseRepositories expenseRepositories;
    private final CashFlowOperations cashFlowOperations;


    @Autowired
    public ExpenseService(ExpenseRepositories expenseRepositories, CashFlowOperations cashFlowOperations) {
        this.expenseRepositories = expenseRepositories;
        this.cashFlowOperations = cashFlowOperations;
    }

    public Account findOneAccount(int idAccount) {
        return cashFlowOperations.findOneAccount(idAccount);
    }

    public Double getSumExpenses(int idAccount) {
        Account foundAccount = findOneAccount(idAccount);

        List<Expense> expenses = foundAccount.getExpenses();
        return cashFlowOperations.getSumCashFlow(expenses, Expense::getAmount);
    }

    public List<ExpenseDTOResponse> getListExpenseDTO(int idAccount) {
        return findOneAccount(idAccount).getExpenses()
                .stream().map(this::convertToExpenseDTO)
                .collect(Collectors.toList());
    }

    public List<Expense> getListExpense(int idAccount) {
        return findOneAccount(idAccount).getExpenses();
    }

    private ExpenseDTOResponse convertToExpenseDTO(Expense expense) {
        return new ExpenseDTOResponse(
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory().getTitle()
        );
    }

    public Expense findOne(int id) {
        Optional<Expense> foundExpense = expenseRepositories.findById(id);
        return foundExpense.orElse(null);
    }

    @Transactional
    public void save(Expense expense) {
        expense.setTime(LocalDateTime.now());
        expenseRepositories.save(expense);
    }

    @Transactional
    public void update(int id, Expense updateExpense) {
        updateExpense.setId(id);
        expenseRepositories.save(updateExpense);
    }

    @Transactional
    public void delete(int id) {
        expenseRepositories.deleteById(id);
    }
}
