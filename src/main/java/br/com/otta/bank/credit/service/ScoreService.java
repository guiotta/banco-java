package br.com.otta.bank.credit.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.factory.ScoreFactory;
import br.com.otta.bank.credit.mapper.ScoreInformationMapper;
import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.model.ScoreInformation;
import br.com.otta.bank.credit.repository.ScoreRepository;
import br.com.otta.bank.credit.validation.ScoreValidation;

/**
 * Classe de serviço para conter os comportamentos necessários para operar os Scores.
 *
 * @author Guilherme
 *
 */
@Service
public class ScoreService {
    private final ScoreRepository repository;
    private final ScoreInformationMapper mapper;
    private final ScoreValidation validation;
    private final ScoreFactory scoreFactory;

    @Autowired
    public ScoreService(ScoreRepository repository, ScoreInformationMapper mapper, ScoreValidation validation,
            ScoreFactory scoreFactory) {
        this.repository = repository;
        this.mapper = mapper;
        this.validation = validation;
        this.scoreFactory = scoreFactory;
    }

    public Collection<ScoreInformation> findAll() {
        return repository.findAll().stream()
                .map((score) -> mapper.map(score))
                .collect(Collectors.toList());
    }

    /**
     * Para atualizar os dados, os antigos serão removidos e um novo {@link Score} será populado e salvo na base.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(ScoreData scoreData) {
        if(repository.findById(scoreData.getId()).isPresent()) {
            validation.validate(scoreData);

            repository.deleteById(scoreData.getId());
            Score score = scoreFactory.create(scoreData);
            repository.save(score);
        } else {
            throw new IllegalArgumentException(String.format("Could not find Score with id: %d.", scoreData.getId()));
        }
    }
}
