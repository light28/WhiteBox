package com.epam.lab.accounts.accounts.validator;

import com.epam.lab.accounts.accounts.model.requests.UserRegistrationRequest;
import com.epam.lab.accounts.accounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationRequestRequestValidator extends RequestValidator<UserRegistrationRequest> {

    @Autowired
    private UserService userService;

    @Override
    public void validate(UserRegistrationRequest object) {
        if (userService.isUserExistsForEmail(object.getEmail())) {
            getErrorsService().propagateIllegalArgument("Email %s already in use", object.getEmail());
        }
    }
}
