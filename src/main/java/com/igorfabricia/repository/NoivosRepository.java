package com.igorfabricia.repository;

import com.igorfabricia.model.Noivos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoivosRepository extends JpaRepository<Noivos, Long> {
    Optional<Noivos> findByUsuarioAndSenha(String usuario, String senha);
}
