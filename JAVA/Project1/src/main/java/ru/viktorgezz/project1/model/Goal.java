package ru.viktorgezz.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;


@Entity
@Table(name = "Goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account ownerGoal;

    @Column(name = "monthly_income")
    @Min(value = 0, message = "Income must be greater than 0")
    private Double monthlyIncome;

    @Column(name = "monthly_savings")
    @Min(value = 0, message = "Monthly savings must be greater than 0")
    private Double monthlySavings;

    public Goal() {
    }

    public Goal(Account ownerGoal, Double monthlyIncome, Double monthlySavings) {
        this.ownerGoal = ownerGoal;
        this.monthlyIncome = monthlyIncome;
        this.monthlySavings = monthlySavings;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", monthlyIncome=" + monthlyIncome +
                ", monthlySavings=" + monthlySavings +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getOwnerGoal() {
        return ownerGoal;
    }

    public void setOwnerGoal(Account ownerGoal) {
        this.ownerGoal = ownerGoal;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double income) {
        this.monthlyIncome = income;
    }

    public Double getMonthlySavings() {
        return monthlySavings;
    }

    public void setMonthlySavings(Double economy) {
        this.monthlySavings = economy;
    }
}
