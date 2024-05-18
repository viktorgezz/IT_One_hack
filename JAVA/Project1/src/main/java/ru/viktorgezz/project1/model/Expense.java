package ru.viktorgezz.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "Expense")
public class Expense {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account ownerExpense;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 150, message = "The title size cannot exceed 150 characters")
    private String title;

    @Column(name = "amount")
    @Min(value = 0, message = "Amount must be greater than 0")
    private Double amount;

    @Column(name = "time")
    private LocalDateTime time;


    public Expense() {
    }

    public Expense(Account ownerExpense, Category category, String title, Double amount) {
        this.ownerExpense = ownerExpense;
        this.category = category;
        this.title = title;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getOwnerExpense() {
        return ownerExpense;
    }

    public void setOwnerExpense(Account ownerExpense) {
        this.ownerExpense = ownerExpense;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
