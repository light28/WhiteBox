package com.epam.lab.accounts.accounts;

import com.epam.lab.accounts.accounts.consts.Redirects;
import com.epam.lab.accounts.accounts.facade.UserFacade;
import com.epam.lab.accounts.accounts.model.requests.UserRegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationPageController {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationPageController.class);
    private static final String REGISTRATION_PAGE_JSP = "registration-page";

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/register")
    public String registrationPage() {
        return REGISTRATION_PAGE_JSP;
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public String onRegisterRequest(@Valid final UserRegistrationRequest userRegistrationRequest) {
        String registrationResults;
        try
        {
            // try register user
            userFacade.handleRegistrationRequest(userRegistrationRequest);
            registrationResults = Redirects.REDIRECT_HOME;
        }
        catch (IllegalArgumentException e)
        {
            registrationResults = Redirects.REDIRECT_REGISTER_ERROR;
        }
        return registrationResults;
    }
}
