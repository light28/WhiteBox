package com.epam.lab.accounts.integrationtests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/integration-test/resources",
        glue = {
                "com.epam.lab.accounts.integrationtests.defs",
                "com.epam.lab.accounts.integrationtests.config",
                "com.epam.lab.accounts.integrationtests.hooks"
        },
        plugin = "pretty"
)

@SpringBootTest
public class CucumberIntegrationTestRunner {
}
