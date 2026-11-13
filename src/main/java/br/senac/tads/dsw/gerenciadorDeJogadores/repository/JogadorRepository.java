package br.senac.tads.dsw.gerenciadorDeJogadores.repository;

import br.senac.tads.dsw.gerenciadorDeJogadores.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Optional<Jogador> findById(Long id);
}
