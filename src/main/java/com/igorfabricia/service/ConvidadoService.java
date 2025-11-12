package com.igorfabricia.service;

import com.igorfabricia.model.Convidado;
import com.igorfabricia.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvidadoService {

    @Autowired
    private ConvidadoRepository convidadoRepository;

    public List<Convidado> listarTodos() {
        return convidadoRepository.findAll();
    }

    public Optional<Convidado> buscarPorId(Long id) {
        return convidadoRepository.findById(id);
    }

    public Convidado salvar(Convidado convidado) {
        return convidadoRepository.save(convidado);
    }

    public Convidado atualizar(Long id, Convidado convidadoAtualizado) {
        return convidadoRepository.findById(id)
                .map(convidado -> {
                    convidado.setNomeConvidado(convidadoAtualizado.getNomeConvidado());
                    convidado.setRespostaPresenca(convidadoAtualizado.getRespostaPresenca());
                    return convidadoRepository.save(convidado);
                })
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
    }

    public void deletar(Long id) {
        convidadoRepository.deleteById(id);
    }

    public Convidado confirmarPresenca(Long id) {
        return convidadoRepository.findById(id)
                .map(convidado -> {
                    convidado.confirmarPresenca();
                    return convidadoRepository.save(convidado);
                })
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
    }

    public Convidado recusarPresenca(Long id) {
        return convidadoRepository.findById(id)
                .map(convidado -> {
                    convidado.recusarPresenca();
                    return convidadoRepository.save(convidado);
                })
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
    }
}