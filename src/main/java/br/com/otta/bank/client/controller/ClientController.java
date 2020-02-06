package br.com.otta.bank.client.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.otta.bank.client.model.ClientData;
import br.com.otta.bank.client.model.ClientInformation;
import br.com.otta.bank.client.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Classe para controlar o acesso aos recursos com funcionalidades dos clientes.
 *
 * @author Guilherme
 *
 */
@RestController
@RequestMapping("client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(description = "Adiciona um cliente na base, utilizando as as informações passadas por paramêtro.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientInformation> save(@Valid @RequestBody ClientData clientData) {
        ClientInformation clientInformation = clientService.save(clientData);

        return ResponseEntity.ok(clientInformation);
    }

    @Operation(description = "Lista todos os clientes da base.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ClientInformation>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }
}
