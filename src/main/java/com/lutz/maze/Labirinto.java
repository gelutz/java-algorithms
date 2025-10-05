package com.lutz.maze;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Labirinto {
	public final String PAREDE = "X";
	public final String SAIDA = "D";
	public final String CAMINHO_ABERTO = " ";
	public final String CAMINHO_SOLUCAO = "O";
	public final String CAMINHO_ERRADO = "#";

	private String[][] labirinto = null;
	private int tamanhoX, tamanhoY;

	private List<Integer[]> bifurcacoes = new ArrayList<>();

	public void criaLabirinto(String filename) {
		try {
			String text = Files.readString(Path.of(filename));
			String nl = text.contains("\n") ? "\n" : "\r";
			tamanhoY = text.split(nl).length;
			tamanhoX = text.indexOf(nl);

			this.labirinto = Arrays.stream(text.split(nl))
					.map(l -> l.split(""))
					.toArray(String[][]::new);
		} catch (FileNotFoundException | NoSuchFileException e) {
			throw new IllegalArgumentException("Arquivo não encontrado.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Houve um erro inesperado.");
		}
	}

	public boolean percorreLabirinto() {
		if (resolverLabirinto(0, 0)) {
			System.out.println("Solução encontrada");
			return true;
		} else {
			System.out.println("Ops...");
		}

		return false;
	}

	private boolean resolverLabirinto(int x, int y) {
		if (Objects.equals(this.labirinto[x][y], this.SAIDA))
			return true;
		if (x == tamanhoY - 1 && y == tamanhoX - 1)
			return false;
		if (Objects.equals(this.labirinto[x][y], this.PAREDE))
			return false;

		var topo = x == 0;
		var esquerda = y == 0;
		var chao = x == tamanhoY - 1;
		var direita = y == tamanhoX - 1;

		this.labirinto[x][y] = this.CAMINHO_SOLUCAO;
		this.imprimeLabirinto();

		boolean e = false,
				d = false,
				c = false,
				b = false;
		if (!direita && (Objects.equals(this.labirinto[x][y + 1], this.CAMINHO_ABERTO)
				|| Objects.equals(this.labirinto[x][y + 1], this.SAIDA))) {
			d = true;
		}

		if (!chao && (Objects.equals(this.labirinto[x + 1][y], this.CAMINHO_ABERTO)
				|| Objects.equals(this.labirinto[x + 1][y], this.SAIDA))) {
			b = true;
		}

		if (!esquerda && (Objects.equals(this.labirinto[x][y - 1], this.CAMINHO_ABERTO)
				|| Objects.equals(this.labirinto[x][y - 1], this.SAIDA))) {
			e = true;
		}

		if (!topo && (Objects.equals(this.labirinto[x - 1][y], this.CAMINHO_ABERTO)
				|| Objects.equals(this.labirinto[x - 1][y], this.SAIDA))) {
			c = true;
		}

		List<Boolean> booleans = Stream.of(e, d, c, b).filter(t -> t).toList();
		if (booleans.size() > 1) {
			this.bifurcacoes.add(new Integer[] { x, y });
		}

		if (d)
			return resolverLabirinto(x, y + 1);
		if (b)
			return resolverLabirinto(x + 1, y);
		if (e)
			return resolverLabirinto(x, y - 1);
		if (c)
			return resolverLabirinto(x - 1, y);

		// volta pro ultimo #
		var next = this.bifurcacoes.getLast();
		this.bifurcacoes.remove(next);
		this.labirinto[x][y] = this.CAMINHO_ERRADO;
		return resolverLabirinto(next[0], next[1]);
	}

	public void imprimeLabirinto() {
		try {
			Runtime.getRuntime().exec("clear"); // limpa o console
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		var labirintoText = getLabirintoFormatado();
		System.out.println(labirintoText);
	}

	private String getLabirintoFormatado() {
		if (this.labirinto == null)
			return "";
		return Arrays.stream(this.labirinto).map(l -> String.join("|", l) + "\n").collect(Collectors.joining());
	}

}