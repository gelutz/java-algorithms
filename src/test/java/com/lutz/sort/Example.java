package com.lutz.sort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Example {

    public static void main(String[] args) {
        // int linhas = (int) (Math.random() * 100);
        int linhas = 50;
        Candidato[] candidatos = getCandidatosFromFile(linhas);

        // print para mostrar o layout inicial dos candidatos, partidos e votos
        System.out.println("-x-x Lista inicial: x-x-");
        printCandidatos(candidatos);

        System.out.println("-x-x Lista ordenada por nome: x-x-");
        printCandidatos((Candidato[]) Sort.sortObject(candidatos, "nome"));

        System.out.println("-x-x Lista ordenada por votos: x-x-");
        printCandidatos((Candidato[]) Sort.sortObject(candidatos, "votos"));

        System.out.println("-x-x Lista ordenada por partido: x-x-");
        printCandidatos((Candidato[]) Sort.sortObject(candidatos, "partido"));

        // String nome = "Lucas";
        // System.out.println("-x-x Pesquisando pelo nome " + nome + ": x-x-");
        // int index = OrdenarCandidatos.pesquisaBinariaCandidatos(candidatos, nome);
        // System.out.println(index + ": " + candidatos[index]);

    }

    private static Candidato[] getCandidatosFromFile(int lines) {
        String[] nomes = null;
        String[] partidos = null;

        try {
            nomes = Files.readString(Path.of("src/Etapa2/nomes.txt")).split(
                    "\n");
            partidos = Files.readString(
                    Path.of("src/Etapa2/partidos.txt")).split("\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Arquivo não encontrado!");
        }

        if (nomes == null || partidos == null) {
            throw new RuntimeException(
                    "Não consegui pegar as informações dos arquivos!");
        }

        Candidato[] candidatos = new Candidato[lines];

        int linhaArquivo = 0;
        int linhaCandidatos = 0;
        while (linhaArquivo < lines) {
            int indexExistente = contains(candidatos, nomes[linhaArquivo], partidos[linhaArquivo]);
            if (indexExistente >= 0) {
                candidatos[indexExistente].incrementarVoto(1);
            } else {
                candidatos[linhaCandidatos] = new Candidato(nomes[linhaArquivo], partidos[linhaArquivo]);
                candidatos[linhaCandidatos].incrementarVoto(1);
                linhaCandidatos++;
            }
            linhaArquivo++;
        }

        return removerNulos(candidatos);
    }

    private static int contains(
            Candidato[] arr,
            String needleNome,
            String needlePartido) {
        int index = 0;
        for (Candidato key : arr) {
            if (key == null) {
                index++;
                continue;
            }

            if (key.getNome().equals(needleNome) &&
                    key.getPartido().equals(needlePartido)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    private static void printCandidatos(Candidato[] candidatos) {
        if (candidatos == null)
            return;

        int index = 0;
        for (Candidato candidato : candidatos) {
            if (candidato == null) {
                System.out.println("null");
                index++;
                continue;
            }
            System.out.println(index + ": " + candidato);
            index++;
        }
    }

    public static Candidato[] removerNulos(Candidato[] c) {
        ArrayList<Candidato> removedNull = new ArrayList<Candidato>();
        for (Candidato str : c)
            if (str != null)
                removedNull.add(str);
        return removedNull.toArray(new Candidato[0]);
    }
}
