package com.epam.lab.accounts.accounts.service;

import com.epam.lab.accounts.accounts.consts.SessionAttributes;
import com.epam.lab.accounts.accounts.dto.UserDTO;
import com.epam.lab.accounts.accounts.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionService {

    private HttpSession session;

    public UserDTO getSessionUser() {
        if (session.getAttribute(SessionAttributes.SESSION_USER) == null) {
            session.setAttribute(SessionAttributes.SESSION_USER, UserDTO.guest());
        }
        return (UserDTO) session.getAttribute(SessionAttributes.SESSION_USER);
    }

    public void setSessionUser(final UserDTO userModel) {
        session.setAttribute(SessionAttributes.SESSION_USER, userModel);
    }

    @Autowired
    @SuppressWarnings("all")
    public void setSession(HttpSession session) {
        this.session = session;
    }

    public boolean isGuest() {
        return UserRole.GUEST.equals(getSessionUser().getUserRole());
    }

    public void setLastError(final Exception e) {
        session.setAttribute(SessionAttributes.ERRORS, e);
    }

    public String getSessionUserEmail() {
        return getSessionUser().getEmail();
    }
}
