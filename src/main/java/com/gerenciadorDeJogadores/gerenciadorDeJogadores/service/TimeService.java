package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;


import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Time;

import java.util.List;

public interface TimeService {
    public TimeDto save(Time time);
    public List<TimeDto> getAll();
    public Time getTime(Long id);
}
