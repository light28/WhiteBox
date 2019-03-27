package com.epam.lab.accounts.accounts.model.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserLoginRequest {

    @Email
    private String email;
    @NotEmpty
    private String password;

    private boolean rememberMe;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
