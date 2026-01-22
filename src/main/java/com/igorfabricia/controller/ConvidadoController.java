package com.igorfabricia.controller;

import com.igorfabricia.model.Convidado;
import com.igorfabricia.service.ConvidadoService;
import com.igorfabricia.dto.ConvidadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convidados")
public class ConvidadoController {

    @Autowired
    private ConvidadoService convidadoService;

    @GetMapping
    public ResponseEntity<List<Convidado>> listarTodos() {
        return ResponseEntity.ok(convidadoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Convidado> adicionar(@RequestBody Convidado convidado) {
        return ResponseEntity.status(201).body(convidadoService.salvar(convidado));
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<ConvidadoDTO> confirmarPresenca(@PathVariable Long id) {
        Convidado convidado = convidadoService.confirmarPresenca(id);
        ConvidadoDTO dto = new ConvidadoDTO(
                convidado.getNomeConvidado(),
                convidado.getRespostaPresenca(),
                convidado.getPresente() != null ? convidado.getPresente().getNome() : null
        );
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity<Convidado> recusarPresenca(@PathVariable Long id) {
        return ResponseEntity.ok(convidadoService.recusarPresenca(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        convidadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/resposta/{id}")
    public ResponseEntity<Convidado> responderConvite(
            @PathVariable Long id,
            @RequestParam Boolean resposta) {
        Convidado convidado = convidadoService.atualizarResposta(id, resposta);
        return ResponseEntity.ok(convidado);
    }

    @GetMapping("/lista-para-noivos")
    public ResponseEntity<List<ConvidadoDTO>> listarParaNoivos() {
        List<ConvidadoDTO> lista = convidadoService.listarConvidadosComPresente();
        return ResponseEntity.ok(lista);
    }
}

