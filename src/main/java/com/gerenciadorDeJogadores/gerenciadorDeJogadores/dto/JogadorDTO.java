package com.gerenciadorDeJogadores.gerenciadorDeJogadores.dto;


public class JogadorDTO {

    private Long id;

    private String nome;

    private Integer numeroCamisa;

    private TimeDTO time;

    private boolean lesionado;

    private String descricao;


    public JogadorDTO() {
    }

    public JogadorDTO(Long id, String nome, Integer numeroCamisa, TimeDTO time, boolean lesionado, String descricao) {
        this.id = id;
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

    public TimeDTO getTime() {
        return time;
    }

    public void setTime(TimeDTO time) {
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
