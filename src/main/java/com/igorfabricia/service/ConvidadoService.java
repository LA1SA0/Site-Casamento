package com.igorfabricia.service;

import com.igorfabricia.model.Convidado;
import com.igorfabricia.repository.ConvidadoRepository;
import com.igorfabricia.dto.ConvidadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConvidadoService {

    private final ConvidadoRepository convidadoRepository;

    public ConvidadoService(ConvidadoRepository convidadoRepository) {
        this.convidadoRepository = convidadoRepository;
    }

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Convidado não encontrado"));
    }

    public void deletar(Long id) {
        if (!convidadoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Convidado não encontrado");
        }
        convidadoRepository.deleteById(id);
    }

    public Convidado confirmarPresenca(Long id) {
        return convidadoRepository.findById(id)
                .map(convidado -> {
                    convidado.confirmarPresenca();
                    return convidadoRepository.save(convidado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Convidado não encontrado"));
    }

    public Convidado recusarPresenca(Long id) {
        return convidadoRepository.findById(id)
                .map(convidado -> {
                    convidado.recusarPresenca();
                    return convidadoRepository.save(convidado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Convidado não encontrado"));
    }

    public Convidado atualizarResposta(Long id, Boolean resposta) {
        return convidadoRepository.findById(id)
                .map(convidado -> {
                    convidado.setRespostaPresenca(resposta);
                    return convidadoRepository.save(convidado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Convidado não encontrado"));
    }

    public List<ConvidadoDTO> listarConvidadosComPresente() {
        return convidadoRepository.findAll()
                .stream()
                .map(c -> new ConvidadoDTO(
                        c.getNomeConvidado(),
                        c.getRespostaPresenca(),
                        c.getPresente() != null ? c.getPresente().getNome() : null
                ))
                .collect(Collectors.toList());
    }
}

