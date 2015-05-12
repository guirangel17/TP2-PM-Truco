package truco;

import java.util.ArrayList;

/*
 * Um baralho para jogar truco formado por uma lista de 40 cartas:
 * contem 10 valores (AS, 2, 3, 4, 5, 6, 7, VALETE, DAMA, REI) de 4 naipes (COPAS, ESPADAS, OUROS, PAUS)
 */
public class BaralhoTruco extends Baralho {
	public BaralhoTruco() {
		listaCartas = new ArrayList<Carta>();
		
		for(int naipe = Carta.primeiroNaipe(); naipe <= Carta.ultimoNaipe(); naipe++) {
			for(int valor = Carta.menorValor(); valor <= Carta.maiorValor(); valor++) {
				if (valor == 8)
					valor = 10;
				else 
					listaCartas.add(criaCarta(naipe, valor)); // Preenche o baralho com cartas de truco
			}
		}
	}

	protected CartaTruco criaCarta(int naipe, int valor) {
		return new CartaTruco(naipe, valor);
	}
}
