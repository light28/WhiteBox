package com.epam.lab.accounts.e2e.pageobject;

import com.epam.lab.accounts.e2e.config.PageDriver;
import com.epam.lab.accounts.e2e.config.proxy.ElementsLocationUtil;


public abstract class PageObject {

    private final PageDriver pageDriver;

    public PageObject(PageDriver pageDriver) {

        this.pageDriver = pageDriver;

        final ElementsLocationUtil locationUtil = new ElementsLocationUtil(pageDriver);

        locationUtil.initializePageElements(this);
    }

    public PageDriver getPageDriver() {
        return pageDriver;
    }
}
