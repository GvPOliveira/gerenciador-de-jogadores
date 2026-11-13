package br.senac.tads.dsw.gerenciadorDeJogadores.service;

import br.senac.tads.dsw.gerenciadorDeJogadores.dto.TimeEntradaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.dto.TimeRespostaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.entity.Time;
import br.senac.tads.dsw.gerenciadorDeJogadores.exception.TimeNotFound;
import br.senac.tads.dsw.gerenciadorDeJogadores.repository.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TimeServiceJPA implements TimeService {

    private final TimeRepository timeRepository;

    public TimeServiceJPA(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Transactional
    public TimeRespostaDto salvar(TimeEntradaDto timeEntradaDto) {
        Time timeSalvado = timeRepository.save(toEntity(timeEntradaDto));
        return new TimeRespostaDto(timeSalvado.getId(), timeSalvado.getNome());
    }

    @Transactional(readOnly = true)
    public Time buscarPorId(Long id) {
        Optional<Time> time = timeRepository.findById(id);
        if (time.isEmpty()) {
            throw new TimeNotFound("Esse time não foi encontrado.");
        } else {
            return time.get();
        }
    }

    @Transactional(readOnly = true)
    public List<TimeRespostaDto> buscarTodos() {
        return timeRepository.findAll().stream().map(time -> new TimeRespostaDto(time.getId(), time.getNome())).toList();
    }


    private Time toEntity(TimeEntradaDto timeEntradaDto) {
        return new Time(timeEntradaDto.getNome());
    }

}
