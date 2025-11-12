package com.igorfabricia.service;

import com.igorfabricia.model.Presente;
import com.igorfabricia.repository.PresenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresenteService {

    @Autowired
    private PresenteRepository presenteRepository;

    public List<Presente> listarTodos() {
        return presenteRepository.findAll();
    }

    public Optional<Presente> buscarPorId(Long id) {
        return presenteRepository.findById(id);
    }

    public Presente salvar(Presente presente) {
        return presenteRepository.save(presente);
    }

    public Presente atualizar(Long id, Presente presenteAtualizado) {
        return presenteRepository.findById(id)
                .map(presente -> {
                    presente.setNome(presenteAtualizado.getNome());
                    presente.setDescricao(presenteAtualizado.getDescricao());
                    presente.setUrlImagem(presenteAtualizado.getUrlImagem());
                    presente.setStatus(presenteAtualizado.getStatus());
                    return presenteRepository.save(presente);
                })
                .orElseThrow(() -> new RuntimeException("Presente não encontrado"));
    }

    public void deletar(Long id) {
        presenteRepository.deleteById(id);
    }

    public Presente reservar(Long id) {
        return presenteRepository.findById(id)
                .map(presente -> {
                    presente.reservar();
                    return presenteRepository.save(presente);
                })
                .orElseThrow(() -> new RuntimeException("Presente não encontrado"));
    }

    public Presente atualizarStatus(Long id, String novoStatus) {
        return presenteRepository.findById(id)
                .map(presente -> {
                    presente.atualizarStatus(novoStatus);
                    return presenteRepository.save(presente);
                })
                .orElseThrow(() -> new RuntimeException("Presente não encontrado"));
    }
}

