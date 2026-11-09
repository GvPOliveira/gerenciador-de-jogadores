package com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jogadores")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer numeroCamisa;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Time time;

    @Column(nullable = false)
    private boolean lesionado;


    private String descricao;


    public Jogador(){}

    public Jogador(String nome, Integer numeroCamisa, Time time, boolean lesionado, String descricao) {
        this.nome = nome;
        this.numeroCamisa = numeroCamisa;
        this.time = time;
        this.lesionado = lesionado;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
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

    public Integer getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(Integer numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isLesionado() {
        return lesionado;
    }

    public void setLesionado(boolean lesionado) {
        this.lesionado = lesionado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
