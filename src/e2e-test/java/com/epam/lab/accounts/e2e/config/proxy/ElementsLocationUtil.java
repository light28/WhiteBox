package com.epam.lab.accounts.e2e.config.proxy;

import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.PageElement;
import com.epam.lab.accounts.e2e.pageobject.PageObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class ElementsLocationUtil {

    public static final String SUPPORTED_LOCATORS_ID = "id";
    public static final String SUPPORTED_LOCATORS_NAME = "name";
    public static final String SUPPORTED_LOCATORS_CLASS_NAME = "className";
    public static final String SUPPORTED_LOCATORS_XPATH = "xpath";
    public static final String SUPPORTED_LOCATORS_CSS = "css";
    public static final String SUPPORTED_LOCATORS_LINK_TEXT = "linkText";
    public static final String SUPPORTED_LOCATORS_PARTIAL_LINK_TEXT = "partialLinkText";
    public static final String SUPPORTED_LOCATORS_USING = "using";

    private PageDriver pageDriver;

    public ElementsLocationUtil(PageDriver pageDriver) {
        this.pageDriver = pageDriver;
    }

    public PageElement locate(Function<String, By> byProvider, String locator) {
        return (PageElement) Enhancer.create(PageElement.class, new PageElementInvocationHandler(pageDriver, byProvider, locator));
    }

    public void initializePageElements(PageObject pageObject) {

        final ImmutableList<Field> fields = ImmutableList.copyOf(pageObject.getClass().getDeclaredFields());

        final List<Field> pageElementFields = fields.stream()
                .filter(f -> PageElement.class.isAssignableFrom(f.getType())).collect(Collectors.toList());

        for (Field pageElementField : pageElementFields) {

            if (pageElementField.isAnnotationPresent(FindBy.class)) {

                handlePageElementField(pageObject, pageElementField);
            }
        }
    }

    private void handlePageElementField(PageObject pageObject, Field pageElementField) {
        final FindBy elementLocators = pageElementField.getAnnotation(FindBy.class);

        final Map<String, Function<String, PageElement>> locatorsMap = createLocatorMap(elementLocators);

        final Map.Entry<String, Function<String, PageElement>> locatorToPageElementProvider = locatorsMap.entrySet().stream()
                .filter(e -> isNotBlank(e.getKey())).findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("No valid locator found for field " + pageElementField.getClass()));


        final String locator = locatorToPageElementProvider.getKey();
        final PageElement pageElement = locatorsMap.get(locator).apply(locatorToPageElementProvider.getKey());

        setFieldValue(pageObject, pageElementField, pageElement);
    }

    private void setFieldValue(PageObject pageObject, Field pageElementField, PageElement pageElement) {
        ReflectionUtils.makeAccessible(pageElementField);
        ReflectionUtils.setField(pageElementField, pageObject, pageElement);
    }

    private Map<String, Function<String, PageElement>> createLocatorMap(FindBy elementLocators) {
        final Map<String, Function<String, PageElement>> locatorsMap = new HashMap<>();

        locatorsMap.put(elementLocators.id(), (id) -> locate(By::id, id));
        locatorsMap.put(elementLocators.name(), (name) -> locate(By::name, name));
        locatorsMap.put(elementLocators.className(), (className) -> locate(By::className, className));
        locatorsMap.put(elementLocators.xpath(), (xpath) -> locate(By::xpath, xpath));
        locatorsMap.put(elementLocators.css(), (css) -> locate(By::cssSelector, css));
        locatorsMap.put(elementLocators.linkText(), (linkText) -> locate(By::linkText, linkText));
        locatorsMap.put(elementLocators.partialLinkText(), (partialLinkText) -> locate(By::partialLinkText, partialLinkText));
        return locatorsMap;
    }


}
