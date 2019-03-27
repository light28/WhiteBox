package com.epam.lab.accounts.e2e.defs;

import com.epam.lab.accounts.accounts.facade.AccountFacade;
import com.epam.lab.accounts.accounts.model.requests.UserLoginRequest;
import com.epam.lab.accounts.accounts.model.requests.UserRegistrationRequest;
import com.epam.lab.accounts.accounts.service.AccountService;
import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.pageobject.LoginPage;
import com.epam.lab.accounts.e2e.pageobject.RegistrationPage;
import com.epam.lab.accounts.integrationtests.config.CucumberUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertTrue;

public class UserE2eDefs {
    @Autowired
    private PageDriver pageDriver;
    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private RegistrationPage registrationPage;

    @When("user submit login form with attributes:")
    public void userSubmitLoginFormWithAttributes(final DataTable dataTable) {
        final UserLoginRequest userLoginRequest = CucumberUtils.toObject(dataTable, UserLoginRequest.class);
        loginPage.submitLoginForm(userLoginRequest);
    }

    @Then("user is logged in and located on home page")
    public void userIsLoggedInAndLocatedOnHomePage() {
        assertTrue(pageDriver.findOne(By.id("home.user.menu")).isCurrentlyVisible());
    }

    @When("user submit registration form with attributes:")
    public void userSubmitRegistrationFormWithAttributes(final DataTable dataTable) {
        final UserRegistrationRequest request = CucumberUtils.toObject(dataTable, UserRegistrationRequest.class);
        registrationPage.submitRegistrationForm(request);
    }
}
