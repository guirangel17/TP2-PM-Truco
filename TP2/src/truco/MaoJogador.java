package truco;

import java.util.ArrayList;

public class MaoJogador 
{
	private ArrayList<Carta> cartas;
	private Jogador jogador;
	
	public ArrayList<Carta> getCartas() {
		return cartas;
	}
	
	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	// terá um método de retirar carta do arrayList, que quer dizer que a carta foi jogada
}
