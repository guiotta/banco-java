package br.com.otta.bank.account.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.client.model.ClientType;

/**
 * Testes unitários do componente {@link AccountTypeMapper}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class AccountTypeMapperTest {
    @InjectMocks
    private AccountTypeMapper mapper;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldCorrectlyMapCheckingAccount() {
        // given
        // when
        AccountType actualType = mapper.map(ClientType.PHYSICAL);
        // then
        assertEquals(AccountType.CHECKING_ACCOUNT, actualType);
    }

    @Test
    public void shouldCorrectlyMapBusinessAccount() {
        // given
        // when
        AccountType actualType = mapper.map(ClientType.LEGAL);
        // then
        assertEquals(AccountType.BUSINESS_ACCOUNT, actualType);
    }
}
