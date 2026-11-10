package com.gerenciadorDeJogadores.gerenciadorDeJogadores.controller;


import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeEntradaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeRespostaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.service.TimeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;


    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping
    public List<TimeRespostaDto> buscarTodos() {
        return timeService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody TimeEntradaDto timeEntradaDto) {
        TimeRespostaDto timeRespostaDto = timeService.salvar(timeEntradaDto);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/times/{id}").buildAndExpand(timeRespostaDto.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }




}
