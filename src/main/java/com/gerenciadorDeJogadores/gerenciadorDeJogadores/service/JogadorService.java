package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorRespostaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorEntradaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;

import java.util.List;

public interface JogadorService {
    public JogadorRespostaDto save(JogadorEntradaDto jogadorEntradaDto);
    public List<JogadorRespostaDto> getAll();
    public Jogador get(Long id);
    public JogadorRespostaDto put(Long id, JogadorEntradaDto jogadorEntradaDto);
    public void delete(Long id);


}
