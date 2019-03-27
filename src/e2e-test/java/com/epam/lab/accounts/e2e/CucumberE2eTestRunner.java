package com.epam.lab.accounts.e2e;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/e2e-test/resources/UserAccountE2E.feature",
        glue = {
                "com.epam.lab.accounts.e2e.defs",
                "com.epam.lab.accounts.integrationtests.defs",
                "com.epam.lab.accounts.e2e.config",
                "com.epam.lab.accounts.e2e.hooks"
        },
        plugin = "pretty"
)

@SpringBootTest
public class CucumberE2eTestRunner {
}
