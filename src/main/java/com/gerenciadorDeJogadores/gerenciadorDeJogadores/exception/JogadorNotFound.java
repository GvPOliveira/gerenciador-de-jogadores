package com.gerenciadorDeJogadores.gerenciadorDeJogadores.exception;

public class JogadorNotFound extends RuntimeException{
    public JogadorNotFound(String message){
        super(message);
    }
}
