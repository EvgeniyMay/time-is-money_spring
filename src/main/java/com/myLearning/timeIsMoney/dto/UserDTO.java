package com.myLearning.timeIsMoney.dto;

import javax.validation.constraints.Size;

public class UserDTO {

    @Size(min=4, max = 20, message = "#{login.input.error.login-size}")
    private String login;

    @Size(min=6, max = 20, message = "{login.input.error.password.size}")
    private String password;

    public UserDTO() {
    }

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
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
}
