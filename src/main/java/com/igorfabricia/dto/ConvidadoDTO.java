package com.igorfabricia.dto;

public class ConvidadoDTO {
    private String nome;
    private Boolean respostaPresenca;
    private String presenteNome;

    public ConvidadoDTO(String nome, Boolean respostaPresenca, String presenteNome) {
        this.nome = nome;
        this.respostaPresenca = respostaPresenca;
        this.presenteNome = presenteNome;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getRespostaPresenca() {
        return respostaPresenca;
    }

    public String getPresenteNome() {
        return presenteNome;
    }
}

