package truco;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Jogador jogador1 = new Jogador("Guilherme", "guirangel");
		Jogador jogador2 = new Jogador("Eloisa", "elorangel");
		Jogador jogador3 = new Jogador("Camila", "carangel");
		Jogador jogador4 = new Jogador("Julio", "jurangel");
		
		Dupla dupla1 = new Dupla(jogador1, jogador2);
		Dupla dupla2 = new Dupla(jogador3, jogador4);
		
		JogoTruco jogo = new JogoTruco(dupla1, dupla2);
		jogo.comecarNovaPartida();

		/*
		while (jogo.getPontuacaoDupla1() < 12 && jogo.getPontuacaoDupla2() < 12 && jogo.getAbandonarDupla1() == false && jogo.getAbandonarDupla2() == false) {
			jogo.comecarNovaPartida();
		}
		System.out.println(jogo.getPartidas().get(0).getRodadas().get(0).getMaoJogador1().getCartas().get(0).getNome());
		*/
		
	 }
}
