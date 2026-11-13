package br.senac.tads.dsw.gerenciadorDeJogadores.repository;

import br.senac.tads.dsw.gerenciadorDeJogadores.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    public Optional<Time> findById(Long id);
}
