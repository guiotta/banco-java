package br.com.otta.bank.account.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.client.entity.Client;

/**
 * Testes unitários da classe {@link AccountBuilder}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class AccountBuilderTest {
    private static final String NUMBER = "0";
    private static final String AGENCY = "agency";
    private static final AccountType TYPE = AccountType.CHECKING_ACCOUNT;

    @InjectMocks
    private AccountBuilder accountBuilder;

    @Mock
    private Account account;
    @Mock
    private Client client;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldBuildEmptyAccount() {
        // given
        Account expectedAccount = new Account();
        // when
        Account actualValue = accountBuilder.build();
        // then
        assertEquals(expectedAccount, actualValue);
    }

    @Test
    public void shouldBuildyAccountWithNumber() {
        // given
        Account expectedAccount = new Account();
        expectedAccount.setNumber(NUMBER);
        // when
        Account actualValue = accountBuilder.setNumber(NUMBER).build();
        // then
        assertEquals(expectedAccount, actualValue);
    }

    @Test
    public void shouldBuildyAccountWithAgency() {
        // given
        Account expectedAccount = new Account();
        expectedAccount.setAgency(AGENCY);
        // when
        Account actualValue = accountBuilder.setAgency(AGENCY).build();
        // then
        assertEquals(expectedAccount, actualValue);
    }

    @Test
    public void shouldBuildyAccountWithAccountType() {
        // given
        Account expectedAccount = new Account();
        expectedAccount.setType(TYPE);
        // when
        Account actualValue = accountBuilder.setAccountType(TYPE).build();
        // then
        assertEquals(expectedAccount, actualValue);
    }

    @Test
    public void shouldBuildyAccountWithClient() {
        // given
        Account expectedAccount = new Account();
        expectedAccount.setClient(client);
        // when
        Account actualValue = accountBuilder.setClient(client).build();
        // then
        assertEquals(expectedAccount, actualValue);
    }
}
