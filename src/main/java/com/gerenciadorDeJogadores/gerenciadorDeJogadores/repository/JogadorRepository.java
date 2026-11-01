package com.gerenciadorDeJogadores.gerenciadorDeJogadores.repository;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Optional<Jogador> findById(Long id);
}
