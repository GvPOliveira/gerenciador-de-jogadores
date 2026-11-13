package br.senac.tads.dsw.gerenciadorDeJogadores.controller;

import br.senac.tads.dsw.gerenciadorDeJogadores.dto.JogadorEntradaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.dto.JogadorRespostaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.service.JogadorService;
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
    public List<JogadorRespostaDto> buscarJogadores() {
        return jogadorService.buscarTodos();
    }

    @GetMapping("/{id}")
    public JogadorRespostaDto buscarJogadorPorId(@PathVariable Long id) {
        return jogadorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorRespostaDto> alterarJogador(@PathVariable Long id, @Valid @RequestBody JogadorEntradaDto jogadorEntradaDto) {
        JogadorRespostaDto jogadorAlterado = jogadorService.alterar(id, jogadorEntradaDto);
        return ResponseEntity.ok(jogadorAlterado);
    }


    @PostMapping
    public ResponseEntity<?> salvarJogador(@Valid @RequestBody JogadorEntradaDto jogadorEntradaDto) {
        JogadorRespostaDto jogadorRespostaDto = jogadorService.salvar(jogadorEntradaDto);
        URI location = ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("/jogadores/{id}").
                buildAndExpand(jogadorRespostaDto.getId()).
                toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirJogador(@PathVariable Long id) {
        jogadorService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
