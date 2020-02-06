package br.com.otta.bank.account.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.google.common.collect.Lists;

import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.account.factory.UniqueAccountNumberFactory;
import br.com.otta.bank.account.mapper.AccountInformationMapper;
import br.com.otta.bank.account.mapper.AccountTypeMapper;
import br.com.otta.bank.account.model.AccountInformation;
import br.com.otta.bank.account.repository.AccountRepository;
import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.filter.ScoreRangeFilter;
import br.com.otta.bank.credit.repository.ScoreRepository;

/**
 * Testes unitários do componente {@link AccountService}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceTest {
    private static final String AGENCY_NUMBER = "10001";
    private static final ClientType CLIENT_TYPE = ClientType.PHYSICAL;
    private static final AccountType ACCOUNT_TYPE = AccountType.CHECKING_ACCOUNT;
    private static final String UNIQUE_NUMBER = "001";
    private static final Integer SCORE = Integer.valueOf(1);

    @Mock
    private AccountInformationMapper accountInformationMapper;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountTypeMapper accountTypeMapper;

    @Mock
    private ScoreRangeFilter scoreRangeFilter;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private UniqueAccountNumberFactory uniqueAccountNumberFactory;

    private AccountService accountService;

    @Mock
    private Client client;
    private Account account;
    @Mock
    private Account savedAccount;
    @Mock
    private Score scoreA;
    @Mock
    private Score scoreB;
    @Mock
    private AccountInformation accountInformation;

    @BeforeEach
    protected void setUp() throws Exception {
        accountService = new AccountService(AGENCY_NUMBER, accountRepository, uniqueAccountNumberFactory,
                accountTypeMapper, scoreRepository, scoreRangeFilter, accountInformationMapper);

        account = new Account(UNIQUE_NUMBER, AGENCY_NUMBER, ACCOUNT_TYPE);
        account.setClient(client);

        given(client.getType()).willReturn(CLIENT_TYPE);
        given(accountTypeMapper.map(CLIENT_TYPE)).willReturn(ACCOUNT_TYPE);
        given(uniqueAccountNumberFactory.get()).willReturn(UNIQUE_NUMBER);
        given(accountRepository.save(account)).willReturn(savedAccount);

        given(accountRepository.findAll()).willReturn(Lists.newArrayList(savedAccount));
    }

    @Test
    public void shouldCorrectlyCreateAccount() throws Exception {
        // given
        // when
        Account actualAccount = accountService.create(client);
        // then
        assertEquals(savedAccount, actualAccount);
    }

    @Test
    public void shouldFindAllAccountsInDatabase() {
        // given
        given(scoreRepository.findAll()).willReturn(Lists.newArrayList(scoreA, scoreB));
        given(savedAccount.getClient()).willReturn(client);
        given(client.getScore()).willReturn(SCORE);
        given(scoreRangeFilter.filter(SCORE, scoreA)).willReturn(Boolean.FALSE);
        given(scoreRangeFilter.filter(SCORE, scoreB)).willReturn(Boolean.TRUE);
        given(accountInformationMapper.map(savedAccount, scoreB)).willReturn(accountInformation);
        // when
        Collection<AccountInformation> actualValues = accountService.findAll();
        // then
        assertThat(actualValues, containsInAnyOrder(accountInformation));
    }

    @Test
    public void shouldThrowExceptionWhenFindingAccountsAndDontFindAnyScore() {
        // given
        given(scoreRepository.findAll()).willReturn(Lists.newArrayList(scoreA, scoreB));
        given(savedAccount.getClient()).willReturn(client);
        given(client.getScore()).willReturn(SCORE);
        given(scoreRangeFilter.filter(SCORE, scoreA)).willReturn(Boolean.FALSE);
        given(scoreRangeFilter.filter(SCORE, scoreB)).willReturn(Boolean.FALSE);
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            accountService.findAll();
        });
        // then
    }

}
