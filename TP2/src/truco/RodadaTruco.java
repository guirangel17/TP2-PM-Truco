package truco;

public class RodadaTruco {
	private MaoJogadorTruco maoJogador1;
	private MaoJogadorTruco maoJogador2;
	private MaoJogadorTruco maoJogador3;
	private MaoJogadorTruco maoJogador4;
	
	public RodadaTruco(Dupla dupla1, Dupla dupla2, BaralhoTruco baralho){
		maoJogador1 = new MaoJogadorTruco(dupla1.getJogador1(), baralho);
		maoJogador2 = new MaoJogadorTruco(dupla1.getJogador2(), baralho);
		maoJogador3 = new MaoJogadorTruco(dupla2.getJogador1(), baralho);
		maoJogador4 = new MaoJogadorTruco(dupla2.getJogador2(), baralho);	
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
}
