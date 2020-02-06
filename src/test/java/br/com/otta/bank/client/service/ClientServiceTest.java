package br.com.otta.bank.client.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.google.common.collect.Lists;

import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.service.AccountService;
import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.factory.ClientScoreFactory;
import br.com.otta.bank.client.mapper.ClientInformationMapper;
import br.com.otta.bank.client.model.ClientData;
import br.com.otta.bank.client.model.ClientInformation;
import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.client.repository.ClientRepository;
import br.com.otta.bank.client.validation.ValidationService;
import br.com.otta.bank.client.validation.exception.ValidationFailedException;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    private static final int SCORE = 5;
    private static final int CLIENT_TYPE_ID = 1;
    private static final String NAME = "name";
    private static final String DOCUMENT = "document";

    @Mock
    private AccountService accountService;

    @Mock
    private ClientInformationMapper mapper;

    @Mock
    private ClientRepository repository;

    @Mock
    private ClientScoreFactory scoreFactory;

    @Mock
    private ValidationService validationService;
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientData clientData;
    @Mock
    private Client savedClient;
    @Mock
    private Account account;
    @Mock
    private ClientInformation clientInformation;

    @BeforeEach
    protected void setUp() throws Exception {

    }

    @Test
    public void shouldCorrectlySaveClient() throws Exception {
        // given
        given(scoreFactory.get()).willReturn(SCORE);

        given(clientData.getType()).willReturn(CLIENT_TYPE_ID);
        given(clientData.getDocument()).willReturn(DOCUMENT);

        given(clientData.getName()).willReturn(NAME);
        Client client = new Client(NAME, ClientType.LEGAL, DOCUMENT, SCORE);
        given(validationService.validateClientDocument(ClientType.LEGAL, DOCUMENT)).willReturn(Boolean.TRUE);
        given(repository.save(client)).willReturn(savedClient);
        given(accountService.create(savedClient)).willReturn(account);
        given(mapper.map(savedClient)).willReturn(clientInformation);
        // when
        ClientInformation actualValue = clientService.save(clientData);
        // then
        assertEquals(clientInformation, actualValue);
    }

    @Test
    public void shouldThrowExceptionWhenValidationFails() throws Exception {
        // given
        given(scoreFactory.get()).willReturn(SCORE);

        given(clientData.getType()).willReturn(CLIENT_TYPE_ID);
        given(clientData.getDocument()).willReturn(DOCUMENT);

        given(validationService.validateClientDocument(ClientType.LEGAL, DOCUMENT))
                .willThrow(ValidationFailedException.class);
        // when
        Assertions.assertThrows(ValidationFailedException.class, () -> {
            clientService.save(clientData);
        });
        // then
    }

    @Test
    public void shouldThrowExceptionWhenRepositoryFailsInSavingClient() throws Exception {
        // given
        given(scoreFactory.get()).willReturn(SCORE);

        given(clientData.getType()).willReturn(CLIENT_TYPE_ID);
        given(clientData.getDocument()).willReturn(DOCUMENT);

        given(clientData.getName()).willReturn(NAME);
        Client client = new Client(NAME, ClientType.LEGAL, DOCUMENT, SCORE);
        given(validationService.validateClientDocument(ClientType.LEGAL, DOCUMENT)).willReturn(Boolean.TRUE);
        given(repository.save(client)).willThrow(DataIntegrityViolationException.class);
        // given(accountService.create(savedClient)).willReturn(account);
        // given(mapper.map(savedClient)).willReturn(clientInformation);
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            clientService.save(clientData);
        });
        // then
    }

    @Test
    public void shouldFindAllClientsInDatabase() {
        // given
        given(repository.findAll()).willReturn(Lists.newArrayList(savedClient));
        given(mapper.map(savedClient)).willReturn(clientInformation);
        // when
        Collection<ClientInformation> actualValues = clientService.findAll();
        // then
        assertThat(actualValues, containsInAnyOrder(clientInformation));
    }

    @Test
    public void shouldReturnEmptyCollectionWhenDatabaseDoesNotHaveClient() {
        // given
        given(repository.findAll()).willReturn(Lists.newArrayList());
        // when
        Collection<ClientInformation> actualValues = clientService.findAll();
        // then
        assertThat(actualValues, empty());
    }

}
