package truco;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho 
{
	private ArrayList<Carta> listaCartas;
	private int teste;
	
	public Baralho()
	{
		listaCartas = new ArrayList<Carta>();
		
		for(int naipe = ICarta.COPAS; naipe <= ICarta.PAUS; naipe++)
		{
			for(int valor = 1; valor <= 13; valor++)
			{
				// O jogo de truco não utiliza as cartas 8,9 e 10 do baralho
				if(valor == 8)
					valor = 10;
				else
					listaCartas.add(new Carta(naipe, valor));
			}
		}
	}
	
	public void embaralhar()
	{
		Collections.shuffle(listaCartas);
	}
	
	public ArrayList<Carta> getListaCartas() {
		return listaCartas;
	}

	public void setListaCartas(ArrayList<Carta> listaCartas) {
		this.listaCartas = listaCartas;
	}
}
