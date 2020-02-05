package br.com.otta.bank.account.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe para configurações necessárias para as funcionalidades ligadas às contas dos clientes.
 *
 * @author Guilherme
 *
 */
@Configuration
public class AccountConfiguration {
    public static final String AGENCY_BEAN_NAME = "agencyNumber";

    @Value("${bank.values.agency}")
    private String agencyNumber;

    @Bean(name = AGENCY_BEAN_NAME)
    public String getAgencyNumber() {
        return agencyNumber;
    }
}
