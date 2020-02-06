package br.com.otta.bank.account.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.repository.AccountRepository;

/**
 * Teste unitários do componente {@link UniqueAccountNumberFactory}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class UniqueAccountNumberFactoryTest {
    private static final String VALUE_0 = "0";
    private static final String VALUE_1 = "1";

    @Mock
    private AccountNumberFactory accountNumberFactory;

    @Mock
    private AccountRepository repository;
    @InjectMocks
    private UniqueAccountNumberFactory uniqueAccountNumberFactory;

    @Mock
    private Account account;

    @BeforeEach
    protected void setUp() throws Exception {
        given(accountNumberFactory.get()).willReturn(VALUE_0, VALUE_1);
        given(repository.findByNumber(VALUE_0)).willReturn(Optional.of(account));
        given(repository.findByNumber(VALUE_1)).willReturn(Optional.empty());
    }

    @Test
    public void shouldCorrectlyGenerateUniqueAccountNumber() {
        // given
        // when
        String actualValue = uniqueAccountNumberFactory.get();
        // then
        assertEquals(VALUE_1, actualValue);
    }

    @Test
    public void shouldCorrectlyGenerateAccountNumberUntilGetUniqueValue() {
        // given
        // when
        uniqueAccountNumberFactory.get();
        // then
        verify(repository).findByNumber(VALUE_0);
        verify(repository).findByNumber(VALUE_1);
    }

}
