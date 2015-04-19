package truco;

public class Dupla 
{
	private Jogador jogador1;
	private Jogador jogador2;
	private int partidasJogadas;
	private int partidasVencidas;
	
	public Dupla(Jogador jogador1, Jogador jogador2)
	{
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public int getPartidasJogadas() {
		return partidasJogadas;
	}

	public void setPartidasJogadas(int partidasJogadas) {
		this.partidasJogadas = partidasJogadas;
	}

	public int getPartidasVencidas() {
		return partidasVencidas;
	}

	public void setPartidasVencidas(int partidasVencidas) {
		this.partidasVencidas = partidasVencidas;
	}
}
