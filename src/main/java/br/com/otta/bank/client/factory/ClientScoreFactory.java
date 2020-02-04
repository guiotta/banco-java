package br.com.otta.bank.client.factory;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * Componente para criar o valor randômico do Score do Cliente.
 *
 * @author Guilherme
 *
 */
@Component
public class ClientScoreFactory {
    private static final int SCORE_EXCLUSIVE_MAX_LIMIT = 10;

    public int get() {
        Random random = new Random();
        return random.nextInt(SCORE_EXCLUSIVE_MAX_LIMIT);
    }
}
