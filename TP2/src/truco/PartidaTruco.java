package truco;

import java.util.ArrayList;

public class PartidaTruco {
	// Jogo ao qual a partida pertence
	private JogoTruco jogo;
	private ArrayList<RodadaTruco> rodadas;
	private int tipoPartida;
	protected BaralhoTruco baralho;
	
	public PartidaTruco(JogoTruco jogo) {
		this.jogo = jogo;
		rodadas = new ArrayList<RodadaTruco>();
		baralho = new BaralhoTruco();
		baralho.embaralhar();
	}
	
	public void comecarNovaRodada() {
		MaoJogadorTruco maoJogador1 = new MaoJogadorTruco(jogo.getDupla1().getJogador1(), baralho);
		MaoJogadorTruco maoJogador2 = new MaoJogadorTruco(jogo.getDupla1().getJogador2(), baralho);
		MaoJogadorTruco maoJogador3 = new MaoJogadorTruco(jogo.getDupla2().getJogador1(), baralho);
		MaoJogadorTruco maoJogador4 = new MaoJogadorTruco(jogo.getDupla2().getJogador2(), baralho);
		
		RodadaTruco novaRodada = new RodadaTruco(this, maoJogador1, maoJogador2, maoJogador3, maoJogador4);
		rodadas.add(novaRodada);
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

	public int getTipoPartidaTruco() {
		return tipoPartida;
	}

	public void setTipoPartidaTruco(int tipoPartida) {
		this.tipoPartida = tipoPartida;
	}
}
