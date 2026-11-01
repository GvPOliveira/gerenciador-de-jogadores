package com.gerenciadorDeJogadores.gerenciadorDeJogadores.controller;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.exception.JogadorNotFound;
import com.gerenciadorDeJogadores.gerenciadorDeJogadores.exception.TimeNotFound;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class TratamentoDeExceptions {

    @ExceptionHandler(JogadorNotFound.class)
    public ResponseEntity<ProblemDetail> JogadorNotFoundException(JogadorNotFound jogadorNotFound) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404),
                jogadorNotFound.getMessage());
        return ResponseEntity.of(pb).build();

    }
    @ExceptionHandler(TimeNotFound.class)
    public ResponseEntity<ProblemDetail> timeNotFound(TimeNotFound timeNotFound) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404),
                timeNotFound.getMessage());
        return ResponseEntity.of(pb).build();

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> errorValid(MethodArgumentNotValidException errorValid){
        List<FieldError> erro = errorValid.getFieldErrors();
        Map<String, String> erros = new HashMap<>();
        for(FieldError f : erro){
            erros.put(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(erros);
    }

}
