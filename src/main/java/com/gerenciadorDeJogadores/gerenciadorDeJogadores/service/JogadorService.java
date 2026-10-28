package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;

import java.util.List;

public interface JogadorService {
    public JogadorDTO save(Jogador jogador);
    public List<JogadorDTO> getAll();


}
