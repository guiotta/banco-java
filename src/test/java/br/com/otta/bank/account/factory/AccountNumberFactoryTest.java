package br.com.otta.bank.account.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Testes unitários do componente {@link AccountNumberFactory}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class AccountNumberFactoryTest {
    private static final int GENERATED_VALUE = 100;
    private static final String EXPECTED_VALUE = "000100";

    private AccountNumberFactory factory = spy(new AccountNumberFactory());

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldCorrectlyGenerateAccountNumber() {
        // given
        when(factory.generateRandomNumber()).thenReturn(GENERATED_VALUE);
        // when
        String actualValue = factory.get();
        // then
        assertEquals(EXPECTED_VALUE, actualValue);
    }
}
