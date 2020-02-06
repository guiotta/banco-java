package br.com.otta.bank.credit.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.model.ScoreInformation;
import br.com.otta.bank.credit.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;

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

    @Operation(description = "Lista todas as informações de Score que estão na base.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ScoreInformation>> findAll() {
        return ResponseEntity.ok(scoreService.findAll());
    }

    @Operation(description = "Atualiza a base utilizando as informações do objeto passado como paramêtro.")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreInformation> update(@Valid @RequestBody ScoreData scoreData) {
        return ResponseEntity.ok(scoreService.update(scoreData));
    }

    @Operation(description = "Cria um novo elemento na base utilizando as informações do objeto passado como paramêtro.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreInformation> insert(@Valid @RequestBody ScoreData scoreData) {
        return ResponseEntity.ok(scoreService.insert(scoreData));
    }
}
