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

        String primeiroNome = nome.split(" ")[0];

        List<String> mensagensSim = List.of(
                "Que bom que vai vir, " + primeiroNome + "!",
                "Ficaremos muito felizes com sua presença, " + primeiroNome + "!",
                "Mal podemos esperar pra te ver no grande dia, " + primeiroNome + "!",
                primeiroNome + ", prepare-se para celebrar conosco!",
                "Sua presença vai tornar nosso dia ainda mais especial, " + primeiroNome + "!"
        );

        List<String> mensagensNao = List.of(
                "Ah, que pena que não poderá vir, " + primeiroNome + ".",
                "Sentiremos sua falta, " + primeiroNome + "!",
                "Uma pena que não poderá estar presente, " + primeiroNome + ".",
                primeiroNome + ", esperamos te ver em outra ocasião!",
                "Mesmo de longe, sabemos que estará torcendo por nós, " + primeiroNome + "!"
        );

        Random random = new Random();
        if (vaiComparecer) {
            return mensagensSim.get(random.nextInt(mensagensSim.size()));
        } else {
            return mensagensNao.get(random.nextInt(mensagensNao.size()));
        }
    }
}

