package br.com.otta.bank.client.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Lists;

import br.com.otta.bank.client.model.ClientData;
import br.com.otta.bank.client.model.ClientInformation;
import br.com.otta.bank.client.service.ClientService;

/**
 * Testes unitários do componente {@link ClientController}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {
    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientData clientData;
    @Mock
    private ClientInformation clientInformation;

    @BeforeEach
    protected void setUp() throws Exception {

    }

    @Test
    public void shouldCorrectlyCallServiceWhenSaveIsCalled() throws Exception {
        // given
        given(clientService.save(clientData)).willReturn(clientInformation);
        // when
        ResponseEntity<ClientInformation> actualValue = clientController.save(clientData);
        // then
        assertEquals(clientInformation, actualValue.getBody());
    }

    @Test
    public void shouldCorrectlyCallServiceWhenFindAllIsCalled() throws Exception {
        // given
        given(clientService.findAll()).willReturn(Lists.newArrayList(clientInformation));
        // when
        ResponseEntity<Collection<ClientInformation>> actualValues = clientController.findAll();
        // then
        assertThat(actualValues.getBody(), contains(clientInformation));
    }

}
