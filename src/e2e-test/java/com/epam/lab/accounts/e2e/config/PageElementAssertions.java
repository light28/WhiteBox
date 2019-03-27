package com.epam.lab.accounts.e2e.config;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PageElementAssertions {

    private PageElement pageElement;

    public PageElementAssertions(PageElement pageElement) {
        this.pageElement = pageElement;
    }

    public void elementExists() {
        assertThat(pageElement.isExists()).as("Element should exists but does not").isTrue();
    }

    public void elementTextIsEqualTo(String expectedText) {
        assertThat(pageElement.getText()).isEqualTo(expectedText);
    }
}
