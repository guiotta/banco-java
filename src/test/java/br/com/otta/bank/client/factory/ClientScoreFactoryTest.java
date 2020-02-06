package br.com.otta.bank.client.factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários do componente {@link ClientScoreFactory}.
 *
 * @author Guilherme
 *
 */
public class ClientScoreFactoryTest {
    private ClientScoreFactory factory;

    @BeforeEach
    protected void setUp() throws Exception {
        this.factory = new ClientScoreFactory();
    }

    @Test
    public void shouldCorrectlyGenerateValue() {
        // given
        for (int index = 0; index < 100; index++) {
            // when
            int actualValue = factory.get();
            // then
            assertTrue(actualValue < 10);
        }
    }

}
