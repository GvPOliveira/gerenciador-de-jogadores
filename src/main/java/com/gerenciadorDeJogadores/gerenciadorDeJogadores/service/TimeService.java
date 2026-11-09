package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;


import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Time;

import java.util.List;

public interface TimeService {
    TimeDto salvar(Time time);

    List<TimeDto> buscarTodos();

    Time buscarPorId(Long id);
}
