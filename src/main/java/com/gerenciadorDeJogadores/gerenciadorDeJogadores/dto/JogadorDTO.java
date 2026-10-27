package com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto;


public class JogadorDTO {

    private String nome;

    private String numeroCamisa;

    private String time;

    private boolean lesionado;

    private String descricao;


    public JogadorDTO(){}

    public JogadorDTO(String nome, String numeroCamisa, String time, boolean lesionado, String descricao) {
        this.nome = nome;
        this.numeroCamisa = numeroCamisa;
        this.time = time;
        this.lesionado = lesionado;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(String numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isLesionado() {
        return lesionado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLesionado(boolean lesionado) {
        this.lesionado = lesionado;

    }
}
