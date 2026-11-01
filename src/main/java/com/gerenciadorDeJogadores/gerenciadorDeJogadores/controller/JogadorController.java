package com.gerenciadorDeJogadores.gerenciadorDeJogadores.controller;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Time;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.service.JogadorService;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.service.TimeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/jogadores")
public class JogadorController {


    private final JogadorService jogadorService;
    private final TimeService timeService;

    public JogadorController(JogadorService jogadorService, TimeService timeService) {
        this.jogadorService = jogadorService;
        this.timeService = timeService;
    }

    @GetMapping
    public List<JogadorDTO> getJogadores() {
        return jogadorService.getAll();
    }

    @GetMapping("/{id}")
    public JogadorDTO getJogador(@PathVariable Long id) {
        Jogador jogador = jogadorService.get(id);
        return new JogadorDTO(jogador.getId(), jogador.getNome(), jogador.getNumeroCamisa(),
                new TimeDTO(jogador.getTime().getId(), jogador.getTime().getNome()),
                jogador.isLesionado(), jogador.getDescricao());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorDTO> alterarJogador(@PathVariable Long id,@Valid @RequestBody Jogador jogador) {
        Time time = timeService.getTime(jogador.getTime().getId());
        jogador.setTime(time);
        JogadorDTO jogadorAlterado = jogadorService.put(id, jogador);
        return ResponseEntity.ok(jogadorAlterado);  }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Jogador jogador) {
        Time time = timeService.getTime(jogador.getTime().getId());
        jogador.setTime(time);
        JogadorDTO jogadorDTO = jogadorService.save(jogador);
        URI location = ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("/jogadores/{id}").
                buildAndExpand(jogadorDTO.getId()).
                toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirJogador(@PathVariable Long id) {
        jogadorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
