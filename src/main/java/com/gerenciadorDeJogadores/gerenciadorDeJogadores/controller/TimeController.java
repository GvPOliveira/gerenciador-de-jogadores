package com.gerenciadorDeJogadores.gerenciadorDeJogadores.controller;


import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDTO;
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
    public List<TimeDTO> getAll() {
        return timeServiceJPA.getAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Time time) {
        TimeDTO timeDTO = timeServiceJPA.save(time);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/times/{nome}").buildAndExpand(timeDTO.getNome())
                .toUri();
        return ResponseEntity.created(location).build();


    }


}
