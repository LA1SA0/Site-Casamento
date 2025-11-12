package com.igorfabricia.service;

import com.igorfabricia.model.Noivos;
import com.igorfabricia.repository.NoivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoivosService {

    @Autowired
    private NoivosRepository noivosRepository;

    public Noivos getNoivos() {
        return noivosRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Noivos não cadastrados"));
    }

    public Noivos atualizarDataCasamento(LocalDate novaData) {
        Noivos noivos = getNoivos();
        noivos.setDataCasamento(novaData);
        return noivosRepository.save(noivos);
    }

    public int calcularDiasRestantes() {
        return getNoivos().calcularDiasRestantes();
    }

    public int calcularDiasCasados() {
        return getNoivos().calcularDiasCasados();
    }
}
