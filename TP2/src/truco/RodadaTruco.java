package truco;

public class RodadaTruco 
{
	private PartidaTruco partida;
	private MaoJogadorTruco maoJogador1;
	private MaoJogadorTruco maoJogador2;
	private MaoJogadorTruco maoJogador3;
	private MaoJogadorTruco maoJogador4;
	
	public RodadaTruco(PartidaTruco partida, 
					   MaoJogadorTruco maoJogador1, 
					   MaoJogadorTruco maoJogador2, 
					   MaoJogadorTruco maoJogador3, 
					   MaoJogadorTruco maoJogador4)
	{
		this.partida = partida;
		this.maoJogador1 = maoJogador1;
		this.maoJogador2 = maoJogador2;
		this.maoJogador3 = maoJogador3;
		this.maoJogador4 = maoJogador4;
	}
	
	public PartidaTruco getPartida() {
		return partida;
	}

	public void setPartida(PartidaTruco partida) {
		this.partida = partida;
	}

	public void setMaoJogador1(MaoJogadorTruco maoJogador1) {
		this.maoJogador1 = maoJogador1;
	}
	
	public void setMaoJogador2(MaoJogadorTruco maoJogador2) {
		this.maoJogador2 = maoJogador2;
	}
	
	public void setMaoJogador3(MaoJogadorTruco maoJogador3) {
		this.maoJogador3 = maoJogador3;
	}
	
	public MaoJogadorTruco getMaoJogador1() {
		return maoJogador1;
	}
	
	public MaoJogadorTruco getMaoJogador2() {
		return maoJogador2;
	}
	
	public MaoJogadorTruco getMaoJogador3() {
		return maoJogador3;
	}
	
	public MaoJogadorTruco getMaoJogador4() {
		return maoJogador4;
	}
	
	public void setMaoJogador4(MaoJogadorTruco maoJogador4) {
		this.maoJogador4 = maoJogador4;
	}
}
