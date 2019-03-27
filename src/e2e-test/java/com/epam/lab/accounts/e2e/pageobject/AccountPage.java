package com.epam.lab.accounts.e2e.pageobject;

import com.epam.lab.accounts.accounts.model.requests.CreateUpdateAccountRequest;
import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.PageElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountPage extends PageObject{

    @FindBy(id = "account.edit.code")
    private PageElement accountEditCode;
    @FindBy(id = "account.edit.name")
    private PageElement accountEditName;
    @FindBy(id = "imgUrl")
    private PageElement accountEditImgUrl;
    @FindBy(id = "account.edit.balance")
    private PageElement accountEditBalance;
    @FindBy(id = "account.edit.submit")
    private PageElement accountEditSubmit;

    @Autowired
    public AccountPage(PageDriver pageDriver) {
        super(pageDriver);
    }

    public void submitCreateAccountForm(CreateUpdateAccountRequest request) {
        accountEditCode.sendKeys(request.getAccountCode());
        submitUpdateAccountForm(request);
    }

    public void submitUpdateAccountForm(CreateUpdateAccountRequest request) {
        accountEditName.sendKeys(request.getAccountName());
        accountEditImgUrl.sendKeys(request.getAccountImage());
        accountEditBalance.sendKeys(request.getAccountBalance());
        accountEditSubmit.submit();
    }
}
