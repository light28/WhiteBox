package com.epam.lab.accounts.e2e.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormAttribute {

    // attribute name being used to populate data from object with form data
    // only for input elements
    String value() default "";
}
