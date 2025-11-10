package com.igorfabricia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Presente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPresente;

    @Column (nullable = false)
    private String nome;

    @Column (columnDefinition = "TEXT")
    private String descricao;

    private String urlImagem;

    private Double valor;

    @Column(nullable = false)
    private String status = "Disponível";


    @ManyToOne
    @JoinColumn(name = "idNoivos")
    private Noivos noivos;

    public void reservar() {
        if (this.status.equals ("Disponível")) {
            this.status = "Reservado";
        }
    }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }
}
