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
	
	private Dupla duplaVencedora; // Dupla vencedora da Rodada
	private int numRodada; // Pode ser 1, 2 ou 3, pois há no máximo 3 rodadas em uma partida
	private int numJogadorInicial; // Primeiro jogador da rodada - o jogador que inicia a rodada é o que jogou a maior carta na rodada anterior - por default, jogador 1
	private int numJogadorAlteradorTipo; // Jogador que alterou o tipo da Partida (pediu truco/seis/nove/doze)
	private boolean empate; // Rodada terminou empatada ou não
	private static final int NUM_JOGADORES = 4; 
	
	public RodadaTruco(PartidaTruco partida, MaoJogadorTruco maoJogador1, MaoJogadorTruco maoJogador2, MaoJogadorTruco maoJogador3, MaoJogadorTruco maoJogador4, boolean empate) {
		this.partida = partida;
		this.maoJogador1 = maoJogador1;
		this.maoJogador2 = maoJogador2;
		this.maoJogador3 = maoJogador3;
		this.maoJogador4 = maoJogador4;
		this.empate = empate;
		
		numJogadorInicial = 1;
		numJogadorAlteradorTipo = -10; // Não existe nenhum jogador inicialmente que fez alteração no tipo da Partida
		numRodada = partida.getNumeroRodadas();
	}
	
	public void rodadaTruco() {
		CartaTruco maiorCarta = new CartaTruco(ICarta.COPAS, 4); // Inicializada com o menor valor de carta do truco mineiro - Auxiliar na verificação da maior carta da rodada
		boolean empateAnterior = empate; // Rodada anterior (se existente) empatou (true/false)
		int contador = 0; // Contador do número de cartas jogadas na rodada
		int op = 0; // Opcao escolhida pelo usuário
		int numJogador = numJogadorInicial;

		while (contador < RodadaTruco.NUM_JOGADORES && partida.getTerminarPartida() == false) { // Enquanto os 4 jogadores da rodada não jogar
			if (empateAnterior) { // Se rodada anterior empatada
				// Jogadores tem que mostrar maior carta, mudar tipo da partida, ou desistir do jogo
				op = opcoesMenuEmpate(numJogador, partida.getTipoPartida());
				
				if (op == 1) { // Jogador deseja jogar sua maior carta
					MaoJogadorTruco maoJogador = localizaMao(numJogador);
					CartaTruco cartaAtual;
					if (CartaTruco.empate(maoJogador.maiorCarta(), maoJogador.getCartasMao().get(0))) { // Verifica o índice da maior carta
						cartaAtual = jogaCarta(numJogador, 0); // Retira a carta da mão do jogador
					} else {
						cartaAtual = jogaCarta(numJogador, 1);
					}
					
					System.out.println("\n\t Jogador " + numJogador + " jogou: " + cartaAtual.getNome());
					
					// Número do próximo jogador que irá jogar
					if (numJogador == 4) {
						numJogador = 1;
					} else {
						numJogador++;
					}
					
					maiorCarta = CartaTruco.retornaMaiorCarta(maiorCarta, cartaAtual); // Verifica a maior carta jogada até então na rodada
				}
				
				if (op == 4) { // Jogador deseja pedir Truco/Seis/Nove/Doze
					contador--;
					numJogadorAlteradorTipo = numJogador;
					trataTipoPartida(numJogador);
				}
				
				if (op == -1) { // Jogador deseja deixar o jogo
					trataDesistencia(numJogador);
				}
				
			} else { // Se rodada anterior não tiver terminado empatada
				op = opcoesMenu(numJogador, partida.getTipoPartida());
				
				if (op == 1 || op == 2 || op == 3) { // Jogador deseja jogar uma carta
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
					contador--;
					numJogadorAlteradorTipo = numJogador;
					trataTipoPartida(numJogador);
				}
				
				if (op == -1) { // Deixar o jogo
					trataDesistencia(numJogador);
				}
			}
			contador++; // Uma carta de um jogador foi jogada
		}
		
		// Verifica se houve empate entre a maior carta jogada na rodada e a carta de algum outro jogador
		 if (!partida.getTerminarPartida()) { // Se a partida não estiver finalizada
			for (int i = 1; i <= RodadaTruco.NUM_JOGADORES; i++) {
				// Não compara com o proprio jogador nem com sua dupla
				if (i != jogadorMaiorCartaRodada(maiorCarta) && i + 2 != jogadorMaiorCartaRodada(maiorCarta) && i - 2 != jogadorMaiorCartaRodada(maiorCarta)) {
					MaoJogadorTruco maoJogadorAtual = localizaMao(i);
						
					if (CartaTruco.empate(maiorCarta, maoJogadorAtual.getCartasJogadas().get(numRodada))) { // Se houver empate
						empate = true;
						if (getNumRodada() != 0) { // Se o empate ocorreu na segunda ou terceira rodada, ganha a dupla que ganhou a primeira rodada
							duplaVencedora = partida.getRodadas().get(0).duplaVencedora;
							partida.setTerminarPartida(true);
						}
						System.out.println("\n\n\t##A Rodada " + (numRodada + 1) + " terminou empatada. ##");
						break;
					} else { // Senão
						empate = false;
					}
				}
			}
			
			if (empate == false) { // Em caso de não haver empate, ganha a dupla que jogou a maior carta na rodada
				if (jogadorMaiorCartaRodada(maiorCarta) == 1 || jogadorMaiorCartaRodada(maiorCarta) == 3) {
					duplaVencedora = partida.getJogo().getDupla1();
				} else {
					duplaVencedora = partida.getJogo().getDupla2();
				}
				if (empateAnterior) { // Se a rodada anterior tiver terminado empatada
					partida.setTerminarPartida(true); // Termina a partida
				}
				System.out.println("\n\n\t## Os vencedores da Rodada " + (numRodada + 1) + " foram " + duplaVencedora.getJogador1().getNome() 
						+ " e " + duplaVencedora.getJogador2().getNome() + " ##");
			}
			
			if (numRodada == 2) { // Ao final da terceira rodada, a partida finaliza-se, independente do vencedor
				partida.setTerminarPartida(true);
			}
			
			setNumJogadorInicial(jogadorMaiorCartaRodada(maiorCarta));	
		 }
		
	}
	
	public void trataDesistencia(int numJogador) {
		if (numJogador == 1 || numJogador == 3) { // Alguém da dupla 1 desistiu
			duplaVencedora = partida.getJogo().getDupla2();
			partida.getJogo().setPontuacaoDupla2(12);
		} else { // Alguém da dupla 2 desistiu
			duplaVencedora = partida.getJogo().getDupla1();
			partida.getJogo().setPontuacaoDupla1(12);
		}
		partida.setTerminarPartida(true);
		
		System.out.println("\n\n\t## Jogador " + (numJogador) + " desistiu do jogo. ##");
	}
	
	public void trataTipoPartida(int numJogador) {
		String tipo = "";
		String proxTipo = "";
		int opT = 0;
		int aux = 1;
		int numJogadorAux = numJogador;
		while (opT != 1 && opT != 2) { // Enquanto jogador não aceitar/recusar o novo tipo da partida
			if (partida.getTipoPartida() == IPartidaTruco.COMUM) {
				tipo = "Truco";
				proxTipo = "Seis";
			} else if (partida.getTipoPartida() == IPartidaTruco.TRUCO) {
				tipo = "Seis";
				proxTipo = "Nove";
			} else if (partida.getTipoPartida() == IPartidaTruco.SEIS) {
				tipo = "Nove";
				proxTipo = "Doze";
			} else if (partida.getTipoPartida() == IPartidaTruco.NOVE) {
				tipo = "Doze";
				proxTipo = "Queda";
			}
			
			System.out.println("\n\t ##### Jogador " + numJogadorAux + " pediu " + tipo + " #####");
			
			if (aux % 2 == 1) {
				if (numJogadorAux == 4) {
					numJogadorAux = 1;
				} else {
					numJogadorAux++;
				}
			} else {
				if (numJogadorAux == 1) {
					numJogadorAux = 4;
				} else {
					numJogadorAux--;
				}
			}
			
			opT = trataTipoPartidaAux(numJogadorAux, proxTipo);
			
			if (partida.getTipoPartida() == IPartidaTruco.COMUM) {
				partida.setTipoPartida(4);
			} else if (partida.getTipoPartida() == IPartidaTruco.TRUCO) {
				partida.setTipoPartida(8);
			} else if (partida.getTipoPartida() == IPartidaTruco.SEIS) {
				partida.setTipoPartida(10);
			} else if (partida.getTipoPartida() == IPartidaTruco.NOVE) {
				partida.setTipoPartida(12);
			}
			
			aux++;
		}
		
		if (opT == 1) { // Aceitou
			System.out.println("\n\t ##### Jogador " + numJogadorAux + " aceitou o " + tipo + " #####");
		}
		if (opT == 2) { // Recusou
			System.out.println("\n\t ##### Jogador " + numJogador + " recusou o " + tipo + " #####");
			
			if (partida.getTipoPartida() == IPartidaTruco.TRUCO) {
				partida.setTipoPartida(2);
			} else if (partida.getTipoPartida() == IPartidaTruco.SEIS) {
				partida.setTipoPartida(4);
			} else if (partida.getTipoPartida() == IPartidaTruco.NOVE) {
				partida.setTipoPartida(8);
			} else if (partida.getTipoPartida() == IPartidaTruco.QUEDA) {
				partida.setTipoPartida(10);
			}
			
			if (numJogadorAux == 1 || numJogadorAux == 3) {
				duplaVencedora = partida.getJogo().getDupla2();
			} else {
				duplaVencedora = partida.getJogo().getDupla1();
			}
			partida.setTerminarPartida(true);
		}
	}
	
	public int trataTipoPartidaAux(int numJogador, String proxTipo) {
		Scanner e = new Scanner(System.in);
		int opUsuario = 0;
		
		imprimeMao(numJogador);

		System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:");
		System.out.println("<1> para aceitar");
		System.out.println("<2> para recusar");
		if (proxTipo != "Queda") {
			System.out.println("<4> para pedir " + proxTipo);
			while (opUsuario != 1 && opUsuario != 2 && opUsuario != 4) {
				opUsuario = e.nextInt();
			}
		} else {
			while (opUsuario != 1 && opUsuario != 2) {
				opUsuario = e.nextInt();
			}
		}

		return opUsuario;
	}
	
	public int opcoesMenu(int numJogador, int tipoPartida) {
		Scanner e = new Scanner(System.in);
		MaoJogadorTruco maoJogador = localizaMao(numJogador);
		String tipo = "";
		int opUsuario = 0;
		int numCartas = maoJogador.getNumeroCartasMao();
		
		System.out.println("---------------------------------------------------");
		
		imprimeMao(numJogador);
		
		if (tipoPartida == IPartidaTruco.COMUM) {
			tipo = "Truco";
		} else if (tipoPartida == IPartidaTruco.TRUCO) {
			tipo = "Seis";
		} else if (tipoPartida == IPartidaTruco.SEIS) {
			tipo = "Nove";
		} else if (tipoPartida == IPartidaTruco.NOVE) {
			tipo = "Doze";
		}
		
		// Jogador que alterou o tipo da partida e dupla não podem pedir outra alteração no tipo da partida enquanto alguém da outra dupla não solicitar nova alteração
		// Adequa os menus exibidos para cada jogador de acordo com quantas cartas ele tem na mão
		if (numJogador != numJogadorAlteradorTipo && (numJogador + 2) != numJogadorAlteradorTipo  && 
				(numJogador - 2) != numJogadorAlteradorTipo && partida.getTipoPartida() != 12) {
			if (numCartas == 3) {
				System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1, 2 ou 3> para jogar "
						+ "uma carta\n<4> para pedir " + tipo + "\n<-1> para deixar o jogo");

				while (opUsuario != 1 && opUsuario != 2 && opUsuario != 3 && opUsuario != 4 && opUsuario != -1) {
					opUsuario = e.nextInt();
				}
			} else if (numCartas == 2) {
				System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1 ou 2> para jogar uma "
						+ "carta\n<4> para pedir " + tipo + "\n<-1> para deixar o jogo");
				
				while (opUsuario != 1 && opUsuario != 2 && opUsuario != 4 && opUsuario != -1) {
					opUsuario = e.nextInt();
				}
			} else if (numCartas == 1) {
				System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1> para jogar a carta\n"
						+ "<4> para pedir " + tipo + "\n<-1> para deixar o jogo");
				
				while (opUsuario != 1 && opUsuario != 4 && opUsuario != -1) {
					opUsuario = e.nextInt();
				}
			}
		} else {
			if (numCartas == 3) {
				System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1, 2 ou 3> para jogar "
						+ "uma carta\n<-1> para deixar o jogo");

				while (opUsuario != 1 && opUsuario != 2 && opUsuario != 3 && opUsuario != -1) {
					opUsuario = e.nextInt();
				}
			} else if (numCartas == 2) {
				System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1 ou 2> para jogar "
						+ "uma carta\n<-1> para deixar o jogo");
				
				while (opUsuario != 1 && opUsuario != 2 && opUsuario != -1) {
					opUsuario = e.nextInt();
				}
			} else if (numCartas == 1) {
				System.out.println("\n# Jogador " + numJogador + ", escolha uma opção:\n<1> para jogar a carta"
						+ "\n<-1> para deixar o jogo");
				
				while (opUsuario != 1 && opUsuario != -1) {
					opUsuario = e.nextInt();
				}
			}
		}
		
		
		return opUsuario;
	}
	
	public int opcoesMenuEmpate(int numJogador, int tipoPartida) {
		MaoJogadorTruco maoJogador = localizaMao(numJogador);
		Scanner e = new Scanner(System.in);
		String tipo = "";
		int opUsuario = 0;
		
		System.out.println("---------------------------------------------------");
		System.out.println("\n\t\tMaior Carta Jogador " + numJogador + "(" + maoJogador.getJogador().getNome() + "):");
		System.out.println("\t\t<1> " + maoJogador.maiorCarta().getNome());
		
		if (tipoPartida == IPartidaTruco.COMUM) {
			tipo = "Truco";
		} else if (tipoPartida == IPartidaTruco.TRUCO) {
			tipo = "Seis";
		} else if (tipoPartida == IPartidaTruco.SEIS) {
			tipo = "Nove";
		} else if (tipoPartida == IPartidaTruco.NOVE) {
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
		System.out.println("\n\t\tCartas Jogador " + numJogador + " (" + maoJogador.getJogador().getNome() + "):");
		for (int i=0; i<maoJogador.getNumeroCartasMao(); i++) {
			System.out.println("\t\t<" + (i+1) + "> " + maoJogador.getCartasMao().get(i).getNome());
		}
	}
	
	public int jogadorMaiorCartaRodada(CartaTruco maiorCarta) {
		// Retorna o número do jogador que possui a maior carta da rodada
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
	
	public void setNumJogadorInicial(int numJogadorInicial) {
		this.numJogadorInicial = numJogadorInicial;
	}
	
	public int getNumJogadorInicial() {
		return numJogadorInicial;
	}
	
	public void setNumJogadorAlteradorTipo(int numJogadorAlteradorTipo) {
		this.numJogadorAlteradorTipo = numJogadorAlteradorTipo;
	}
	
	public int getnumJogadorAlteradorTipo() {
		return numJogadorAlteradorTipo;
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
