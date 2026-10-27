package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.repository.JogadorRepository;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }


    public JogadorDTO save(Jogador jogador) {
        jogadorRepository.save(jogador);
        return new JogadorDTO(jogador.getNome(), jogador.getNumeroCamisa(), jogador.getTime(), jogador.isLesionado(), jogador.getDescricao());
    }


}
