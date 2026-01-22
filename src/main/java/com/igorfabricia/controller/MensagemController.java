package com.igorfabricia.controller;

import com.igorfabricia.model.Mensagem;
import com.igorfabricia.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping
    public ResponseEntity<List<Mensagem>> listarTodas() {
        return ResponseEntity.ok(mensagemService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensagem> buscarPorId(@PathVariable Long id) {
        return mensagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mensagem> criar(@RequestBody Mensagem mensagem) {
        return ResponseEntity.status(201).body(mensagemService.salvar(mensagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> atualizar(
            @PathVariable Long id,
            @RequestBody Mensagem mensagemAtualizada
    ) {
        return ResponseEntity.ok(mensagemService.atualizar(id, mensagemAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        mensagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/convidado/{idConvidado}")
    public ResponseEntity<String> gerarMensagemAleatoria(
            @PathVariable Long idConvidado
    ) {
        return ResponseEntity.ok(
                mensagemService.gerarMensagemAleatoriaPorConvidado(idConvidado)
        );
    }
}
