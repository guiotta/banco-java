package br.com.otta.bank.credit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.model.ScoreInformation;
import br.com.otta.bank.credit.service.ScoreService;

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

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody ScoreData scoreData) {
        scoreService.update(scoreData);
    }
}
