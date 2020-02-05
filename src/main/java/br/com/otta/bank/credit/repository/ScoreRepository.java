package br.com.otta.bank.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otta.bank.credit.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {

}
