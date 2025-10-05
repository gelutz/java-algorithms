package com.lutz.maze;

public class Example {
	public static void main(String[] args) {
		var lab = new Labirinto();
		lab.criaLabirinto("src/Etapa1/labirinto4.txt");
		lab.imprimeLabirinto();
		lab.percorreLabirinto();
		lab.imprimeLabirinto(); // aqui precisa mostrar o labirinto completo com o caminho final
	}
}