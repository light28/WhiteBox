package com.epam.lab.accounts.accounts.facade;

import com.epam.lab.accounts.accounts.dto.UserDTO;
import com.epam.lab.accounts.accounts.model.UserRole;
import com.epam.lab.accounts.accounts.model.requests.UserLoginRequest;
import com.epam.lab.accounts.accounts.model.requests.UserRegistrationRequest;
import com.epam.lab.accounts.accounts.service.SessionService;
import com.epam.lab.accounts.accounts.service.UserService;
import com.epam.lab.accounts.accounts.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserFacade {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RequestValidator<UserLoginRequest> userLoginRequestValidator;
    @Autowired
    private RequestValidator<UserRegistrationRequest> userRegistrationRequestValidator;

    public void handleLoginRequest(final UserLoginRequest userLoginRequest) {

        userLoginRequestValidator.validate(userLoginRequest);

        final UserDTO userDTO = userService.getUserForEmail(userLoginRequest.getEmail());
        if (userService.isUserPasswordMatch(userDTO.getEmail(), userLoginRequest.getPassword())) {
            System.out.println("SET SESSION USER: " + userDTO);
            sessionService.setSessionUser(userDTO);
        }
    }

    @Transactional
    public void handleRegistrationRequest(final UserRegistrationRequest userRegistrationRequest) {

        userRegistrationRequestValidator.validate(userRegistrationRequest);

        final UserDTO userDTO = getUserDtoForUserRegistrationRequest(userRegistrationRequest);
        userService.createUser(userDTO);
        userService.updateUserPassword(userDTO.getEmail(), userRegistrationRequest.getPassword());
        sessionService.setSessionUser(userDTO);
    }

    public void handleLogout() {
        sessionService.setSessionUser(UserDTO.guest());
    }

    private UserDTO getUserDtoForUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userRegistrationRequest.getEmail());
        userDTO.setLastName(userRegistrationRequest.getLastName());
        userDTO.setFirstName(userRegistrationRequest.getFirstName());
        userDTO.setUserRole(UserRole.USER);
        return userDTO;
    }
}
