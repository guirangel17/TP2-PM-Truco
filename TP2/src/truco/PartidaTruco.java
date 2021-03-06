package truco;

import java.util.ArrayList;

public class PartidaTruco {
	protected BaralhoTruco baralho; // Baralho da partida
	private ArrayList<RodadaTruco> rodadas; // Conjunto de rodadas pertencentes � partida
	private JogoTruco jogo; // Jogo ao qual a partida pertence
	private Dupla duplaVencedora; // Dupla vencedora da partida
	private boolean terminarPartida; // Indica se partida chegou ao fim ou n�o
	private int tipoPartida; // 2, 4, 8, 10 ou 12 pontos
	private int numJogadorInicial; // Primeiro jogador da partida
	private int numPartida; // N�mero da partida
	
	public PartidaTruco(JogoTruco jogo) {
		this.jogo = jogo;
		rodadas = new ArrayList<RodadaTruco>();
		baralho = new BaralhoTruco();
		baralho.embaralhar();
		tipoPartida = 2;

		numPartida = this.jogo.getNumeroPartidas();
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
		
		// Inst�ncia de uma nova Rodada
		RodadaTruco novaRodada = new RodadaTruco(this, maoJogador1, maoJogador2, maoJogador3, maoJogador4, false); // Par�metro empate da novaRodada igual � falso pois trata-se da primeira rodada do jogo
		novaRodada.setNumJogadorInicial(numJogadorInicial);
		novaRodada.rodadaTruco();
		rodadas.add(novaRodada); // Adiciona novaRodada ao Array de Partidas
	}
	
	public void partidaTruco() {
		System.out.println("\nRodada " + (getNumeroRodadas() + 1) + ": partida valendo " + tipoPartida + " pontos.");
		comecarNovaPartida();
		
		while(!terminarPartida) { // Enquanto partida n�o for finalizada
			RodadaTruco novaRodada = new RodadaTruco(this, rodadas.get(getNumeroRodadas() - 1).getMaoJogador1(), rodadas.get(getNumeroRodadas() - 1).getMaoJogador2(), 
					rodadas.get(getNumeroRodadas() - 1).getMaoJogador3(), rodadas.get(getNumeroRodadas() - 1).getMaoJogador4(), rodadas.get(getNumeroRodadas() - 1).getEmpate());
			novaRodada.setNumJogadorInicial(rodadas.get(getNumeroRodadas() - 1).getNumJogadorInicial());
			novaRodada.setNumJogadorAlteradorTipo(rodadas.get(getNumeroRodadas() - 1).getnumJogadorAlteradorTipo());
			System.out.println("\nRodada " + (getNumeroRodadas() + 1) + ": partida valendo " + tipoPartida + " pontos.");
			novaRodada.rodadaTruco();
			rodadas.add(novaRodada);
			
			if (rodadas.get(getNumeroRodadas() - 1).getEmpate() == false) { // Se rodada anterior n�o tiver terminado empatada
				if (novaRodada.getEmpate() == false) { // Se atual rodada n�o tiver terminado empatada
					if (novaRodada.getDuplaVencedora() == rodadas.get(0).getDuplaVencedora()) { // Se o vencedor da rodada anterior e da atual s�o duplas iguais
						terminarPartida = true; // Partida finalizada
					}
				}
			} else { // Sen�o
				if (novaRodada.getEmpate() == true) { // Se rodada atual tiver terminado empatada
					terminarPartida = true;
				} else { // Sen�o
					terminarPartida = false;
				}
			}
		}
		
		imprimeVencedorPartida();
	}
	
	public void imprimeVencedorPartida() {
		if (getNumeroRodadas() == 1) { // Se a partida tiver terminado com uma rodada, o vencedor da primeira rodada � o vencedor da partida
			duplaVencedora = rodadas.get(0).getDuplaVencedora();
			System.out.println("\n\t### Os vencedores da Partida " + (numPartida + 1) + " foram " + duplaVencedora.getJogador1().getNome() + " e " + duplaVencedora.getJogador2().getNome() + " ###");
		} else if (getNumeroRodadas() == 2) { // Se a partida tiver terminado com duas rodadas, o vencedor da segunda rodada � o vencedor da partida
			duplaVencedora = rodadas.get(1).getDuplaVencedora();
			System.out.println("\n\t### Os vencedores da Partida " + (numPartida + 1) + " foram " + duplaVencedora.getJogador1().getNome() + " e " + duplaVencedora.getJogador2().getNome() + " ###");
		} else if (getNumeroRodadas() == 3) { // Se a partida tiver terminado com tr�s rodadas
			if (rodadas.get(0).getEmpate() == true && rodadas.get(1).getEmpate() == true && rodadas.get(2).getEmpate() == true) { // Todas rodadas terminaram empatadas
				System.out.println("\n\t### A partida " + (numPartida + 1) + " terminou empatada. Nenhuma dupla ganhar� pontos. ###");
			} else {
				if (rodadas.get(0).getEmpate() == true && rodadas.get(1).getEmpate() == true) { // Se as duas primeiras rodadas tiverem terminado empatadas, o vencedor da terceira rodada � o vencedor da partida
					duplaVencedora = rodadas.get(2).getDuplaVencedora();
				} else {
					if (rodadas.get(0).getDuplaVencedora() == rodadas.get(2).getDuplaVencedora()) { // Vencedor primeira igual ao vencedor da �ltima
						duplaVencedora = rodadas.get(0).getDuplaVencedora();
					}
					if (rodadas.get(1).getDuplaVencedora() == rodadas.get(2).getDuplaVencedora()) { // Vencedor segunda igual ao vencedor da �ltima
						duplaVencedora = rodadas.get(1).getDuplaVencedora();
					}
				}
				
				System.out.println("\n\t### Os vencedores da partida foram " + duplaVencedora.getJogador1().getNome() + " e " + duplaVencedora.getJogador2().getNome() + " ###");
			}
		}
	}
	
	public void setNumPartida(int numPartida) {
		this.numPartida = numPartida;
	}
	
	public int getNumPartida(){
		return numPartida;
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
