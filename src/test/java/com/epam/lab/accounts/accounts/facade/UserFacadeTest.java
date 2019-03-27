package com.epam.lab.accounts.accounts.facade;

import com.epam.lab.accounts.accounts.dto.UserDTO;
import com.epam.lab.accounts.accounts.model.requests.UserLoginRequest;
import com.epam.lab.accounts.accounts.model.requests.UserRegistrationRequest;
import com.epam.lab.accounts.accounts.model.UserRole;
import com.epam.lab.accounts.accounts.service.ErrorsService;
import com.epam.lab.accounts.accounts.service.SessionService;
import com.epam.lab.accounts.accounts.service.UserService;
import com.epam.lab.accounts.accounts.validator.UserLoginRequestRequestValidator;
import com.epam.lab.accounts.accounts.validator.UserRegistrationRequestRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFacadeTest {


    @Mock
    private UserService userService;
    @Mock
    private SessionService sessionService ;
    private ErrorsService errorsService = new ErrorsService();
    private UserFacade userFacade = new UserFacade();
    private MockHttpSession httpSession = new MockHttpSession();
    private UserLoginRequestRequestValidator userLoginRequestRequestValidator = new UserLoginRequestRequestValidator();
    private UserRegistrationRequestRequestValidator userRegistrationRequestRequestValidator = new UserRegistrationRequestRequestValidator();
    @Before
    public void resetSession () {
        setField(errorsService, "sessionService", sessionService);
        setField(userLoginRequestRequestValidator, "userService", userService);
        setField(userRegistrationRequestRequestValidator, "userService", userService);
        setField(userLoginRequestRequestValidator, "errorsService", errorsService);
        setField(userRegistrationRequestRequestValidator, "errorsService", errorsService);
        setField(userFacade, "userLoginRequestValidator", userLoginRequestRequestValidator);
        setField(userFacade, "userRegistrationRequestValidator", userRegistrationRequestRequestValidator);
        setField(userFacade, "sessionService", sessionService);
        setField(userFacade, "userService", userService);
        setField(sessionService, "session", httpSession);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleLoginFailed(){
        // GIVEN
        final UserLoginRequest userLoginRequest = getDefaultLoginRequest();
        when(userService.isUserExistsForEmail(userLoginRequest.getEmail())).thenReturn(false);
        // WHEN
        userFacade.handleLoginRequest(userLoginRequest);
        // THEN
        // IllegalArgumentException should be propagated
    }

    @Test
    public void testHandleLogin() {
        // GIVEN
        final UserLoginRequest userLoginRequest = getDefaultLoginRequest();
        final UserDTO user = getDefaultSessionUser();
        when(userService.isUserExistsForEmail(userLoginRequest.getEmail())).thenReturn(true);
        when(userService.getUserForEmail(userLoginRequest.getEmail())).thenReturn(user);
        when(userService.isUserPasswordMatch(userLoginRequest.getEmail(), userLoginRequest.getPassword())).thenReturn(true);
        // WHEN
        userFacade.handleLoginRequest(userLoginRequest);
        // THEN
        verify(sessionService).setSessionUser(user);
    }

    @Test
    public void testHandleRegistrationRequest() {
        // GIVEN
        final UserRegistrationRequest userRegistrationRequest = getDefaultRegistrationRequest();
        final UserDTO userDTO = getUserDtoForRegistrationRequest(userRegistrationRequest);
        // WHEN
        userFacade.handleRegistrationRequest(userRegistrationRequest);
        // THEN
        verify(userService).createUser(userDTO);
        verify(userService).updateUserPassword(userRegistrationRequest.getEmail(), userRegistrationRequest.getPassword());
        verify(sessionService).setSessionUser(userDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleRegistrationRequestFailed() {
        // GIVEN
        final UserRegistrationRequest userRegistrationRequest = getDefaultRegistrationRequest();
        when(userService.isUserExistsForEmail(userRegistrationRequest.getEmail())).thenReturn(true);
        // WHEN
        userFacade.handleRegistrationRequest(userRegistrationRequest);
        // THEN
        // IllegalArgumentException should be propagated
    }

    private UserDTO getUserDtoForRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        final UserDTO userDTO= new UserDTO();
        userDTO.setEmail(userRegistrationRequest.getEmail());
        userDTO.setLastName(userRegistrationRequest.getLastName());
        userDTO.setUserRole(UserRole.USER);
        userDTO.setFirstName(userRegistrationRequest.getFirstName());
        return userDTO;
    }

    public UserLoginRequest getDefaultLoginRequest() {
        final UserDTO userModel = getDefaultSessionUser();
        final UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail(userModel.getEmail());
        userLoginRequest.setRememberMe(true);
        return userLoginRequest;
    }

    public UserDTO getDefaultSessionUser() {
        final UserDTO userModel = new UserDTO();
        userModel.setUserRole(UserRole.GUEST);
        userModel.setEmail("default@gmail.com");
        userModel.setFirstName("FirstName");
        userModel.setLastName("LastName");
        return userModel;
    }

    public UserRegistrationRequest getDefaultRegistrationRequest() {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setEmail("register@gmail.com");
        userRegistrationRequest.setFirstName("FirstName");
        userRegistrationRequest.setLastName("LastName");
        userRegistrationRequest.setPassword("1111");
        return userRegistrationRequest;
    }
}
