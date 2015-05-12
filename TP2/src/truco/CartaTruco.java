package truco;

public class CartaTruco extends Carta 
{
	private int ordemImportancia;
	
	public CartaTruco(int naipe, int valor) 
	{
		super(naipe, valor);
		this.ordemImportancia = calculaValorImportancia();
	}

	protected int calculaValorImportancia() {
		switch(this.valor) {
			case ICarta.AS:
				if(this.naipe == ICarta.ESPADAS)
					return 12;
				else
					return 8;
			
			case 2:
				return 9;
			
			case 3:
				return 10;
			
			case 4:
				if(this.naipe == ICarta.PAUS)
					return 14;
				else
					return 1;
			
			case 5:
				return 2;
			
			case 6:
				return 3;
			
			case 7:
				if(this.naipe == ICarta.COPAS) 
					return 13;
				else if(this.naipe == ICarta.OUROS) 
					return 11;
			    else 
					return 4;
				
			case ICarta.VALETE:
				return 6;
			
			case ICarta.DAMA:
				return 5;
			
			case ICarta.REI:
				return 7;	
			
			default:
				return 0;
		}	
	}

	public int getOrdemImportancia() {
		return ordemImportancia;
	}

	public void setOrdemImportancia(int ordemImportancia) {
		this.ordemImportancia = ordemImportancia;
	}
}
