package com.epam.lab.accounts.e2e.config.proxy;

import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.PageElement;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageElementInvocationHandler implements InvocationHandler {

    public static final String LOCATOR_PARAMS_METHOD = "locatorParams";
    private String paramRgxPattern = "\\$\\{.+?}";

    private PageDriver pageDriver;
    private Function<String, By> byProvider;
    private String locator;

    public PageElementInvocationHandler(PageDriver pageDriver, Function<String, By> byProvider, String locator) {
        this.pageDriver = pageDriver;
        this.byProvider = byProvider;
        this.locator = locator;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        String locatorImpl = this.locator;

        if (method.getName().equals(LOCATOR_PARAMS_METHOD)) {

            for (Object object : objects) {

                if (object instanceof Object[]) {

                    Object[] values = (Object[]) object;

                    locatorImpl = this.locator.replaceFirst(paramRgxPattern, (String) values[0]);
                }
            }
        }


        final PageElement pageElement = pageDriver.findOne(byProvider.apply(locatorImpl));
        return method.invoke(pageElement, objects);
    }


}
