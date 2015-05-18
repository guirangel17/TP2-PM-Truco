package truco;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Um baralho comum formado por uma lista de 52 cartas:
 * contem 13 valores (AS, 2, 3, ..., 10, VALETE, DAMA, REI) de 4 naipes (COPAS, ESPADAS, OUROS, PAUS)
 */
public class Baralho {
	protected ArrayList<Carta> listaCartas;
	
	public Baralho() {
		listaCartas = new ArrayList<Carta>();
		
		for(int naipe = Carta.primeiroNaipe(); naipe <= Carta.ultimoNaipe(); naipe++) {
			for(int valor = Carta.menorValor(); valor <= Carta.maiorValor(); valor++) {
				listaCartas.add(criaCarta(naipe, valor)); // Preenche o baralho com cartas comuns
			}
		}
	}
	
	public void setListaCartas(ArrayList<Carta> listaCartas) {
		this.listaCartas = listaCartas;
	}
	
	public ArrayList<Carta> getListaCartas() {
		return listaCartas;
	}
	
	protected Carta criaCarta(int naipe, int valor) {
		return new Carta(naipe, valor);
	}
	
	public Carta pegaCarta() {
	    if(numeroCartas() == 0)
	    	return null;
	    else
	    	return (Carta) listaCartas.remove(numeroCartas() - 1); // Retira e retorna a carta do topo do baralho
	}
	
	public void embaralhar() {
		Collections.shuffle(listaCartas);
	}

	public int numeroCartas() {
		return listaCartas.size();
	}
}
