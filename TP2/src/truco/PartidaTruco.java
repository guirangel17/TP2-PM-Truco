package truco;

import java.util.ArrayList;

public class PartidaTruco {
	// Jogo ao qual a partida pertence
	private Jogo jogo;
	private ArrayList<RodadaTruco> rodadas;
	private int tipoPartida;
	protected BaralhoTruco baralho;
	
	public PartidaTruco(Jogo jogo) {
		this.jogo = jogo;
		rodadas = new ArrayList<RodadaTruco>();
		baralho = new BaralhoTruco();
		
		comecarPartidaTruco();
	}

	public void comecarPartidaTruco() 
	{
		// Numero de cartas inicialmente
		System.out.println(baralho.numeroCartas());
		baralho.embaralhar();
		
		MaoJogadorTruco maoJogador1 = new MaoJogadorTruco(jogo.getDupla1().getJogador1(), this.baralho);
		MaoJogadorTruco maoJogador2 = new MaoJogadorTruco(jogo.getDupla1().getJogador2(), this.baralho);
		MaoJogadorTruco maoJogador3 = new MaoJogadorTruco(jogo.getDupla2().getJogador1(), this.baralho);
		MaoJogadorTruco maoJogador4 = new MaoJogadorTruco(jogo.getDupla2().getJogador2(), this.baralho);
		
		RodadaTruco novaRodada = new RodadaTruco(this, maoJogador1, maoJogador2, maoJogador3, maoJogador4);
	
		// Teste mãos
		System.out.println(novaRodada.getMaoJogador1().getCartas().get(0).getNome());
		System.out.println(novaRodada.getMaoJogador1().getCartas().get(1).getNome());
		System.out.println(novaRodada.getMaoJogador1().getCartas().get(2).getNome());

		System.out.println(novaRodada.getMaoJogador2().getCartas().get(0).getNome());
		System.out.println(novaRodada.getMaoJogador2().getCartas().get(1).getNome());
		System.out.println(novaRodada.getMaoJogador2().getCartas().get(2).getNome());

		System.out.println(novaRodada.getMaoJogador3().getCartas().get(0).getNome());
		System.out.println(novaRodada.getMaoJogador3().getCartas().get(1).getNome());
		System.out.println(novaRodada.getMaoJogador3().getCartas().get(2).getNome());

		// Numero de cartas apos distribuicao 
		System.out.println(baralho.numeroCartas());
	}
	
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
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
