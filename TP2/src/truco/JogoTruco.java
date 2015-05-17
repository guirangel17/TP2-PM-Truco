package truco;

import java.util.ArrayList;

public class JogoTruco {
	// Partidas pertencentes ao jogo
	private ArrayList<PartidaTruco> partidas;
	
	// Duplas que estão jogando e suas respectivas pontuações no jogo atual
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
		while (pontuacaoDupla1 < 12 && pontuacaoDupla2 < 12) {
			int numJogadorInicial;
			imprimePlacarJogo();
			
			PartidaTruco novaPartida = new PartidaTruco(this);
			
			if (partidas.size() == 0) {
				numJogadorInicial = 1;
			} else {
				numJogadorInicial = partidas.get(partidas.size() - 1).getNumJogadorInicial() + 1;
			}
			
			if (numJogadorInicial == 5) {
				numJogadorInicial = 1;
			}
			novaPartida.setNumJogadorInicial(numJogadorInicial);
			novaPartida.partidaTruco();
			partidas.add(novaPartida);
			
			if (novaPartida.getDuplaVencedora() == dupla1) {
				pontuacaoDupla1 += novaPartida.getTipoPartida();
			} else {
				pontuacaoDupla2 += novaPartida.getTipoPartida();
			}
		}
	}
	
	public void imprimePlacarJogo() {
		System.out.println("\n\t\t\t\t\tPLACAR:\n\t#### DUPLA 1 (" + dupla1.getJogador1().getNome() + " e " 
				+ dupla1.getJogador2().getNome() + ") " + "(" + pontuacaoDupla1 + ") x (" + pontuacaoDupla2 
				+ ") DUPLA 2 (" + dupla2.getJogador1().getNome() + " e " 
				+ dupla2.getJogador2().getNome() + ") ####\n\n");
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
