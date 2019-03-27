package com.epam.lab.accounts.e2e.pageobject;

import com.epam.lab.accounts.accounts.model.requests.UserLoginRequest;
import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.PageElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends PageObject {

    @FindBy(id = "login.email")
    private PageElement emailInput;
    @FindBy(id = "login.password")
    private PageElement passwordInput;
    @FindBy(id = "login.submit")
    private PageElement submitButton;
    @FindBy(id = "home.accounts.${account}")
    private PageElement homeAccountForCode;

    @Autowired
    public LoginPage(PageDriver pageDriver) {
        super(pageDriver);
    }

    public void submitLoginForm(final UserLoginRequest request) {

        emailInput.sendKeys(request.getEmail());
        passwordInput.sendKeys(request.getPassword());
        submitButton.submit();
    }
}
