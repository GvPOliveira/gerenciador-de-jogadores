package com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto;

import jakarta.validation.constraints.*;

public class JogadorEntradaDto {

    @NotBlank(message = "O nome do jogador é obrigatório.")
    @Size(min = 2, message = "O nome do jogador deve ter no mínimo 2 caracteres.")
    private String nome;

    @NotNull(message = "O número da camisa é obrigatório.")
    @Min(value = 1, message = "O número da camisa deve ser no mínimo 1.")
    @Max(value = 99, message = "O número da camisa deve ser no máximo 99.")
    private Integer numeroCamisa;


    @NotNull(message = "O time é obrigatório.")
    private Long idTime;

    private boolean lesionado;


    private String descricao;

    public JogadorEntradaDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            this.nome = nome;
        } else {
            this.nome = nome.trim();
        }

    }

    public Integer getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(Integer numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
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
