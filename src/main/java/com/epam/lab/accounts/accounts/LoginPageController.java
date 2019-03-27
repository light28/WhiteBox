package com.epam.lab.accounts.accounts;

import com.epam.lab.accounts.accounts.consts.Redirects;
import com.epam.lab.accounts.accounts.facade.UserFacade;
import com.epam.lab.accounts.accounts.model.requests.UserLoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginPageController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginPageController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login-page";
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public String onLoginRequest(@Valid final UserLoginRequest userLoginRequest)
    {
        String loginResults;
        try
        {
            userFacade.handleLoginRequest(userLoginRequest);
            loginResults = Redirects.REDIRECT_HOME;
        }
        catch (IllegalArgumentException e)
        {
            loginResults = Redirects.REDIRECT_LOGIN_ERROR;
        }
        return loginResults;
    }

    @RequestMapping("/auth/logout")
    public String onLogoutRequest()
    {
        userFacade.handleLogout();
        return Redirects.REDIRECT_LOGIN;
    }
}
