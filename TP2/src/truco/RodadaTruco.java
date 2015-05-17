package truco;

import java.io.IOException;
import java.util.Scanner;

public class RodadaTruco {
	// Partida à qual a rodada pertence
	private PartidaTruco partida;
	
	// Mão dos jogadores
	private MaoJogadorTruco maoJogador1;
	private MaoJogadorTruco maoJogador2;
	private MaoJogadorTruco maoJogador3;
	private MaoJogadorTruco maoJogador4;
	
	private Dupla duplaVencedora;
	private int numRodada; // Pode ser 1,2 ou 3, pois há no máximo 3 rodadas em uma partida
	private boolean empate;
	private static final int NUM_JOGADORES = 4; 
	
	public RodadaTruco(PartidaTruco partida, MaoJogadorTruco maoJogador1, MaoJogadorTruco maoJogador2, MaoJogadorTruco maoJogador3, MaoJogadorTruco maoJogador4, boolean empate) {
		this.partida = partida;
		this.maoJogador1 = maoJogador1;
		this.maoJogador2 = maoJogador2;
		this.maoJogador3 = maoJogador3;
		this.maoJogador4 = maoJogador4;
		this.empate = empate;

		numRodada = partida.getNumeroRodadas();
		
		rodadaTruco(1); // ----------------------- MUDAR
	}
	
	public void rodadaTruco(int numJogadorInicial) {
		boolean empateAnterior = empate;
		int numJogador = numJogadorInicial;
		int contador = 0; // Contador do número de cartas jogadas na rodada
		int op; // Opcao escolhida pelo usuário
		int i;
		CartaTruco maiorCarta = new CartaTruco(ICarta.COPAS, 4); // Inicializada com o menor valor de carta do truco mineiro
		
		Jogador jogador = localizaJogador(numJogador);

		while (contador < RodadaTruco.NUM_JOGADORES) {
			if (empateAnterior) { // Se primeira rodada terminar empatada
				op = opcoesMenuEmpate(numJogador, partida.getTipoPartida());
				if (op == 1) {
					CartaTruco cartaAtual = jogaCarta(numJogador, op - 1); // Retira a carta da mão do jogador
					System.out.println("\n\t Jogador " + numJogador + " jogou: " + cartaAtual.getNome());
					if (numJogador == 4) {
						numJogador = 1;
					} else {
						numJogador++;
					}
					
					maiorCarta = CartaTruco.retornaMaiorCarta(maiorCarta, cartaAtual); // Verifica a maior carta jogada até então na rodada
				}
				
			} else { // Senão
				op = opcoesMenu(numJogador, partida.getTipoPartida());
				
				if (op == 1 || op == 2 || op == 3) { // Uma carta foi jogada
					CartaTruco cartaAtual = jogaCarta(numJogador, op - 1); // Retira a carta da mão do jogador
					System.out.println("\n\t Jogador " + numJogador + " jogou: " + cartaAtual.getNome());
					if (numJogador == 4) {
						numJogador = 1;
					} else {
						numJogador++;
					}
					
					maiorCarta = CartaTruco.retornaMaiorCarta(maiorCarta, cartaAtual); // Verifica a maior carta jogada até então na rodada
				}
				
				if (op == 4) { // Pedido de Truco, Seis, Nove ou Doze
					
				}
				
				if (op == -1) { // Deixar o jogo
					
				}
			}
			contador++;
		}
		
		// Verifica se houve empate entre a maior carta jogada na rodada e a carta de algum outro jogador
		for (i = 1; i <= RodadaTruco.NUM_JOGADORES; i++) {
			// Não compara com o proprio jogador nem com sua dupla
			if (i != jogadorMaiorCartaRodada(maiorCarta) && i + 2 != jogadorMaiorCartaRodada(maiorCarta) && i - 2 != jogadorMaiorCartaRodada(maiorCarta)) {
				MaoJogadorTruco maoJogadorAtual = localizaMao(i);
					
				if (CartaTruco.empate(maiorCarta, maoJogadorAtual.getCartasJogadas().get(numRodada))) {
					empate = true;
					if (getNumRodada() != 0) { // Ganha a dupla que ganhou a primeira rodada
						duplaVencedora = partida.getRodadas().get(0).duplaVencedora;
						partida.setTerminarPartida(true);
					}
					System.out.println("\n\n\t## Rodada 0" + (numRodada + 1) + " terminou empatada. ##");
					break;
				} else {
					empate = false;
				}
			}
		}
		
		if (empate == false) { // Em caso de não haver empate, ganha a dupla que jogou a maior carta
			if (jogadorMaiorCartaRodada(maiorCarta) == 1 || jogadorMaiorCartaRodada(maiorCarta) == 3) {
				duplaVencedora = partida.getJogo().getDupla1();
			} else {
				duplaVencedora = partida.getJogo().getDupla2();
			}
			if (empateAnterior) {
				partida.setTerminarPartida(true);
			}
			System.out.println("\n\n\t## Os vencedores da rodada foram " + duplaVencedora.getJogador1().getNome() + " e " + duplaVencedora.getJogador2().getNome() + " ##");
		}
		
		if (numRodada == 2) { // Ao final da terceira rodada, a partida finaliza-se
			partida.setTerminarPartida(true);
		}
	}
	
