package com.gerenciadorDeJogadores.gerenciadorDeJogadores.controller;


import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Time;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.service.TimeServiceJPA;
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

    private final TimeServiceJPA timeServiceJPA;


    public TimeController(TimeServiceJPA timeServiceJPA) {
        this.timeServiceJPA = timeServiceJPA;
    }

    @GetMapping
    public List<TimeDto> buscarTodos() {
        return timeServiceJPA.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Time time) {
        TimeDto timeDto = timeServiceJPA.salvar(time);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/times/{nome}").buildAndExpand(timeDto.getNome())
                .toUri();
        return ResponseEntity.created(location).build();


    }


}
