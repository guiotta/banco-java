package br.com.otta.bank.account.factory;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.annotations.VisibleForTesting;

/**
 * Componente para criar o número da conta, de forma randômica.
 * 
 * @author Guilherme
 *
 */
@Component
public class AccountNumberFactory {
    private static final int EXCLUSIVE_MAX_VALUE = 1000000;
    private static final int STRING_MAX_SIZE = 6;
    private static final char PAD_CHAR = '0';

    public String get() {
        int number = generateRandomNumber();

        return StringUtils.leftPad(String.valueOf(number), STRING_MAX_SIZE, PAD_CHAR);
    }

    @VisibleForTesting
    protected int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(EXCLUSIVE_MAX_VALUE);
    }

}
