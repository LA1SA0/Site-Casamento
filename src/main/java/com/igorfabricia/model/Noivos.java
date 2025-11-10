package com.igorfabricia.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Noivos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNoivos;

    private String nomeNoivo;
    private String nomeNoiva;
    private LocalDate dataCasamento;

    @Column (unique = true, nullable = false)
    private String usuario;

    @Column (nullable = false)
    private String senha;

    @OneToMany (mappedBy = "noivos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Presente> presentes;

    @OneToMany (mappedBy = "noivos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Galeria> fotos;

    public int calcularDiasRestantes() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), dataCasamento);
    }

    public int calcularDiasCasados() {
        return (int) ChronoUnit.DAYS.between(dataCasamento, LocalDate.now());
    }

    public void atualizarDataCasamento(LocalDate novaData) {
        this.dataCasamento = novaData;
    }


}
