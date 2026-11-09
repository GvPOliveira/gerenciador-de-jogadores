package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorEntradaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorRespostaDto;

import java.util.List;

public interface JogadorService {
    JogadorRespostaDto salvar(JogadorEntradaDto jogadorEntradaDto);

    List<JogadorRespostaDto> buscarTodos();

    JogadorRespostaDto buscarPorId(Long id);

    JogadorRespostaDto alterar(Long id, JogadorEntradaDto jogadorEntradaDto);

    void excluir(Long id);


}
