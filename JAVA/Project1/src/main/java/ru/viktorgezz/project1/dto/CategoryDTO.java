package ru.viktorgezz.project1.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDTO {

    private int accountId;

    @NotEmpty(message = "Category should not be empty")
    @Size(min = 1, max = 150, message = "The Category size cannot exceed 150 characters")
    private String titleCategory;


    public CategoryDTO() {
    }

    public CategoryDTO(String titleCategory) {
        this.titleCategory = titleCategory;
    }

    public String getTitleCategory() {
        return titleCategory;
    }

    public void setTitleCategory(String titleCategory) {
        this.titleCategory = titleCategory;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
