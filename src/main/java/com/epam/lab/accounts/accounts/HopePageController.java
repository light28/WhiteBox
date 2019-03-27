package com.epam.lab.accounts.accounts;

import com.epam.lab.accounts.accounts.dto.AccountDTO;
import com.epam.lab.accounts.accounts.facade.AccountFacade;
import com.epam.lab.accounts.accounts.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HopePageController {

    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/")
    public String index(final Map<String, Object> attrs) {
        final List<AccountDTO> accounts = accountFacade.getAccountsForCurrentUser();
        attrs.put("accounts", accounts);
        return "home-page";
    }


}
