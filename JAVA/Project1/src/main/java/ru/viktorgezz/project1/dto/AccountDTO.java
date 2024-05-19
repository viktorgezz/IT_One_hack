package ru.viktorgezz.project1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AccountDTO {

    @NotEmpty(message = "Login should not be empty")
    @Size(min = 1, max = 150, message = "The login size cannot exceed 150 characters")
    private String login;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 1, max = 150, message = "The password size cannot exceed 150 characters")
    private String password;

    @NotEmpty(message = "Email should not be empty")
    @Size(min = 1, max = 150, message = "The email size cannot exceed 150 characters")
    private String email;

    public AccountDTO() {
    }

    public AccountDTO(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
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
}
