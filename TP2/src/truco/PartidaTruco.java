package truco;

import java.util.ArrayList;

public class PartidaTruco {
	private ArrayList<RodadaTruco> rodadas;
	private int tipoPartida;
	private Dupla dupla1;
	private Dupla dupla2;
	protected BaralhoTruco baralho;
	
	public PartidaTruco(Dupla dupla1, Dupla dupla2) {
		this.dupla1 = dupla1;
		this.dupla2 = dupla2;
		rodadas = new ArrayList<RodadaTruco>();
		baralho = new BaralhoTruco();
		comecarPartidaTruco();
	}

	public void comecarPartidaTruco() {
		// Numero de cartas inicialmente
		System.out.println(baralho.numeroCartas());
		baralho.embaralhar();
		RodadaTruco novaRodada = new RodadaTruco(dupla1, dupla2, baralho);
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
	
	public BaralhoTruco getBaralhoTruco() {
		return baralho;
	}
	
	public void setBaralhoTruco(BaralhoTruco baralho) {
		this.baralho = baralho;
	}
}
