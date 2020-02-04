package br.com.otta.bank.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.otta.bank.client.entity.Client;

/**
 * {@link Repository} para controlar o acesso aos {@link Client clientes} armazenados na base.
 * 
 * @author Guilherme
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByDocument(String document);
}
