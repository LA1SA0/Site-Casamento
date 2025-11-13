package com.igorfabricia.controller;

import com.igorfabricia.model.Presente;
import com.igorfabricia.service.PresenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presentes")
public class PresenteController {

    @Autowired
    private PresenteService presenteService;

    @GetMapping
    public ResponseEntity<List<Presente>> listarTodos() {
        return ResponseEntity.ok(presenteService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Presente> adicionar(@RequestBody Presente presente) {
        return ResponseEntity.status(201).body(presenteService.salvar(presente));
    }

    @PutMapping("/{id}/reservar")
    public ResponseEntity<Presente> reservar(@PathVariable Long id) {
        return ResponseEntity.ok(presenteService.reservar(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Presente> atualizarStatus(@PathVariable Long id, @RequestBody String novoStatus) {
        return ResponseEntity.ok(presenteService.atualizarStatus(id, novoStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        presenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

