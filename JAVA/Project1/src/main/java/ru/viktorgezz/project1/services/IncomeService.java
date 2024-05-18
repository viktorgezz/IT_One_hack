package ru.viktorgezz.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.project1.model.Account;
import ru.viktorgezz.project1.model.Income;
import ru.viktorgezz.project1.repositories.IncomeRepositories;
import ru.viktorgezz.project1.util.CashFlowOperations;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class IncomeService {

    private final IncomeRepositories incomeRepositories;
    private final CashFlowOperations cashFlowOperations;

    @Autowired
    public IncomeService(IncomeRepositories incomeRepositories, CashFlowOperations cashFlowOperations) {
        this.incomeRepositories = incomeRepositories;
        this.cashFlowOperations = cashFlowOperations;
    }

    public double getSumIncome(int idAccount) {
        Account foundAccount = findOneAccount(idAccount);

        List<Income> incomes = foundAccount.getIncomes();
        return cashFlowOperations.getSumCashFlow(incomes, Income::getProfit);
    }

    public Account findOneAccount(int idAccount) {
        return cashFlowOperations.findOneAccount(idAccount);
    }


    @Transactional
    public void save(Income income) {
        income.setTime(LocalDateTime.now());
        incomeRepositories.save(income);
    }

    @Transactional
    public void update(int id, Income updateIncome) {
        updateIncome.setId(id);
        incomeRepositories.save(updateIncome);
    }

    @Transactional
    public void delete(int id) {
        incomeRepositories.deleteById(id);
    }
}
