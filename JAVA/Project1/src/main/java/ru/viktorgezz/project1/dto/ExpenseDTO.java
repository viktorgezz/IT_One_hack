package ru.viktorgezz.project1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ExpenseDTO {

    private int accountId;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 0, max = 150, message = "The name size cannot exceed 150 characters")
    private String title;

    @Min(value = 0, message = "Amount must be greater than 0")
    private Double amount;

    @NotEmpty(message = "The title category must not be empty.")
    private String categoryTitle;

    public ExpenseDTO() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
