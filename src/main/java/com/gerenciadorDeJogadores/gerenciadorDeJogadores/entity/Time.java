package com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "times")
public class Time {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "O nome do time é obrigatório.")
    @Size(min = 2, message = "O nome do time deve ter no mínimo 2 caracteres.")
    @Column(nullable = false)
    private String nome;


    @OneToMany(mappedBy = "time", fetch = FetchType.LAZY)
    List<Jogador> jogadores = new ArrayList<>();

    public Time() {
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
