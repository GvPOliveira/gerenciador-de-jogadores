package com.gerenciadorDeJogadores.gerenciadorDeJogadores.controller;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.service.JogadorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {


    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public List<JogadorDTO> getJogadores(){
        return jogadorService.getAll();
    }



    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Jogador jogador) {
       JogadorDTO jogadorDTO = jogadorService.save(jogador);
        URI location = ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("/jogadores/{nome}").
                buildAndExpand(jogadorDTO.getNome()).
                toUri();
        return ResponseEntity.created(location).build();
    }


}
