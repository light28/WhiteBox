package com.epam.lab.accounts.e2e.config;

import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Configuration
public class WebDriverConfiguration {

    public static final long DEFAULT_DRIVER_TIMEOUT = 10;

    private final ThreadLocal<PageDriver> pageDriverThreadLocal = ThreadLocal.withInitial(new Supplier<PageDriver>() {
        @Override
        public PageDriver get() {
            final PageDriver pageDriver = new PageDriver();
            pageDriver.set(new ChromeDriverSupplier());
            return pageDriver;
        }
    });

    @Bean
    public PageDriver webDriver() {
        return pageDriverThreadLocal.get();
    }

    public class ChromeDriverSupplier implements Supplier<WebDriver> {
        @Override
        public WebDriver get() {
            final ChromeOptions options = new ChromeOptions();
            if (isHeadlessChromeSystemProperty()) {
                options.addArguments("headless");
            }
            final WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(DEFAULT_DRIVER_TIMEOUT, TimeUnit.SECONDS);
            return driver;
        }
    }

    private boolean isHeadlessChromeSystemProperty() {
        final String isHeadless = System.getProperty("e2e.headless");
        final Boolean isHeadlessObject = BooleanUtils.toBooleanObject(isHeadless);
        return BooleanUtils.toBooleanDefaultIfNull(isHeadlessObject, false);
    }
}
