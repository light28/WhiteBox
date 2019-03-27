package com.epam.lab.accounts.accounts.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserModel {

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToMany(targetEntity = AccountModel.class)
    private List<AccountModel> accounts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<AccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountModel> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
