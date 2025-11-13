package com.igorfabricia.service;

import com.igorfabricia.model.Noivos;
import com.igorfabricia.repository.NoivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class NoivosService {

    @Autowired
    private NoivosRepository noivosRepository;

    public Noivos autenticar(String usuario, String senha) {
        Optional<Noivos> noivos = noivosRepository.findByUsuarioAndSenha(usuario, senha);
        return noivos.orElse(null);
    }

    public Noivos buscarUnicoCasal() {
        return noivosRepository.findAll().stream().findFirst().orElse(null);
    }

    public Noivos atualizarDados(Noivos atualizado) {
        Noivos existente = buscarUnicoCasal();
        if (existente == null) {
            return noivosRepository.save(atualizado);
        }

        existente.setNomeNoiva(atualizado.getNomeNoiva());
        existente.setNomeNoivo(atualizado.getNomeNoivo());
        existente.setDataCasamento(atualizado.getDataCasamento());
        existente.setUsuario(atualizado.getUsuario());
        existente.setSenha(atualizado.getSenha());

        return noivosRepository.save(existente);
    }

    public Noivos atualizarDataCasamento(LocalDate novaData) {
        Noivos noivos = buscarUnicoCasal();
        noivos.setDataCasamento(novaData);
        return noivosRepository.save(noivos);
    }

    public int calcularDiasRestantes() {
        return buscarUnicoCasal().calcularDiasRestantes();
    }

    public int calcularDiasCasados() {
        return buscarUnicoCasal().calcularDiasCasados();
    }
}

