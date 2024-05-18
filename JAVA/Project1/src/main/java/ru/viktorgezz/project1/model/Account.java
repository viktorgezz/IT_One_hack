package ru.viktorgezz.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
        @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 150, message = "Name should be between 1 and 150 characters")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @Column(name = "email")
    @Email(message = "Email is incorrect")
    private String email;

    @OneToMany(mappedBy = "ownerExpense")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "ownerCategory")
    private List<Category> categories;

    @OneToMany(mappedBy = "ownerIncome")
    private List<Income> incomes;

    @OneToOne(mappedBy = "ownerGoal")
    private Goal goal;

    @OneToMany(mappedBy = "account1")
    private Set<Friend> friends1;

    @OneToMany(mappedBy = "account2")
    private Set<Friend> friends2;

    public Account() {
    }

    public Account(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Set<Friend> getFriends1() {
        return friends1;
    }

    public void setFriends1(Set<Friend> friends1) {
        this.friends1 = friends1;
    }

    public Set<Friend> getFriends2() {
        return friends2;
    }

    public void setFriends2(Set<Friend> friends2) {
        this.friends2 = friends2;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
