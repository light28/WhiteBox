package com.epam.lab.accounts.integrationtests.hooks;


import com.epam.lab.accounts.accounts.service.SessionService;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class SpringConfigHook {

    @Autowired
    private SessionService sessionService;

    @Before
    public void resetHttpSession() {
        // adjust session service to user mocked session instead real one
        ReflectionTestUtils.setField(sessionService, "session", new MockHttpSession());
    }
}
