package br.senac.tads.dsw.gerenciadorDeJogadores.service;

import br.senac.tads.dsw.gerenciadorDeJogadores.dto.JogadorEntradaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.dto.JogadorRespostaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.dto.TimeRespostaDto;
import br.senac.tads.dsw.gerenciadorDeJogadores.entity.Jogador;
import br.senac.tads.dsw.gerenciadorDeJogadores.entity.Time;
import br.senac.tads.dsw.gerenciadorDeJogadores.exception.JogadorNotFound;
import br.senac.tads.dsw.gerenciadorDeJogadores.repository.JogadorRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class JogadorServiceJPA implements JogadorService {
    private final JogadorRepository jogadorRepository;
    private final TimeService timeService;

    public JogadorServiceJPA(JogadorRepository jogadorRepository, TimeService timeService) {
        this.jogadorRepository = jogadorRepository;
        this.timeService = timeService;
    }

    @Transactional
    public JogadorRespostaDto salvar(JogadorEntradaDto jogadorEntradaDto) {
        Jogador jogador = toEntity(jogadorEntradaDto);
        Jogador jogadorSalvado = jogadorRepository.save(jogador);
        return toDto(jogadorSalvado);
    }

    @Transactional(readOnly = true)
    public JogadorRespostaDto buscarPorId(Long id) {
        Optional<Jogador> optJogador = jogadorRepository.findById(id);
        if (optJogador.isEmpty()) {
            throw new JogadorNotFound("Jogador não encontrado.");
        } else {
            return toDto(optJogador.get());
        }
    }

    @Transactional
    public JogadorRespostaDto alterar(Long id, JogadorEntradaDto jogadorAlterado) {
        Optional<Jogador> optJogador = jogadorRepository.findById(id);
        if (optJogador.isEmpty()) {
            throw new JogadorNotFound("Jogador não encontrado.");
        } else {
            Jogador jogador = atualizarDados(optJogador.get(), jogadorAlterado);
            Jogador jogadorSalvado = jogadorRepository.save(jogador);
            return toDto(jogadorSalvado);
        }
    }
    @Transactional
    public void excluir(Long id) {
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        if (jogador.isEmpty()) {
            throw new JogadorNotFound("Jogador não encontrado.");
        }
        jogadorRepository.delete(jogador.get());
    }

    @Transactional(readOnly = true)
    public List<JogadorRespostaDto> buscarTodos() {
        return jogadorRepository.findAll().stream().map(this::toDto).toList();
    }


    private JogadorRespostaDto toDto(Jogador jogador) {
        return new JogadorRespostaDto(jogador.getId(), jogador.getNome(), jogador.getNumeroCamisa(),
                new TimeRespostaDto(jogador.getTime().getId(), jogador.getTime().getNome()),
                jogador.isLesionado(), jogador.getDescricao());
    }

    private Jogador toEntity(JogadorEntradaDto jogadorEntradaDto) {
        Time time = timeService.buscarPorId(jogadorEntradaDto.getIdTime());
        return new Jogador(jogadorEntradaDto.getNome(), jogadorEntradaDto.getNumeroCamisa(),
                time, jogadorEntradaDto.isLesionado(), jogadorEntradaDto.getDescricao());
    }


    private Jogador atualizarDados(Jogador jogadorBanco, JogadorEntradaDto jogadorEntrada) {
        Time time = timeService.buscarPorId(jogadorEntrada.getIdTime());
        jogadorBanco.setTime(time);
        jogadorBanco.setLesionado(jogadorEntrada.isLesionado());
        jogadorBanco.setNome(jogadorEntrada.getNome());
        jogadorBanco.setDescricao(jogadorEntrada.getDescricao());
        jogadorBanco.setNumeroCamisa(jogadorEntrada.getNumeroCamisa());
        return jogadorBanco;
    }

}
