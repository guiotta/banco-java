package br.com.otta.bank.account.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.otta.bank.account.model.AccountInformation;
import br.com.otta.bank.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Classe para controlar o acesso aos recursos das funcionalidades de contas.
 *
 * @author Guilherme
 *
 */
@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @Operation(description = "Lista as informações sobre as contas dos clientes na base.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AccountInformation>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }
}
