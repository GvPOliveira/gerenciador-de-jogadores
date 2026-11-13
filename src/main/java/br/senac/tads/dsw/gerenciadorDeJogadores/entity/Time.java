package br.senac.tads.dsw.gerenciadorDeJogadores.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "times")
public class Time {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nome;


    @OneToMany(mappedBy = "time", fetch = FetchType.LAZY)
    List<Jogador> jogadores = new ArrayList<>();

    public Time() {
    }
    public Time(String nome) {
        this.nome = nome;
    }


    public Long getId() {
        return id;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
