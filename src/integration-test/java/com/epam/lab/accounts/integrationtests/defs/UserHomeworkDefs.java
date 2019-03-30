package com.epam.lab.accounts.integrationtests.defs;

import com.epam.lab.accounts.accounts.AccountsPageController;
import com.epam.lab.accounts.accounts.LoginPageController;
import com.epam.lab.accounts.accounts.RegistrationPageController;
import com.epam.lab.accounts.accounts.model.AccountModel;
import com.epam.lab.accounts.accounts.model.UserModel;
import com.epam.lab.accounts.accounts.model.requests.UserLoginRequest;
import com.epam.lab.accounts.accounts.model.requests.UserRegistrationRequest;
import com.epam.lab.accounts.accounts.service.AccountService;
import com.epam.lab.accounts.accounts.service.SessionService;
import com.epam.lab.accounts.accounts.service.UserService;
import com.epam.lab.accounts.integrationtests.config.CucumberUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserHomeworkDefs {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private LoginPageController loginPageController;
    @Autowired
    private RegistrationPageController registrationPageController;



    @When("send user login request:")
    public void sendUserLoginRequest(final DataTable dataTable) {
        final UserLoginRequest userLoginRequest = CucumberUtils.toObject(dataTable, UserLoginRequest.class);
        loginPageController.onLoginRequest(userLoginRequest);
    }



    @Then("user model exists in database:")
    public void accountIsExist(final DataTable dataTable) {
        final UserModel user = CucumberUtils.toObject(dataTable, UserModel.class);
        boolean isAccountExist = userService.isUserExistsForEmail(user.getEmail());

        assertTrue("account not exist",isAccountExist);

    }



    @Then("user model is not exists in database:")
    public void accountIsNotExist(final DataTable dataTable) {
        final UserModel user = CucumberUtils.toObject(dataTable, UserModel.class);
        boolean isAccountExist = userService.isUserExistsForEmail(user.getEmail());

        assertFalse("account is exist",isAccountExist);

    }
}
