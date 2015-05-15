package truco;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner e = new Scanner(System.in);
		
		Jogador jogador1 = new Jogador("Guilherme", "guirangel");
		Jogador jogador2 = new Jogador("Eloisa", "elorangel");
		Jogador jogador3 = new Jogador("Camila", "carangel");
		Jogador jogador4 = new Jogador("Julio", "jurangel");
		
		Dupla dupla1 = new Dupla(jogador1, jogador2);
		Dupla dupla2 = new Dupla(jogador3, jogador4);
		
		int i;
		int numeroPartida;
		int numeroRodada;
		int numeroCartaJogador1;
		int carta = 0;
		
		JogoTruco jogo = new JogoTruco(dupla1, dupla2);
		
		/*
		while (jogo.getPontuacaoDupla1() < 12 && jogo.getPontuacaoDupla2() < 12) {
			jogo.comecarNovaPartida();
		}
		*/
		
		jogo.comecarNovaPartida();
		
		numeroPartida = jogo.getNumeroPartidas() - 1;
		numeroRodada = jogo.getPartidas().get(numeroPartida).getNumeroRodadas() - 1;
		
		numeroCartaJogador1 = jogo.getPartidas().get(numeroPartida).getRodadas().get(numeroRodada).getMaoJogador1().getNumeroCartas();
		
		System.out.println("Cartas Jogador 1: ");
		for (i=0; i<numeroCartaJogador1; i++) {
			System.out.println("<" + (i+1) + "> " + jogo.getPartidas().get(numeroPartida).getRodadas().get(numeroRodada).getMaoJogador1().getCartas().get(i).getNome());
		}
		
		while (carta != 1 && carta != 2 && carta != 3) {
			System.out.println("Jogador 1, escolha uma carta para jogar: ");
			carta = e.nextInt();
		}
		
		carta = carta - 1;
		
		System.out.println("Jogador 1 jogou: " + jogo.getPartidas().get(numeroPartida).getRodadas().get(numeroRodada).getMaoJogador1().getCartas().get(carta).getNome());
		// Retira carta jogada da mão do jogador
		jogo.getPartidas().get(numeroPartida).getRodadas().get(numeroRodada).JogaCartaJogador1(carta);
				
	 }
}
