package com.epam.lab.accounts.e2e.pageobject;

import com.epam.lab.accounts.accounts.model.requests.CreateUpdateAccountRequest;
import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.PageElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountGridPage extends PageObject {

    @FindBy(id = "home.accounts.${accountCode}")
    private PageElement accountCodePageElementForAccountCode;
    @FindBy(id = "home.accounts.${accountCode}.name")
    private PageElement accountNamePageElementForAccountCode;

    @Autowired
    public AccountGridPage(PageDriver pageDriver) {
        super(pageDriver);
    }


    public void accountOnAccountsGridPage(CreateUpdateAccountRequest request) {

        accountCodePageElementForAccountCode
                .locatorParams(request.getAccountCode())
                .hardAssertThat().elementExists();

        accountNamePageElementForAccountCode
                .locatorParams(request.getAccountCode())
                .hardAssertThat().elementTextIsEqualTo(request.getAccountName());
    }
}
