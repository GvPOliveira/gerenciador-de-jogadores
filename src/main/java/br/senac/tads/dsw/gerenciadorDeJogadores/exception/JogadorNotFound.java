package br.senac.tads.dsw.gerenciadorDeJogadores.exception;

public class JogadorNotFound extends RuntimeException{
    public JogadorNotFound(String message){
        super(message);
    }
}
