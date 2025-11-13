package com.igorfabricia.controller;

import com.igorfabricia.model.Convidado;
import com.igorfabricia.service.ConvidadoService;
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
    public ResponseEntity<Convidado> confirmarPresenca(@PathVariable Long id) {
        return ResponseEntity.ok(convidadoService.confirmarPresenca(id));
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
}

