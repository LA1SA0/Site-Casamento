package com.igorfabricia.controller;

import com.igorfabricia.model.Galeria;
import com.igorfabricia.service.GaleriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/galeria")
public class GaleriaController {

    @Autowired
    private GaleriaService galeriaService;

    @GetMapping
    public ResponseEntity<List<Galeria>> listarTodas() {
        return ResponseEntity.ok(galeriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Galeria> buscarPorId(@PathVariable Long id) {
        return galeriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Galeria> salvar(@RequestBody Galeria galeria) {
        return ResponseEntity.status(201).body(galeriaService.salvar(galeria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Galeria> atualizar(@PathVariable Long id, @RequestBody Galeria galeriaAtualizada) {
        return ResponseEntity.ok(galeriaService.atualizar(id, galeriaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        galeriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

