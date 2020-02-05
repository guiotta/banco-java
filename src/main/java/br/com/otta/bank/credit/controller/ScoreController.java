package br.com.otta.bank.credit.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.model.ScoreInformation;
import br.com.otta.bank.credit.service.ScoreService;

/**
 * Classe para realizar o controle dos acesso às funcionalidades de Score.
 * @author Guilherme
 *
 */
@RestController
@RequestMapping("score")
public class ScoreController {
    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ScoreInformation> findAll() {
        return scoreService.findAll();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ScoreInformation update(@Valid @RequestBody ScoreData scoreData) {
        return scoreService.update(scoreData);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ScoreInformation insert(@Valid @RequestBody ScoreData scoreData) {
        return scoreService.insert(scoreData);
    }
}
