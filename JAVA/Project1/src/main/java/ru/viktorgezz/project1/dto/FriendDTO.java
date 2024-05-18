package ru.viktorgezz.project1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FriendDTO {

    private int accountId;

    @NotEmpty(message = "Login should not be empty")
    @Size(min = 1, max = 150, message = "Login should be between 1 and 150 characters")
    private String login;

    public FriendDTO(int accountId, String login) {
        this.accountId = accountId;
        this.login = login;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
