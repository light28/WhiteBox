package com.epam.lab.accounts.e2e.config;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.function.Supplier;

public class PageElement {

    public static PageElement convert(WebElement webElement) {
        return of(() -> webElement);
    }

    public static PageElement of(Supplier<WebElement> webElementSupplier) {
        final PageElement pageElement = new PageElement();
        pageElement.set(webElementSupplier);
        return pageElement;
    }

    private String[] locatorParams;

    public PageElement locatorParams(String ... locatorParams) {
        this.locatorParams = locatorParams;
        return this;
    }

    public String[] getLocatorParams() {
        return locatorParams;
    }

    private Supplier<WebElement> webElementSupplier;

    public void set(Supplier<WebElement> webElementSupplier) {
        this.webElementSupplier = webElementSupplier;
    }

    private WebElement get() {
        return webElementSupplier.get();
    }

    public void sendKeys(CharSequence ... text) {
        get().clear();
        get().sendKeys(text);
    }

    public void sendKeys(BigDecimal numberAsText) {
        sendKeys(numberAsText.toString());
    }

    public void submit() {
        get().submit();
    }

    public void click() {
        get().click();
    }

    public void clear() {
        get().clear();
    }

    public String getText() {
        return get().getText();
    }

    public boolean isExists() {
        try
        {
            return get().isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isCurrentlyVisible() {
        return get().isDisplayed();
    }

    public PageElementAssertions hardAssertThat() {
        return new PageElementAssertions(this);
    }
}
