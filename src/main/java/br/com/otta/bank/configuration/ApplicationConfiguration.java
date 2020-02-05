package br.com.otta.bank.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.safeguard.check.SafeguardCheck;

/**
 * Classe para controlar a criação dos beans necessários para a aplicação.
 * @author Guilherme
 *
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public SafeguardCheck safeguardCheck() {
        return new SafeguardCheck();
    }
}
