package br.com.otta.bank.credit.filter;

import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.entity.Score;

/**
 * Componente para filtar se um determinado valor está dentro do range de um {@link Score}.</br>
 * O range do Score é definido pelas propriedade {@link Score#getMinimal()} e {@link Score#getMaximal()}.
 * @author Guilherme
 *
 */
@Component
public class ScoreRangeFilter {
    public boolean filter(int value, Score score) {
        return value >= score.getMinimal() && value <= score.getMaximal();
    }
}
