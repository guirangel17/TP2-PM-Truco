package truco;

import java.util.ArrayList;

public class Partida 
{
	private ArrayList<Rodada> rodadas;
	private int tipoPartida;
	private Dupla dupla1;
	private Dupla dupla2;
	
	public Partida()
	{
		rodadas = new ArrayList<Rodada>();
	}

	public void comecarPartida(Baralho baralho)
	{
		Rodada novaRodada = new Rodada();
		
		
	}
	
	public ArrayList<Rodada> getRodadas() {
		return rodadas;
	}

	public void setRodadas(ArrayList<Rodada> rodadas) {
		this.rodadas = rodadas;
	}

	public int getTipoPartida() {
		return tipoPartida;
	}

	public void setTipoPartida(int tipoPartida) {
		this.tipoPartida = tipoPartida;
	}
}
