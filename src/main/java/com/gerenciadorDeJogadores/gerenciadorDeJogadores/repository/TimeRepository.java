package com.gerenciadorDeJogadores.gerenciadorDeJogadores.repository;

import com.gerenciadorDeJogadores.gerenciadorDeJogadores.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    public Optional<Time> findById(Long id);
}
