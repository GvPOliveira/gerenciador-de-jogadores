package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.repository.JogadorRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class JogadorServiceJPA implements JogadorService {
    private final JogadorRepository jogadorRepository;

    public JogadorServiceJPA(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }


    public JogadorDTO save(Jogador jogador) {
        jogadorRepository.save(jogador);
        return new JogadorDTO(jogador.getNome(), jogador.getNumeroCamisa(), jogador.getTime(), jogador.isLesionado(), jogador.getDescricao());
    }

    public List<JogadorDTO> getAll() {
        return jogadorRepository.findAll().stream().map(jogador -> new JogadorDTO(jogador.getNome(),
                jogador.getNumeroCamisa(), jogador.getTime(), jogador.isLesionado(), jogador.getDescricao())).toList();
    }

}
