package com.epam.lab.accounts.accounts.facade;

import com.epam.lab.accounts.accounts.converter.AccountConverter;
import com.epam.lab.accounts.accounts.dto.AccountDTO;
import com.epam.lab.accounts.accounts.model.requests.CreateUpdateAccountRequest;
import com.epam.lab.accounts.accounts.service.AccountService;
import com.epam.lab.accounts.accounts.service.ErrorsService;
import com.epam.lab.accounts.accounts.service.SessionService;
import com.epam.lab.accounts.accounts.service.UserService;
import com.epam.lab.accounts.accounts.validator.CreateAccountRequestRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountFacadeTest {

    @Mock
    private AccountService accountService;
    @Mock
    private UserService userService;
    @Mock
    private SessionService sessionService ;
    private ErrorsService errorsService = new ErrorsService();
    private AccountFacade accountFacade = new AccountFacade();
    private MockHttpSession httpSession = new MockHttpSession();
    private CreateAccountRequestRequestValidator validator = new CreateAccountRequestRequestValidator();
    private AccountConverter converter = new AccountConverter();

    @Before
    public void resetSession () {
        setField(errorsService, "sessionService", sessionService);

        setField(validator, "accountService", accountService);
        setField(validator, "errorsService", errorsService);

        setField(accountFacade, "createAccountRequestValidator", validator);
        setField(accountFacade, "sessionService", sessionService);
        setField(accountFacade, "accountService", accountService);
        setField(accountFacade, "userService", userService);
        setField(accountFacade, "accountConverter", converter);

        setField(sessionService, "session", httpSession);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleCreateAccountRequest(){
        // GIVEN
        final CreateUpdateAccountRequest createUpdateRequest = getDefaultCreateRequest();
        final AccountDTO account = getDefaultSessionAccount();

        // WHEN
        when(accountService.isAccountExistsForAccountCode(account.getCode())).thenReturn(false);

        // THEN
        validator.validate(createUpdateRequest);
        verify(accountService).createAccountForCurrentUser(account);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testHandleUpdateAccountRequest(){
        // GIVEN
        final CreateUpdateAccountRequest createUpdateRequest = getDefaultUpdateRequest();
        final AccountDTO account = getDefaultSessionAccount();

        // WHEN
        when(accountService.isAccountExistsForAccountCode(account.getCode())).thenReturn(true);
        //when(accountService.getAccountForAccountCode(createUpdateRequest.getAccountCode())).thenReturn(account);

        // THEN
        accountFacade.handleCreateOrUpdateAccountRequest(createUpdateRequest);
        verify(accountService).updateAccount(account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleUpdateAccountRequestFailed(){
        // GIVEN
        //final CreateUpdateAccountRequest createUpdateRequest = getDefaultUpdateRequest();
       // final AccountDTO account = getDefaultSessionAccount();

        // WHEN
        //when(accountService.isAccountExistsForAccountCode(account.getCode())).thenReturn(true);
        //when(accountService.getAccountForAccountCode(createUpdateRequest.getAccountCode())).thenReturn(account);

        // THEN
        //accountFacade.handleCreateOrUpdateAccountRequest(createUpdateRequest);
        //verify(accountService).updateAccount(account);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testisAccountBelongsToCurrentUser(){
        // GIVEN
        final String acoountCode = getDefaultSessionAccount().getCode();

        // WHEN

        // THEN
        verify(accountService).isAccountExistsForAccountCode(acoountCode);
    }





    public AccountDTO getDefaultSessionAccount() {
        final AccountDTO accountModel = new AccountDTO();
        accountModel.setCode("pharma-group");
        accountModel.setBalance(new BigDecimal(666));
        accountModel.setImg("LOL");
        accountModel.setName("Glad Valakas");
        return accountModel;
    }

    public CreateUpdateAccountRequest getDefaultUpdateRequest() {
        final AccountDTO acoountModel = getDefaultSessionAccount();
        final CreateUpdateAccountRequest createUpdateRequest = new CreateUpdateAccountRequest();

        createUpdateRequest.setAccountBalance(new BigDecimal(500));
        createUpdateRequest.setAccountCode(acoountModel.getCode());
        createUpdateRequest.setAccountImage(acoountModel.getImg());
        createUpdateRequest.setAccountName(acoountModel.getName());

        return createUpdateRequest;
    }

    public CreateUpdateAccountRequest getDefaultCreateRequest() {
        final AccountDTO acoountModel = getDefaultSessionAccount();
        final CreateUpdateAccountRequest createUpdateRequest = new CreateUpdateAccountRequest();

        createUpdateRequest.setAccountBalance(acoountModel.getBalance());
        createUpdateRequest.setAccountCode("lol");
        createUpdateRequest.setAccountImage(acoountModel.getImg());
        createUpdateRequest.setAccountName(acoountModel.getName());

        return createUpdateRequest;
    }

}
