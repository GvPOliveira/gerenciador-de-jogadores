package com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TimeEntradaDto {

    @NotBlank(message = "O nome do time é obrigatório.")
    @Size(min = 2, message = "O nome do time deve ter no mínimo 2 caracteres.")
    private String nome;

    public TimeEntradaDto() {
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
}
