package ru.viktorgezz.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "Income")
public class Income {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "profit")
    @Min(value = 0, message = "Profit must be greater than 0")
    private Double profit;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 150, message = "The title size cannot exceed 150 characters")
    private String title;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account ownerIncome;

    @Column(name = "time")
    private LocalDateTime time;

    public Income() {
    }

    public Income(Double profit, String title, Account ownerIncome) {
        this.profit = profit;
        this.title = title;
        this.ownerIncome = ownerIncome;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", profit=" + profit +
                ", title='" + title + '\'' +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Account getOwnerIncome() {
        return ownerIncome;
    }

    public void setOwnerIncome(Account ownerExpense) {
        this.ownerIncome = ownerExpense;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
