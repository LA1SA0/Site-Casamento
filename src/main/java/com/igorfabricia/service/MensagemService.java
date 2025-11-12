package com.igorfabricia.service;

import com.igorfabricia.model.Mensagem;
import com.igorfabricia.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Mensagem> listarTodas() {
        return mensagemRepository.findAll();
    }

    public Optional<Mensagem> buscarPorId(Long id) {
        return mensagemRepository.findById(id);
    }

    public Mensagem salvar(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    public Mensagem atualizar(Long id, Mensagem mensagemAtualizada) {
        return mensagemRepository.findById(id)
                .map(mensagem -> {
                    mensagem.setTexto(mensagemAtualizada.getTexto());
                    return mensagemRepository.save(mensagem);
                })
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
    }

    public void deletar(Long id) {
        mensagemRepository.deleteById(id);
    }

    public String gerarMensagemAleatoria(Long id) {
        return mensagemRepository.findById(id)
                .map(mensagem -> Mensagem.gerarMensagemAleatoria(
                        mensagem.getConvidado().getNomeConvidado(),
                        Boolean.TRUE.equals(mensagem.getConvidado().getRespostaPresenca())
                ))
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
    }

}

