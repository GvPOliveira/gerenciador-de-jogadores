package com.gerenciadorDeJogadores.gerenciadorDeJogadores.repository;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
