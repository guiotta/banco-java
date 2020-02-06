package br.com.otta.bank.account.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.account.model.AccountInformation;
import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.extractor.LimitValuesExtractor;
import br.com.otta.bank.credit.model.LimitValues;

/**
 * Testes unitários do componente {@link AccountInformationMapper}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class AccountInformationMapperTest {
    private static final Long ID = Long.valueOf(1l);
    private static final String NAME = "name";
    private static final String DOCUMENT = "document";
    private static final AccountType TYPE = AccountType.CHECKING_ACCOUNT;
    private static final String OVERDRAFT = "overdraft";
    private static final String CREDIT_CARD_LIMIT = "creditCardLimit";

    @Mock
    private LimitValuesExtractor extractor;
    @InjectMocks
    private AccountInformationMapper accountInformationMapper;

    @Mock
    private Account account;
    @Mock
    private Score score;
    @Mock
    private Client client;
    @Mock
    private LimitValues limitValues;

    @BeforeEach
    protected void setUp() throws Exception {
        given(account.getId()).willReturn(ID);
        given(account.getClient()).willReturn(client);
        given(client.getName()).willReturn(NAME);
        given(client.getDocument()).willReturn(DOCUMENT);
        given(account.getType()).willReturn(TYPE);
        given(extractor.extract(score)).willReturn(limitValues);
        given(limitValues.getOverdraft()).willReturn(OVERDRAFT);
        given(limitValues.getCreditCardLimit()).willReturn(CREDIT_CARD_LIMIT);
    }

    @Test
    public void shouldCorrectlyMapAccountInformation() throws Exception {
        // given
        AccountInformation expectedValue = new AccountInformation(ID, NAME, DOCUMENT, TYPE.getId(), OVERDRAFT,
                CREDIT_CARD_LIMIT);
        // when
        AccountInformation actualValue = accountInformationMapper.map(account, score);
        // then
        assertEquals(expectedValue, actualValue);
    }

}
