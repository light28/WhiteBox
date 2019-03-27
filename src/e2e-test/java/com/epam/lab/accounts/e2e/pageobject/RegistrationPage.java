package com.epam.lab.accounts.e2e.pageobject;

import com.epam.lab.accounts.accounts.model.requests.UserRegistrationRequest;
import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.PageElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationPage extends PageObject{

    @FindBy(id = "register.firstName")
    private PageElement firstNameInput;
    @FindBy(id = "register.lastName")
    private PageElement lastNameInput;
    @FindBy(id = "register.email")
    private PageElement emailInput;
    @FindBy(id = "register.password")
    private PageElement passwordInput;
    @FindBy(id = "register.submit")
    private PageElement submitInput;

    @Autowired
    public RegistrationPage(PageDriver pageDriver) {
        super(pageDriver);
    }

    public void submitRegistrationForm(UserRegistrationRequest request) {

        firstNameInput.sendKeys(request.getFirstName());
        lastNameInput.sendKeys(request.getLastName());
        emailInput.sendKeys(request.getEmail());
        passwordInput.sendKeys(request.getPassword());
        submitInput.click();
    }
}
