package com.epam.lab.accounts.e2e.defs;

import com.epam.lab.accounts.accounts.model.requests.CreateUpdateAccountRequest;
import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.PageElement;
import com.epam.lab.accounts.e2e.pageobject.AccountGridPage;
import com.epam.lab.accounts.e2e.pageobject.AccountPage;
import com.epam.lab.accounts.integrationtests.config.CucumberUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.junit.Assert.*;

public class AccountE2eDefs {

    @Autowired
    private PageDriver pageDriver;

    @Autowired
    private AccountPage accountPage;

    @Autowired
    private AccountGridPage accountGridPage;

    @Given("^user submit create-update account form with attributes:$")
    public void userSubmitCreateUpdateAccountForm(final DataTable dataTable) {
        final CreateUpdateAccountRequest request = CucumberUtils.toObject(dataTable, CreateUpdateAccountRequest.class);
        if (!isBlank(request.getAccountCode()))
        {
            accountPage.submitCreateAccountForm(request);
        }
        else
        {
            accountPage.submitUpdateAccountForm(request);
        }
    }

    @Then("account details displayed on accounts grid page")
    public void accountOnAccountsGridPage(final DataTable dataTable) {
        final CreateUpdateAccountRequest request = CucumberUtils.toObject(dataTable, CreateUpdateAccountRequest.class);
        accountGridPage.accountOnAccountsGridPage(request);
    }
}
