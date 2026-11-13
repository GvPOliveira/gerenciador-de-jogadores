package br.senac.tads.dsw.gerenciadorDeJogadores.service;


import br.senac.tads.dsw.gerenciadorDeJogadores.dto.TimeEntradaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.dto.TimeRespostaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.entity.Time;

import java.util.List;

public interface TimeService {
    TimeRespostaDto salvar(TimeEntradaDto timeEntradaDto);

    List<TimeRespostaDto> buscarTodos();

    Time buscarPorId(Long id);
}
