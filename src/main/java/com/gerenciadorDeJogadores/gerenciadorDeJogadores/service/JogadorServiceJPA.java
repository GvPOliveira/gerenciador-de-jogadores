package com.gerenciadorDeJogadores.gerenciadorDeJogadores.service;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.JogadorDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto.TimeDTO;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Jogador;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.exception.JogadorNotFound;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.repository.JogadorRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class JogadorServiceJPA implements JogadorService {
    private final JogadorRepository jogadorRepository;

    public JogadorServiceJPA(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }


    public JogadorDTO save(Jogador jogador) {
        jogadorRepository.save(jogador);
        TimeDTO timeDTO = new TimeDTO(jogador.getTime().getId(), jogador.getTime().getNome());

        return new JogadorDTO(jogador.getId(), jogador.getNome(), jogador.getNumeroCamisa(),
                timeDTO, jogador.isLesionado(), jogador.getDescricao());
    }

    public Jogador get(Long id) {
        Optional<Jogador> optJogador = jogadorRepository.findById(id);
        if (optJogador.isEmpty()) {
            throw new JogadorNotFound("Jogador não encontrado.");
        } else {
            return optJogador.get();
        }
    }

    public JogadorDTO put(Long id, Jogador jogadorAlterado) {
        Optional<Jogador> optJogador = jogadorRepository.findById(id);
        if (optJogador.isEmpty()) {
            throw new JogadorNotFound("Jogador não encontrado.");
        } else {
            Jogador jogador = optJogador.get();
            jogador.setTime(jogadorAlterado.getTime());
            jogador.setLesionado(jogadorAlterado.isLesionado());
            jogador.setNome(jogadorAlterado.getNome());
            jogador.setDescricao(jogadorAlterado.getDescricao());
            jogador.setNumeroCamisa(jogadorAlterado.getNumeroCamisa());
            Jogador jogadorSalvado = jogadorRepository.save(jogador);

            return new JogadorDTO(jogadorSalvado.getId(),
                    jogadorSalvado.getNome(), jogadorSalvado.getNumeroCamisa(),
                    new TimeDTO(jogadorSalvado.getTime().getId(),
                    jogadorSalvado.getTime().getNome()),
                    jogadorSalvado.isLesionado(), jogadorSalvado.getDescricao());
        }
    }

    public void delete(Long id){
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        if(jogador.isEmpty()){
            throw new JogadorNotFound("Jogador não encontrado.");
        }
        jogadorRepository.delete(jogador.get());
    }


    public List<JogadorDTO> getAll() {
        return jogadorRepository.findAll().stream().map(jogador -> new JogadorDTO(jogador.getId(), jogador.getNome(),
                jogador.getNumeroCamisa(), new TimeDTO(jogador.getTime().getId(), jogador.getTime().getNome()),
                jogador.isLesionado(), jogador.getDescricao())).toList();
    }

}
