package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;


import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeEntradaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeRespostaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Time;

import java.util.List;

public interface TimeService {
    TimeRespostaDto salvar(TimeEntradaDto timeEntradaDto);

    List<TimeRespostaDto> buscarTodos();

    Time buscarPorId(Long id);
}