	public int opcoesMenu(int numJogador, int tipoPartida) {
		MaoJogadorTruco maoJogador = localizaMao(numJogador);
		Scanner e = new Scanner(System.in);
		String tipo = "";
		int opUsuario = 0;
		int numCartas = maoJogador.getNumeroCartasMao();
		
		System.out.println("---------------------------------------------------");
		
		imprimeMao(numJogador);
		
		if (tipoPartida == 2) {
			tipo = "Truco";
		} else if (tipoPartida == 4) {
			tipo = "Seis";
		} else if (tipoPartida == 8) {
			tipo = "Nove";
		} else if (tipoPartida == 10) {
			tipo = "Doze";
		}
			
		if (numCartas == 3) {
			System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1, 2 ou 3> para jogar uma carta\n<4> para pedir " + tipo + "\n<-1> para deixar o jogo");
			
			while (opUsuario != 1 && opUsuario != 2 && opUsuario != 3 && opUsuario != 4) {
				opUsuario = e.nextInt();
			}
		} else if (numCartas == 2) {
			System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1 ou 2> para jogar uma carta\n<4> para pedir " + tipo + "\n<-1> para deixar o jogo");
			
			while (opUsuario != 1 && opUsuario != 2 && opUsuario != 4) {
				opUsuario = e.nextInt();
			}
		} else if (numCartas == 1) {
			System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1> para jogar a carta\n<4> para pedir " + tipo + "\n<-1> para deixar o jogo");
			
			while (opUsuario != 1 && opUsuario != 4) {
				opUsuario = e.nextInt();
			}
		}
		
		return opUsuario;
	}
	
	public int opcoesMenuEmpate(int numJogador, int tipoPartida) {
		MaoJogadorTruco maoJogador = localizaMao(numJogador);
		Scanner e = new Scanner(System.in);
		String tipo = "";
		int opUsuario = 0;
		int numCartas = maoJogador.getNumeroCartasMao();
		
		System.out.println("---------------------------------------------------");
		System.out.println("\n\t\tMaior Carta Jogador " + numJogador);
		System.out.println("\t\t<1> " + maoJogador.maiorCarta().getNome());
		
		if (tipoPartida == 2) {
			tipo = "Truco";
		} else if (tipoPartida == 4) {
			tipo = "Seis";
		} else if (tipoPartida == 8) {
			tipo = "Nove";
		} else if (tipoPartida == 10) {
			tipo = "Doze";
		}
		
		System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1> para jogar sua carta\n<4> para pedir " + tipo + "\n<-1> para deixar o jogo");
		while (opUsuario != 1 && opUsuario != 4) {
			opUsuario = e.nextInt();
		}
		
		return opUsuario;
	}
	
	public void imprimeMao(int numJogador) {
		// Localiza a mao do jogador passado por parâmetro
		MaoJogadorTruco maoJogador = localizaMao(numJogador);
		
		// Imprime as cartas do jogador
		System.out.println("\n\t\tCartas Jogador " + numJogador + ":");
		for (int i=0; i<maoJogador.getNumeroCartasMao(); i++) {
			System.out.println("\t\t<" + (i+1) + "> " + maoJogador.getCartasMao().get(i).getNome());
		}
	}
	
	public int jogadorMaiorCartaRodada(CartaTruco maiorCarta) {
		for(int i = 1; i <= RodadaTruco.NUM_JOGADORES; i++) {
			MaoJogadorTruco maoJogadorAtual = localizaMao(i);
			if(CartaTruco.empate(maiorCarta, maoJogadorAtual.getCartasJogadas().get(numRodada))) {
				return i;
			}
		}
		
		return 0;
	}
	
	public CartaTruco jogaCarta(int numJogador, int indexCarta) {
		switch(numJogador) {
			case 1:
				return maoJogador1.jogaCarta(indexCarta);
			case 2:
				return maoJogador2.jogaCarta(indexCarta);
			case 3:
				return maoJogador3.jogaCarta(indexCarta);
			case 4:
				return maoJogador4.jogaCarta(indexCarta);
			default:
				return maoJogador1.jogaCarta(indexCarta);
		}
	}
	
	public MaoJogadorTruco localizaMao(int numJogador) {
		switch(numJogador) {
			case 1:
				return this.maoJogador1;
			case 2:
				return this.maoJogador2;
			case 3:
				return this.maoJogador3;
			case 4:
				return this.maoJogador4;
			default:
				return this.maoJogador1;
		}
	}
	
	public Jogador localizaJogador(int numJogador) {
		switch(numJogador) {
			case 1:
				return this.maoJogador1.getJogador();
			case 2:
				return this.maoJogador2.getJogador();
			case 3:
				return this.maoJogador3.getJogador();
			case 4:
				return this.maoJogador4.getJogador();
			default:
				return this.maoJogador1.getJogador();
		}
	}
	
	public void setPartida(PartidaTruco partida) {
		this.partida = partida;
	}
	
	public PartidaTruco getPartida() {
		return partida;
	}
	
	public void setNumRodada(int numRodada) {
		this.numRodada = numRodada;
	}
	
	public int getNumRodada(){
		return numRodada;
	}
	
	public void setDuplaVencedora(Dupla duplaVencedora) {
		this.duplaVencedora = duplaVencedora;
	}
	
	public Dupla getDuplaVencedora() {
		return duplaVencedora;
	}
	
	public void setEmpate(boolean empate) {
		this.empate = empate;
	}
	
	public boolean getEmpate() {
		return empate;
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