package com.igorfabricia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvidado;

    @Column(nullable = false)
    private String nomeConvidado;

    private Boolean respostaPresenca;

    @OneToOne(mappedBy = "convidado", cascade = CascadeType.ALL)
    private Mensagem mensagem;

    @ManyToOne
    @JoinColumn(name = "presente_id")
    private Presente presente;

    public void confirmarPresenca() {
        this.respostaPresenca = true;
    }

    public void recusarPresenca() {
        this.respostaPresenca = false;
    }
}
