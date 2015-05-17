package truco;

import java.util.ArrayList;

public class PartidaTruco {
	// Jogo ao qual a partida pertence
	private JogoTruco jogo;
	private ArrayList<RodadaTruco> rodadas;
	private int tipoPartida;
	private int numJogadorInicial; // Primeiro jogador da Partida - a ordem do jogador inicial é a sequencia da mesa
	protected BaralhoTruco baralho;
	private boolean terminarPartida;
	private Dupla duplaVencedora;
	
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
		novaRodada.setNumJogadorInicial(numJogadorInicial);
		novaRodada.rodadaTruco();
		rodadas.add(novaRodada);
	}
	
	public void partidaTruco() {
		comecarNovaPartida();
		
		while(!terminarPartida) { // Enquanto partida não for finalizada
			RodadaTruco novaRodada = new RodadaTruco(this, rodadas.get(rodadas.size() - 1).getMaoJogador1(), rodadas.get(rodadas.size() - 1).getMaoJogador2(), 
					rodadas.get(rodadas.size() - 1).getMaoJogador3(), rodadas.get(rodadas.size() - 1).getMaoJogador4(), rodadas.get(rodadas.size() - 1).getEmpate());
			novaRodada.setNumJogadorInicial(rodadas.get(rodadas.size() - 1).getNumJogadorInicial());
			novaRodada.rodadaTruco();
			rodadas.add(novaRodada);
			
			if (rodadas.get(rodadas.size() - 1).getEmpate() == false) {
				if (novaRodada.getEmpate() == false) {
					if (novaRodada.getDuplaVencedora() == rodadas.get(0).getDuplaVencedora()){
						terminarPartida = true;
					}
				}
			} else {
				if (novaRodada.getEmpate() == true) {
					terminarPartida = true;
				} else {
					terminarPartida = false;
				}
			}
		}
		
		imprimeVencedorPartida();
	}
	
	public void imprimeVencedorPartida() {
		if (rodadas.size() == 1) { // Se a partida tiver terminado com uma rodada, o vencedor da primeira rodada é o vencedor da partida
			duplaVencedora = rodadas.get(0).getDuplaVencedora();
		} else if (rodadas.size() == 2) { // Se a partida tiver terminado com duas rodadas, o vencedor da segunda rodada é o vencedor da partida
			duplaVencedora = rodadas.get(1).getDuplaVencedora();
		} else if (rodadas.size() == 3) { // Se a partida tiver terminado com três rodadas
			if (rodadas.get(0).getEmpate() == true && rodadas.get(1).getEmpate() == true && rodadas.get(2).getEmpate() == true) {
				System.out.println("\n\t### A partida terminou empatada. Nenhuma dupla ganhará pontos. ###");
			} else {
				if (rodadas.get(0).getEmpate() == true && rodadas.get(1).getEmpate() == true) { // Se as duas primeiras rodadas tiverem terminado empatadas, o vencedor da terceira rodada é o vencedor da partida
					duplaVencedora = rodadas.get(2).getDuplaVencedora();
				} else {
					if (rodadas.get(0).getDuplaVencedora() == rodadas.get(2).getDuplaVencedora()) { // Vencedor primeira igual ao vencedor da última
						duplaVencedora = rodadas.get(0).getDuplaVencedora();
					}
					if (rodadas.get(1).getDuplaVencedora() == rodadas.get(2).getDuplaVencedora()) { // Vencedor segunda igual ao vencedor da última
						duplaVencedora = rodadas.get(1).getDuplaVencedora();
					}
				}
				
				System.out.println("\n\t### Os vencedores da partida foram " + duplaVencedora.getJogador1().getNome() + " e " + duplaVencedora.getJogador2().getNome() + " ###");
			}
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
	
	public void setNumJogadorInicial(int numJogadorInicial) {
		this.numJogadorInicial = numJogadorInicial;
	}
	
	public int getNumJogadorInicial() {
		return numJogadorInicial;
	}
	
	public void setTerminarPartida(boolean terminarPartida) {
		this.terminarPartida = terminarPartida;
	}
	
	public boolean getTerminarPartida() {
		return terminarPartida;
	}
	
	public void setDuplaVencedora(Dupla duplaVencedora) {
		this.duplaVencedora = duplaVencedora;
	}
	
	public Dupla getDuplaVencedora() {
		return duplaVencedora;
	}
}
