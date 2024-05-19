package ru.viktorgezz.project1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ExpenseDTOResponse {
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 0, max = 150, message = "The name size cannot exceed 150 characters")
    private String title;

    @Min(value = 0, message = "Amount must be greater than 0")
    private Double amount;

    @NotEmpty(message = "The title category must not be empty.")
    private String categoryTitle;

    public ExpenseDTOResponse(String title, Double amount, String categoryTitle) {
        this.title = title;
        this.amount = amount;
        this.categoryTitle = categoryTitle;
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
