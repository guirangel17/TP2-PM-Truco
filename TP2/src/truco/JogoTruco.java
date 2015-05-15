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
	
	public void comecarNovaPartida() {
		PartidaTruco novaPartida = new PartidaTruco(this);
		novaPartida.comecarNovaRodada();
		partidas.add(novaPartida);
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
