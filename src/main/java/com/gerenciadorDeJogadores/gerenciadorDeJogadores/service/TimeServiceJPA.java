package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Time;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.exception.TimeNotFound;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeServiceJPA implements TimeService {

    private final TimeRepository timeRepository;

    public TimeServiceJPA(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public TimeDto save(Time time) {
        Time timeSalvado = timeRepository.save(time);
        return new TimeDto(timeSalvado.getId(), timeSalvado.getNome());
    }

    public Time getTime(Long id) {
        Optional<Time> time = timeRepository.findById(id);
        if (time.isEmpty()) {
            throw new TimeNotFound("Esse time não foi encontrado.");
        } else {
            return time.get();
        }
    }

    public List<TimeDto> getAll() {
        return timeRepository.findAll().stream().map(time -> new TimeDto(time.getId(),time.getNome())).toList();
    }

}
