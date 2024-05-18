package ru.viktorgezz.project1.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.model.Expense;
import ru.viktorgezz.project1.model.Goal;
import ru.viktorgezz.project1.model.Income;
import ru.viktorgezz.project1.repositories.GoalRepositories;
import ru.viktorgezz.project1.util.CashFlowOperations;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GoalService {

    private final GoalRepositories goalRepositories;
    private final CashFlowOperations cashFlowOperations;

    public GoalService(GoalRepositories goalRepositories, CashFlowOperations cashFlowOperations) {
        this.goalRepositories = goalRepositories;
        this.cashFlowOperations = cashFlowOperations;
    }

    public double getDiffExpenseSavings(int idAccount) {
        Account foundAccount = findOneAccount(idAccount);
        double monthlySavings = foundAccount.getGoal().getMonthlySavings();
        List<Expense> expenses = foundAccount.getExpenses();

        return monthlySavings - cashFlowOperations.getSumCashFlow(expenses, Expense::getAmount);
    }

    public double getPercentExpenseSavings(int idAccount) {
        Account foundAccount = findOneAccount(idAccount);
        Goal goal = foundAccount.getGoal();
        if (goal == null) {
            return 0;
        }
        double monthlySavings = goal.getMonthlySavings();//

        List<Expense> expenses = foundAccount.getExpenses();
        List<Income> incomes = foundAccount.getIncomes();

        double cashFlow = cashFlowOperations.getSumCashFlow(incomes, Income::getProfit) -
                cashFlowOperations.getSumCashFlow(expenses, Expense::getAmount);
        if (cashFlow == 0) {
            return 0;
        }

        return (double) Math.round(10000 * monthlySavings / cashFlow) / 100;
    }

    public Account findOneAccount(int idAccount) {
        return cashFlowOperations.findOneAccount(idAccount);
    }

    public Goal findOne(int id) {
        Optional<Goal> foundGoal = goalRepositories.findById(id);
        return foundGoal.orElse(null);
    }

//    public double getDiffIncomeExpense(int idAccount) {
//        Account foundAccount = findOneAccount(idAccount);
//        List<Income> incomes = foundAccount.getIncomes();
//        List<Expense> expenses = foundAccount.getExpenses();
//
//        return getSumCashFlow(incomes, Income::getProfit) - getSumCashFlow(expenses, Expense::getAmount);
//    }

    @Transactional
    public void save(Goal goal) {
        goalRepositories.save(goal);
    }

    @Transactional
    public void update(int id, Goal updateGoal) {
        updateGoal.setId(id);
        goalRepositories.save(updateGoal);
    }

    @Transactional
    public void delete(int id) {
        goalRepositories.deleteById(id);
    }

}
