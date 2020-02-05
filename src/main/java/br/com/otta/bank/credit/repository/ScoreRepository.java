package br.com.otta.bank.credit.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.otta.bank.credit.entity.Score;

/**
 * Repositório para controlar o acesso ao banco para a tabela de {@link Score Scores}.
 * @author Guilherme
 *
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query("SELECT s FROM Score s WHERE ?1 BETWEEN s.minimal AND s.maximal")
    Collection<Score> findByScoreRange(int value);

}
