package com.epam.lab.accounts.e2e.config;

import com.google.common.collect.Iterators;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PageElementCollection implements Iterable<PageElement> {

    public Function<WebElement, String> innerHtmlMapper = (WebElement e) -> e.getAttribute("innerHTML");

    public static PageElementCollection of(Supplier<List<WebElement>> webElementListSupplier) {
        final PageElementCollection pageElementCollection = new PageElementCollection();
        pageElementCollection.set(webElementListSupplier);
        return pageElementCollection;
    }

    public Supplier<List<WebElement>> webElementListSupplier;

    public void set(Supplier<List<WebElement>> webElementListSupplier) {
        this.webElementListSupplier = webElementListSupplier;
    }

    public int size() {
        return webElementListSupplier.get().size();
    }

    public List<String> textValues() {
        return webElementListSupplier.get().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> innerHtmlValues() {
        return webElementListSupplier.get().stream().map(innerHtmlMapper).collect(Collectors.toList());
    }


    @Override
    public Iterator<PageElement> iterator() {

        final Iterator<PageElement> pageElementIterator = webElementListSupplier
                .get().stream().map(PageElement::convert).iterator();

        return new Iterator<PageElement>() {
            @Override
            public boolean hasNext() {
                return pageElementIterator.hasNext();
            }

            @Override
            public PageElement next() {
                return pageElementIterator.next();
            }
        };
    }
}
