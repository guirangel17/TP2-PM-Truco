package truco;

import java.util.ArrayList;

public class JogoTruco {
	// Partidas pertencentes ao jogo
	private ArrayList<PartidaTruco> partidas;
	
	// Duplas que estão no Jogo e suas respectivas pontuações no Jogo atual
	private Dupla dupla1;
	private Dupla dupla2;
	private int pontuacaoDupla1;
	private int pontuacaoDupla2;
	
	public JogoTruco(Dupla dupla1, Dupla dupla2) {
		partidas = new ArrayList<PartidaTruco>();
		this.dupla1 = dupla1;
		this.dupla2 = dupla2;
	}
	
	public void comecarNovoJogo() {
		// Enquanto nenhuma das duplas atingir 12 pontos
		while (pontuacaoDupla1 < 12 && pontuacaoDupla2 < 12) {
			int numJogadorInicial;
			imprimePlacarJogo();
			
			PartidaTruco novaPartida = new PartidaTruco(this); // Instância de uma nova Partida
			
			if (getNumeroPartidas() == 0) { // Se for a primeira partida do jogo
				numJogadorInicial = 1; // Quem irá começar jogando é o jogador número 1 (primeiro jogador da dupla 1)
			} else {
				// Senão, quem irá começar jogando a partida é o jogador à direita do jogador que iniciou a última partida
				numJogadorInicial = partidas.get(getNumeroPartidas() - 1).getNumJogadorInicial() + 1;
			}
			
			System.out.println("Partida " + (getNumeroPartidas() + 1));
			
			if (numJogadorInicial == 5) {
				numJogadorInicial = 1;
			}
			
			novaPartida.setNumJogadorInicial(numJogadorInicial); // Set jogador inicial
			novaPartida.partidaTruco(); // Inicia nova partida
			partidas.add(novaPartida); // Adiciona partida ao Array de partidas
			
			// Associa os pontos da partida à dupla vencedora
			if (novaPartida.getDuplaVencedora() == dupla1) {
				pontuacaoDupla1 += novaPartida.getTipoPartida();
			} else {
				pontuacaoDupla2 += novaPartida.getTipoPartida();
			}
		}
		
		imprimeVencedorJogo();
	}
	
	public void imprimePlacarJogo() {
		System.out.println("\n\t\t\t\t\tPLACAR:\n\t#### DUPLA 1 (" + dupla1.getJogador1().getNome() + " e " 
				+ dupla1.getJogador2().getNome() + ") " + "(" + pontuacaoDupla1 + ") x (" + pontuacaoDupla2 
				+ ") DUPLA 2 (" + dupla2.getJogador1().getNome() + " e " 
				+ dupla2.getJogador2().getNome() + ") ####\n\n");
	}
	
	public void imprimeVencedorJogo() {
		if (pontuacaoDupla1 >= 12) {
			System.out.println("\n\n\t##### DUPLA 1 (" + dupla1.getJogador1().getNome() 
					+ " e " + dupla1.getJogador2().getNome() + ") VENCEU! #####");
		} else {
			System.out.println("\n\n\t##### DUPLA 2 (" + dupla2.getJogador1().getNome() 
					+ " e " + dupla2.getJogador2().getNome() + ") VENCEU! #####");
		}
	}
	
	public ArrayList<PartidaTruco> getPartidas() {
		return partidas;
	}
	
	public void setPartidas(ArrayList<PartidaTruco> partidas) {
		this.partidas = partidas;
	}
	
	public int getPontuacaoDupla1() {
		return pontuacaoDupla1;
	}

	public void setPontuacaoDupla1(int pontuacaoDupla1) {
		this.pontuacaoDupla1 = pontuacaoDupla1;
	}

	public int getPontuacaoDupla2() {
		return pontuacaoDupla2;
	}

	public void setPontuacaoDupla2(int pontuacaoDupla2) {
		this.pontuacaoDupla2 = pontuacaoDupla2;
	}

	public Dupla getDupla1() {
		return dupla1;
	}

	public Dupla getDupla2() {
		return dupla2;
	}
	
	public int getNumeroPartidas() {
		return partidas.size();
	}
}
