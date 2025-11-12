package com.igorfabricia.repository;

import com.igorfabricia.model.Noivos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoivosRepository extends JpaRepository<Noivos, Long> {
    Noivos findByUsuario(String usuario);
}
