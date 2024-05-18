package ru.viktorgezz.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Friend")
public class  Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "account1_id")
    private Account account1;

    @ManyToOne
    @JoinColumn(name = "account2_id")
    private Account account2;

    @Column(name = "confirmed")
    @NotNull
    private boolean confirmed;

    public Friend() {
    }

    public Friend(Account account1, Account account2) {
        this.account1 = account1;
        this.account2 = account2;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount1() {
        return account1;
    }

    public void setAccount1(Account account1) {
        this.account1 = account1;
    }

    public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
