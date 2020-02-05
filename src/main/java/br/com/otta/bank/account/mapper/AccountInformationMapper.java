package br.com.otta.bank.account.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.model.AccountInformation;
import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.extractor.LimitValuesExtractor;
import br.com.otta.bank.credit.model.LimitValues;

/**
 * Componente para converter informações em um {@link AccountInformation}.
 *
 * @author Guilherme
 *
 */
@Component
public class AccountInformationMapper {
    private LimitValuesExtractor extractor;

    @Autowired
    public AccountInformationMapper(LimitValuesExtractor extractor) {
        this.extractor = extractor;
    }

    public AccountInformation map(Account account, Score score) {
        String name = account.getClient().getName();
        String document = account.getClient().getDocument();
        LimitValues limitValues = extractor.extract(score);

        return new AccountInformation(account.getId(), name, document, account.getType().getId(),
                limitValues.getOverdraft(), limitValues.getCreditCardLimit());
    }
}
