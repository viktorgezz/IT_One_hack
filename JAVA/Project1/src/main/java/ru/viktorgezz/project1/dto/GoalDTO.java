package ru.viktorgezz.project1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class GoalDTO {

    private int accountId;

    @Min(value = 0, message = "Income must be greater than 0")
    private Double monthlyIncome;

    @Min(value = 0, message = "Monthly savings must be greater than 0")
    private Double monthlySavings;

    public GoalDTO() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Double getMonthlySavings() {
        return monthlySavings;
    }

    public void setMonthlySavings(Double monthlySavings) {
        this.monthlySavings = monthlySavings;
    }
}
