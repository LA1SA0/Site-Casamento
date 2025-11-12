package com.igorfabricia.service;

import com.igorfabricia.model.Galeria;
import com.igorfabricia.repository.GaleriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GaleriaService {

    @Autowired
    private GaleriaRepository galeriaRepository;

    public List<Galeria> listarTodas() {
        return galeriaRepository.findAll();
    }

    public Optional<Galeria> buscarPorId(Long id) {
        return galeriaRepository.findById(id);
    }

    public Galeria salvar(Galeria galeria) {
        return galeriaRepository.save(galeria);
    }

    public Galeria atualizar(Long id, Galeria galeriaAtualizada) {
        return galeriaRepository.findById(id)
                .map(galeria -> {
                    galeria.setCategoria(galeriaAtualizada.getCategoria());
                    galeria.setNomeArquivo(galeriaAtualizada.getNomeArquivo());
                    return galeriaRepository.save(galeria);
                })
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));
    }

    public void deletar(Long id) {
        galeriaRepository.deleteById(id);
    }
}

