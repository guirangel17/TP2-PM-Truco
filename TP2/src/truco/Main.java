package truco;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		/*
		ArrayList<Carta> listaCartas;
		Carta aux;
		
		Baralho baralho = new Baralho();
		System.out.println(baralho.numeroCartas());
		// baralho.embaralhar();
		listaCartas = baralho.getListaCartas();
		while (baralho.numeroCartas() != 0) {
			aux = baralho.pegaCarta();
			System.out.println(aux.getNome() + " " + aux.getOrdemImportancia());
		}
		
		System.out.println("");
		
		BaralhoTruco b = new BaralhoTruco();
		System.out.println(b.numeroCartas());
		// b.embaralhar();
		listaCartas = b.getListaCartas();
		while (b.numeroCartas() != 0) {
			aux = b.pegaCarta();
			System.out.println(aux.getNome() + " " + aux.getOrdemImportancia());
		}
		*/
		
		Jogador jogador1 = new Jogador("Guilherme", "guirangel");
		Jogador jogador2 = new Jogador("Eloisa", "elorangel");
		Jogador jogador3 = new Jogador("Camila", "carangel");
		Jogador jogador4 = new Jogador("Julio", "jurangel");
		
		Dupla dupla1 = new Dupla(jogador1, jogador2);
		Dupla dupla2 = new Dupla(jogador3, jogador4);
		
		// Jogo jogo = new Jogo
		/*
		Partida partida = new Partida();
		System.out.println(jogador1.getNome());
		*/
	}

}
