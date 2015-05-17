package truco;

import java.util.ArrayList;

public class PartidaTruco {
	// Jogo ao qual a partida pertence
	private JogoTruco jogo;
	private ArrayList<RodadaTruco> rodadas;
	private int tipoPartida;
	protected BaralhoTruco baralho;
	private boolean terminarPartida;
	
	public PartidaTruco(JogoTruco jogo) {
		this.jogo = jogo;
		rodadas = new ArrayList<RodadaTruco>();
		baralho = new BaralhoTruco();
		baralho.embaralhar();
		tipoPartida = 2;
	}
	
	public void comecarNovaPartida() {
		/*
		 * Disposicao da mesa:
		 *        1 
		 *      
		 *  2           4
		 *        
		 * 	      3
		 */ 
		MaoJogadorTruco maoJogador1 = new MaoJogadorTruco(jogo.getDupla1().getJogador1(), baralho);
		MaoJogadorTruco maoJogador2 = new MaoJogadorTruco(jogo.getDupla2().getJogador1(), baralho);
		MaoJogadorTruco maoJogador3 = new MaoJogadorTruco(jogo.getDupla1().getJogador2(), baralho);
		MaoJogadorTruco maoJogador4 = new MaoJogadorTruco(jogo.getDupla2().getJogador2(), baralho);
		
		RodadaTruco novaRodada = new RodadaTruco(this, maoJogador1, maoJogador2, maoJogador3, maoJogador4, false);
		rodadas.add(novaRodada);
	}
	
	public void partidaTruco() {
		comecarNovaPartida();
		
		while(!terminarPartida) {
			RodadaTruco novaRodada = new RodadaTruco(this, rodadas.get(rodadas.size() - 1).getMaoJogador1(), rodadas.get(rodadas.size() - 1).getMaoJogador2(), 
					rodadas.get(rodadas.size() - 1).getMaoJogador3(), rodadas.get(rodadas.size() - 1).getMaoJogador4(), rodadas.get(rodadas.size() - 1).getEmpate());
			rodadas.add(novaRodada);
		}
	}
	
	public JogoTruco getJogo() {
		return jogo;
	}

	public void setJogo(JogoTruco jogo) {
		this.jogo = jogo;
	}

	public int getTipoPartida() {
		return tipoPartida;
	}

	public void setTipoPartida(int tipoPartida) {
		this.tipoPartida = tipoPartida;
	}

	public BaralhoTruco getBaralho() {
		return baralho;
	}

	public void setBaralho(BaralhoTruco baralho) {
		this.baralho = baralho;
	}

	public ArrayList<RodadaTruco> getRodadas() {
		return rodadas;
	}

	public void setRodadas(ArrayList<RodadaTruco> rodadas) {
		this.rodadas = rodadas;
	}
	
	public int getNumeroRodadas() {
		return rodadas.size();
	}
	
	
	public void setTerminarPartida(boolean terminarPartida) {
		this.terminarPartida = terminarPartida;
	}
	
	public boolean getTerminarPartida() {
		return terminarPartida;
	}
}
