package com.epam.lab.accounts.accounts.validator;

import com.epam.lab.accounts.accounts.model.requests.UserLoginRequest;
import com.epam.lab.accounts.accounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLoginRequestRequestValidator extends RequestValidator<UserLoginRequest> {

    @Autowired
    private UserService userService;

    @Override
    public void validate(UserLoginRequest object) {
        if (!userService.isUserExistsForEmail(object.getEmail())) {
            getErrorsService().propagateIllegalArgument("Unknown credentials");
        }
    }
}
