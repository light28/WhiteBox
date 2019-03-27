package com.epam.lab.accounts.e2e.config;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;

public class PageDriver {

    private Supplier<WebDriver> webDriverSupplier;
    @Deprecated
    private WebDriver webDriver;

    public void set(final Supplier<WebDriver> webDriverSupplier) {
        this.webDriverSupplier = webDriverSupplier;
    }

    private WebDriver get() {
        if (webDriver == null) {
            webDriver = webDriverSupplier.get();
        }
        return webDriver;
    }

    public void loadUrl(final String url) {
        get().navigate().to(url);
    }

    public void close() {
        try {
            ofNullable(webDriver).ifPresent(WebDriver::close);
        } finally {
            webDriver = null;
        }
    }

    public PageElement findOne(By by) {
        return PageElement.of(new Supplier<WebElement>() {
            @Override
            public WebElement get() {
                return PageDriver.this.get().findElement(by);
            }
        });
    }

    public PageElementCollection findAll(By by) {
        return PageElementCollection.of(new Supplier<List<WebElement>>() {
            @Override
            public List<WebElement> get() {
                return PageDriver.this.get().findElements(by);
            }
        });
    }

    public boolean isElementExists(By by) {
        try {
            return get().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void refresh() {
        get().manage().deleteAllCookies();
    }
}
