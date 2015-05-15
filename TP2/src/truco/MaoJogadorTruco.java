package truco;

import java.util.ArrayList;

public class MaoJogadorTruco {
	private ArrayList<CartaTruco> cartas;
	private Jogador jogador;
	
	public MaoJogadorTruco (Jogador jogador, BaralhoTruco baralho){
		cartas = new ArrayList<CartaTruco>();
		this.jogador = jogador;
		cartas.add((CartaTruco) baralho.pegaCarta());
		cartas.add((CartaTruco) baralho.pegaCarta());
		cartas.add((CartaTruco) baralho.pegaCarta());
	}

	public ArrayList<CartaTruco> getCartas() {
		return cartas;
	}
	
	public void setCartas(ArrayList<CartaTruco> cartas) {
		this.cartas = cartas;
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public int getNumeroCartas() {
		return cartas.size();
	}
	
	// terá um método de retirar carta do arrayList, que quer dizer que a carta foi jogada
}
