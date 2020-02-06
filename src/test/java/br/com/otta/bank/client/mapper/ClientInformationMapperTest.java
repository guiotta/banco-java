package br.com.otta.bank.client.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.model.ClientInformation;
import br.com.otta.bank.client.model.ClientType;

/**
 * Testes unitários do componente {@link ClientInformationMapper}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ClientInformationMapperTest {
    private static final Long ID = Long.valueOf(1l);
    private static final String NAME = "name";
    private static final String DOCUMENT = "document";
    private static final ClientType TYPE = ClientType.PHYSICAL;
    private static final Integer SCORE = Integer.valueOf(5);
    
    private ClientInformationMapper mapper;

    @Mock
    private Client client;

    @BeforeEach
    protected void setUp() throws Exception {
        this.mapper = new ClientInformationMapper();
    }

    @Test
    public void shouldCorrectlyMapClientInformation() throws Exception {
        // given
        ClientInformation expectedValue = new ClientInformation(ID, NAME, DOCUMENT, TYPE.getLabel(), SCORE);
        
        given(client.getId()).willReturn(ID);
        given(client.getName()).willReturn(NAME);
        given(client.getDocument()).willReturn(DOCUMENT);
        given(client.getScore()).willReturn(SCORE);
        given(client.getType()).willReturn(TYPE);
        // when
        ClientInformation actualValue = mapper.map(client);
        // then
        assertEquals(expectedValue, actualValue);
    }

}
