package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;


import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Time;

import java.util.List;

public interface TimeService {
    public TimeDTO save(Time time);
    public List<TimeDTO> getAll();
    public Time getTime(Long id);
}
