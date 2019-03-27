package com.epam.lab.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.DefaultCookieSerializer;

@SpringBootApplication
public class AccountsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AccountsApplication.class);
    }

    @Bean
    public DefaultCookieSerializer cookieSerializer() {
        return new DefaultCookieSerializer();
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}

