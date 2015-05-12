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
		
		// ----- Leitura de 4 jogadores: <nome> e <login>
		Jogador jogador1 = new Jogador("Guilherme", "guirangel");
		Jogador jogador2 = new Jogador("Eloisa", "elorangel");
		Jogador jogador3 = new Jogador("Camila", "carangel");
		Jogador jogador4 = new Jogador("Julio", "jurangel");
		
		// ----- Formação das duplas: jogadores escolhem com quem vao jogar
		Dupla dupla1 = new Dupla(jogador1, jogador2);
		Dupla dupla2 = new Dupla(jogador3, jogador4);
		
		// ----- Instancia de um novo jogo: conjunto de partidas
		// Jogo jogo = new Jogo

		// ----- Instancia de uma nova partida: conjunto de 3 ou 2 rodadas
		PartidaTruco partida = new PartidaTruco(dupla1, dupla2);
	}
}