package ru.viktorgezz.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    private String title;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account ownerCategory;

    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;

    public Category() {
    }

    public Category(String title, Account ownerCategory, List<Expense> expenses) {
        this.title = title;
        this.ownerCategory = ownerCategory;
        this.expenses = expenses;
    }

    public Category(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Account getOwnerCategory() {
        return ownerCategory;
    }

    public void setOwnerCategory(Account ownerCategory) {
        this.ownerCategory = ownerCategory;
    }
}
