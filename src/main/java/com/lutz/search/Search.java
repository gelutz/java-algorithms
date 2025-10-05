package com.lutz.search;

import com.lutz.sort.Candidato;

public class Search {
    public static int pesquisaBinariaCandidatos(Candidato[] candidatos, String nome) {
        return pesquisaBinariaPorNome(candidatos, nome, 0, candidatos.length);
    }

    private static int pesquisaBinariaPorNome(Candidato[] haystack, String needle, int start, int end) {
        System.out.println("start: " + start + "| end: " + end);
        if (start > end)
            return -1;
        int med = (start + end) / 2;
        System.out.println(haystack.length);
        System.out.println(med);
        System.out.println("candidato na posição: " + haystack[med]);
        String nome = haystack[med].getNome();

        if (nome.compareTo(needle) == 0)
            return med;

        if (nome.compareTo(needle) > 0)
            return pesquisaBinariaPorNome(haystack, needle, start, med);
        if (nome.compareTo(needle) < 0)
            return pesquisaBinariaPorNome(haystack, needle, med, end);

        return -1;
    }
}
