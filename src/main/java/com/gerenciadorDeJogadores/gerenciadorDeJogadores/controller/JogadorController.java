package com.gerenciadorDeJogadores.gerenciadorDeJogadores.controller;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorRespostaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorEntradaDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDto;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.service.JogadorService;
import jakarta.validation.Valid;
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

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public List<JogadorRespostaDto> getJogadores() {
        return jogadorService.getAll();
    }

    @GetMapping("/{id}")
    public JogadorRespostaDto getJogador(@PathVariable Long id) {
        Jogador jogador = jogadorService.get(id);
        return new JogadorRespostaDto(jogador.getId(), jogador.getNome(), jogador.getNumeroCamisa(),
                new TimeDto(jogador.getTime().getId(), jogador.getTime().getNome()),
                jogador.isLesionado(), jogador.getDescricao());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorRespostaDto> alterarJogador(@PathVariable Long id, @Valid @RequestBody JogadorEntradaDto jogadorEntradaDto) {
        JogadorRespostaDto jogadorAlterado = jogadorService.put(id, jogadorEntradaDto);
        return ResponseEntity.ok(jogadorAlterado);  }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody JogadorEntradaDto jogadorEntradaDto) {
        JogadorRespostaDto jogadorRespostaDto = jogadorService.save(jogadorEntradaDto);
        URI location = ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("/jogadores/{id}").
                buildAndExpand(jogadorRespostaDto.getId()).
                toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirJogador(@PathVariable Long id) {
        jogadorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
