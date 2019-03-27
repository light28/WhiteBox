package com.epam.lab.accounts.e2e.hooks;


import com.epam.lab.accounts.AccountsApplication;
import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.WebDriverConfiguration;
import com.epam.lab.accounts.e2e.config.WebDriverProperties;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest(
        classes = {AccountsApplication.class, WebDriverConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PageDriverConfigHook {

    @Autowired
    private WebDriverProperties webDriverProperties;

    @Autowired
    private PageDriver pageDriver;


    @Before
    public void setWebDriverPath() {

        System.setProperty("webdriver.chrome.driver", webDriverProperties.getWebDriverPathForCurrentOS());
    }

    @After
    public void closeDriverAfterTest() {

        pageDriver.refresh();
    }

}
