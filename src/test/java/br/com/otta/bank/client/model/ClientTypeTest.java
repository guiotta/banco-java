package br.com.otta.bank.client.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários de {@link ClientType}.
 * 
 * @author Guilherme
 *
 */
public class ClientTypeTest {

    @Test
    public void shouldGetPhysicalType() {
        // given
        int value = 0;
        // when
        ClientType actualValue = ClientType.getClientType(value);
        // then
        assertEquals(ClientType.PHYSICAL, actualValue);
    }

    @Test
    public void shouldGetLegalType() {
        // given
        int value = 1;
        // when
        ClientType actualValue = ClientType.getClientType(value);
        // then
        assertEquals(ClientType.LEGAL, actualValue);
    }

    @Test
    public void shouldThrowExceptionFOrUnkwnownClientTypeId() {
        // given
        int value = -1;
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ClientType.getClientType(value);
        });
        // then
    }

}
