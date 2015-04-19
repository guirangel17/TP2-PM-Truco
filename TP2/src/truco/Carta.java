package truco;

public class Carta implements ICarta
{
	private final int naipe;
	private final int valor;
	private final int ordemImportancia;
	private final String nome;
	
	private static final String[] naipesToString = 
	{
		"espadas", "copas", "ouros", "paus"	
	};
		
	private static final String[] valoresToString = 
	{
		"vazio","As","Dois","Três","Quatro","Cinco",
		"Seis","Sete","Oito","Nove","Dez",
		"Valete","Dama","Rei"	
	};
	
	public Carta(int naipe, int valor)
	{
		this.naipe = naipe;
		this.valor = valor;
		this.ordemImportancia = calculaValorImportancia();
		this.nome = valoresToString[getValor()]+" de "+
	             naipesToString[getNaipe()];
	}
	
	private int calculaValorImportancia()
	{
		switch(this.valor)
		{
			case ICarta.AS:
				if(this.naipe == ICarta.ESPADAS)
				{
					return 12;
				}
				else
				{
					return 8;
				}
			
			case 2:
				return 9;
			
			case 3:
				return 10;
			
			case 4:
				if(this.naipe == ICarta.PAUS)
				{
					return 14;
				}
				else
				{
					return 1;
				}
			
			case 5:
				return 2;
			
			case 6:
				return 3;
			
			case 7:
				if(this.naipe == ICarta.COPAS)
				{
					return 13;
				}
				else if(this.naipe == ICarta.OUROS)
				{
					return 11;
				}
				else
				{
					return 4;
				}
				
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

	public int getNaipe() {
		return naipe;
	}

	public int getValor() {
		return valor;
	}

	public int getOrdemImportancia() {
		return ordemImportancia;
	}

	public String getNome() {
		return nome;
	}
}
