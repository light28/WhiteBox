package com.epam.lab.accounts.accounts.dto;

import com.epam.lab.accounts.accounts.model.UserRole;

public class UserDTO extends DataClass {

    public static UserDTO guest() {
        final UserDTO user = new UserDTO();
        user.setUserRole(UserRole.GUEST);
        return user;
    }

    private String email;
    private String firstName;
    private String lastName;
    private UserRole userRole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}


