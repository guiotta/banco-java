package br.com.otta.bank.credit.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otta.bank.credit.mapper.ScoreInformationMapper;
import br.com.otta.bank.credit.model.ScoreInformation;
import br.com.otta.bank.credit.repository.ScoreRepository;

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

    @Autowired
    public ScoreService(ScoreRepository repository, ScoreInformationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Collection<ScoreInformation> findAll() {
        return repository.findAll().stream()
                .map((score) -> mapper.map(score))
                .collect(Collectors.toList());
    }
}
