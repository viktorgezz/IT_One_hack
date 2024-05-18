package ru.viktorgezz.project1.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class IncomeDTO {

    private int accountId;

    @Column(name = "profit")
    @Min(value = 0, message = "Profit must be greater than 0")
    private Double profit;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 150, message = "The title size cannot exceed 150 characters")
    private String title;

    public IncomeDTO() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
