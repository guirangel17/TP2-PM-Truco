package truco;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// Primeiro fazer cadastro de jogador
		Jogador jogador1 = new Jogador("Guilherme", "guirangel");
		Jogador jogador2 = new Jogador("Eloisa", "elorangel");
		Jogador jogador3 = new Jogador("Camila", "carangel");
		Jogador jogador4 = new Jogador("Julio", "jurangel");
		
		Dupla dupla1 = new Dupla(jogador1, jogador3);
		Dupla dupla2 = new Dupla(jogador2, jogador4);
		
		JogoTruco jogo = new JogoTruco(dupla1, dupla2);		
		jogo.comecarNovoJogo();
		
	 }
}
