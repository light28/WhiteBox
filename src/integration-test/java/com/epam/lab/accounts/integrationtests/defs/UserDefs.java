package com.epam.lab.accounts.integrationtests.defs;

import com.epam.lab.accounts.accounts.LoginPageController;
import com.epam.lab.accounts.accounts.RegistrationPageController;
import com.epam.lab.accounts.accounts.dto.UserDTO;
import com.epam.lab.accounts.accounts.model.UserModel;
import com.epam.lab.accounts.accounts.model.requests.UserLoginRequest;
import com.epam.lab.accounts.accounts.model.requests.UserRegistrationRequest;
import com.epam.lab.accounts.accounts.service.SessionService;
import com.epam.lab.accounts.accounts.service.UserService;
import com.epam.lab.accounts.integrationtests.config.CucumberUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class UserDefs {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private LoginPageController loginPageController;
    @Autowired
    private RegistrationPageController registrationPageController;

    @Given("send user registration request with attributes:")
    public void sendUserRegistrationRequest(final DataTable dataTable) {
        final UserRegistrationRequest userRegistrationRequest = CucumberUtils.toObject(dataTable, UserRegistrationRequest.class);
        registrationPageController.onRegisterRequest(userRegistrationRequest);
    }

    @Then("user model exists in database with attributes:")
    public void userModelExistsInDatabaseWithAttributes(final DataTable dataTable) {
        final UserModel expectedUserModel = CucumberUtils.toObject(dataTable, UserModel.class);
        final UserModel actualUserModel = userService.getUserModelForEmail(expectedUserModel.getEmail());

        assertEquals(actualUserModel.getFirstName(), expectedUserModel.getFirstName());
        assertEquals(actualUserModel.getLastName(), expectedUserModel.getLastName());
        assertEquals(actualUserModel.getEmail(), expectedUserModel.getEmail());
        assertEquals(actualUserModel.getPassword(), expectedUserModel.getPassword());
    }

    @When("send user login request with attributes:")
    public void sendUserLoginRequestWithAttributes(final DataTable dataTable) {
        final UserLoginRequest userLoginRequest = CucumberUtils.toObject(dataTable, UserLoginRequest.class);
        loginPageController.onLoginRequest(userLoginRequest);
    }


    @Then("session contains info about session user:")
    public void sessionContainsInfoAboutSessionUser(final DataTable dataTable) {
        final UserDTO expectedSessionUser = CucumberUtils.toObject(dataTable, UserDTO.class);
        final UserDTO sessionUser = sessionService.getSessionUser();
        assertEquals(sessionUser.getEmail(), expectedSessionUser.getEmail());
        assertEquals(sessionUser.getFirstName(), expectedSessionUser.getFirstName());
        assertEquals(sessionUser.getLastName(), expectedSessionUser.getLastName());
        assertEquals(sessionUser.getUserRole(), expectedSessionUser.getUserRole());
    }

    @Given("user (.+) logs in into app")
    public void userLogsInIntoApp(final String email) {
        final UserDTO user = userService.getUserForEmail(email);
        sessionService.setSessionUser(user);
    }
}
