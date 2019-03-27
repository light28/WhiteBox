package com.epam.lab.accounts.accounts;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorPageController implements ErrorController {

    public static final String ERROR_PAGE_JSP = "error-page";

    @RequestMapping("/error")
    public String onError(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return ERROR_PAGE_JSP;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
