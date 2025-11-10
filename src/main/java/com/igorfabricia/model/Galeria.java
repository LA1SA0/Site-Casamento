package com.igorfabricia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Galeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoto;

    private String categoria;
    // Pré-Wedding, casamento ou ensaio/festa

    @Column(nullable = false)
    private String nomeArquivo;
    // Nome do arquivo da foto armazenada

    @ManyToOne
    @JoinColumn(name = "idNoivos")
    private Noivos noivos;

}
