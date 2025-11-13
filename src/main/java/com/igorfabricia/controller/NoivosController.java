package com.igorfabricia.controller;

import com.igorfabricia.model.Noivos;
import com.igorfabricia.service.NoivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/noivos")
public class NoivosController {

    @Autowired
    private NoivosService noivosService;

    @PostMapping("/login")
    public ResponseEntity<Noivos> login(@RequestBody Noivos login) {
        Noivos noivos = noivosService.autenticar(login.getUsuario(), login.getSenha());
        if (noivos != null) {
            return ResponseEntity.ok(noivos);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping
    public ResponseEntity<Noivos> getNoivos() {
        Noivos noivos = noivosService.buscarUnicoCasal();
        return ResponseEntity.ok(noivos);
    }

    @PutMapping
    public ResponseEntity<Noivos> atualizar(@RequestBody Noivos atualizado) {
        Noivos noivosAtualizados = noivosService.atualizarDados(atualizado);
        return ResponseEntity.ok(noivosAtualizados);
    }

    @PutMapping("/data")
    public ResponseEntity<Noivos> atualizarData(@RequestBody Map<String, String> body) {
        LocalDate novaData = LocalDate.parse(body.get("novaData"));
        Noivos atualizado = noivosService.atualizarDataCasamento(novaData);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/dias-restantes")
    public ResponseEntity<Integer> calcularDiasRestantes() {
        return ResponseEntity.ok(noivosService.calcularDiasRestantes());
    }

    @GetMapping("/dias-casados")
    public ResponseEntity<Integer> calcularDiasCasados() {
        return ResponseEntity.ok(noivosService.calcularDiasCasados());
    }
}
