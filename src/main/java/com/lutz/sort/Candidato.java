package com.lutz.sort;

public class Candidato {
    public String nome;
    public String partido;
    public int intencoesVotos = 0;

    public Candidato(String nome, String partido) {
        this.nome = nome;
        this.partido = partido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getIntencoesVotos() {
        return intencoesVotos;
    }

    public int setIntencoesVotos(int i) {
        return intencoesVotos = i;
    }

    public void incrementarVoto(int amount) {
        this.intencoesVotos += amount;
    }

    public String toString() {
        return "Candidato [nome=" + nome + ", partido=" + partido + ", intencoesVotos=" + intencoesVotos + "]";
    }
}
