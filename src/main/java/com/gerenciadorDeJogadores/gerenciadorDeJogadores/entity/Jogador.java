package com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "jogadores")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do jogador é obrigatório.")
    @Size(min = 2, message = "O nome do jogador deve ter no mínimo 2 caracteres.")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O número da camisa é obrigatório.")
    @Min(value = 1, message = "O número da camisa deve ser no mínimo 1.")
    @Max(value = 99, message = "O número da camisa deve ser no máximo 99.")
    @Column(nullable = false)
    private Integer numeroCamisa;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Time time;

    @Column(nullable = false)
    private boolean lesionado;


    private String descricao;


    public Jogador(){}

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
