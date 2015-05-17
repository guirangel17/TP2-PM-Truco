package truco;

import java.util.ArrayList;

public class MaoJogadorTruco {
	private ArrayList<CartaTruco> cartasMao;
	private ArrayList<CartaTruco> cartasJogadas;
	private Jogador jogador;
	
	public MaoJogadorTruco (Jogador jogador, BaralhoTruco baralho){
		this.cartasMao = new ArrayList<CartaTruco>();
		this.cartasJogadas = new ArrayList<CartaTruco>();
		this.jogador = jogador;
		cartasMao.add((CartaTruco) baralho.pegaCarta());
		cartasMao.add((CartaTruco) baralho.pegaCarta());
		cartasMao.add((CartaTruco) baralho.pegaCarta());
	}
	
	// Retira a carta da m�o do Jogador
	public CartaTruco jogaCarta(int indexCarta) {
		CartaTruco cartaJogada = cartasMao.remove(indexCarta);
		cartasJogadas.add(cartaJogada);
		return cartaJogada;
	}
	
	// Retorna a carta de maior valor do Jogador
	public CartaTruco maiorCarta() {
		CartaTruco aux;
		if (cartasMao.size() == 1) {
			aux = cartasMao.get(0);
		} else {
			aux = CartaTruco.retornaMaiorCarta(cartasMao.get(0), cartasMao.get(1));
			if (cartasMao.size() > 2) {
				return CartaTruco.retornaMaiorCarta(aux, cartasMao.get(2));
			}
		}
		
		return aux;
	}
	
	public ArrayList<CartaTruco> getCartasMao() {
		return cartasMao;
	}

	public void setCartasMao(ArrayList<CartaTruco> cartasMao) {
		this.cartasMao = cartasMao;
	}

	public ArrayList<CartaTruco> getCartasJogadas() {
		return cartasJogadas;
	}

	public void setCartasJogadas(ArrayList<CartaTruco> cartasJogadas) {
		this.cartasJogadas = cartasJogadas;
	}

	public Jogador getJogador() {
		return jogador;
	}
	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public int getNumeroCartasMao() {
		return cartasMao.size();
	}
}
