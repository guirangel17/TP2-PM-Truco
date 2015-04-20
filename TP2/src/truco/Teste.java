package truco;

public class Teste {

	public static void main(String[] args) {
		// Teste Lucas
		Jogador jogador1 = new Jogador("Guilherme", "guirangel");
		Jogador jogador2 = new Jogador("Eloisa", "elorangel");
		Jogador jogador3 = new Jogador("Camila", "carangel");
		Jogador jogador4 = new Jogador("Julio", "jurangel");
		
		Dupla dupla1 = new Dupla(jogador1, jogador2);
		Dupla dupla2 = new Dupla(jogador3, jogador4);
		
		Baralho baralho = new Baralho();
		baralho.embaralhar();
		
		// Jogo jogo = new Jogo
		Partida partida = new Partida();
		
		
	}
}
