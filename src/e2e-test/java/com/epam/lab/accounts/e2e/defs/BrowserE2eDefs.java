package com.epam.lab.accounts.e2e.defs;

import com.epam.lab.accounts.accounts.facade.AccountFacade;
import com.epam.lab.accounts.accounts.service.AccountService;
import com.epam.lab.accounts.e2e.config.PageDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

public class BrowserE2eDefs {

    @Autowired
    private PageDriver pageDriver;
    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private AccountService accountService;

    @Given("^browser opened at (.+) url$")
    public void browserNavigateTo(final String url) {
        pageDriver.loadUrl(url);
    }
}
