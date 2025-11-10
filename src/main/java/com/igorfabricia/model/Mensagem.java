package com.igorfabricia.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Random;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;

    private String texto;

    @OneToOne
    @JoinColumn
    private Convidado convidado;

    public static String gerarMensagemAleatoria(String nome, boolean vaiComparecer) {
        List<String> mensagensSim = List.of(
                "Que bom que vai vir, " + nome + "!",
                "Ficaremos muito felizes com sua presença, " + nome + "!",
                "Mal podemos esperar pra te ver no grande dia, " + nome + "!",
                nome + ", prepare-se para celebrar conosco!",
                "Sua presença vai tornar nosso dia ainda mais especial, " + nome + "!"
        );

        List<String> mensagensNao = List.of(
                "Ah, que pena que não poderá vir, " + nome + ".",
                "Sentiremos sua falta, " + nome + "!",
                "Uma pena que não poderá estar presente, " + nome + ".",
                nome + ", esperamos te ver em outra ocasião!",
                "Mesmo de longe, sabemos que estará torcendo por nós, " + nome + "!"
        );

        Random random = new Random();
        if (vaiComparecer) {
            return mensagensSim.get(random.nextInt(mensagensSim.size()));
        } else {
            return mensagensNao.get(random.nextInt(mensagensNao.size()));
        }
    }
}

