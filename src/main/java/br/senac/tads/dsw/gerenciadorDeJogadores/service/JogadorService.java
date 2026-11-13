package br.senac.tads.dsw.gerenciadorDeJogadores.service;

import br.senac.tads.dsw.gerenciadorDeJogadores.dto.JogadorEntradaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.dto.JogadorRespostaDto;

import java.util.List;

public interface JogadorService {
    JogadorRespostaDto salvar(JogadorEntradaDto jogadorEntradaDto);

    List<JogadorRespostaDto> buscarTodos();

    JogadorRespostaDto buscarPorId(Long id);

    JogadorRespostaDto alterar(Long id, JogadorEntradaDto jogadorEntradaDto);

    void excluir(Long id);


}
