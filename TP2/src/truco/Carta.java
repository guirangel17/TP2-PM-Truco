package truco;

public class Carta implements ICarta
{
	protected final int naipe;
	protected final String nome;
	protected int valor;
	protected int ordemImportancia;
	
	protected static final String[] naipesToString = 
	{
		"copas",
		"espadas",
		"ouros",
		"paus"	
	};

	protected static final String[] valoresToString = 
	{
		"vazio", // indice 0 = vazio
		"As",
		"Dois",
		"Três",
		"Quatro",
		"Cinco",
		"Seis",
		"Sete",
		"Oito",
		"Nove",
		"Dez",
		"Valete",
		"Dama",
		"Rei"	
	};
	
	public Carta(int naipe, int valor) {
		this.naipe = naipe;
		this.valor = valor;
		this.ordemImportancia = calculaValorImportancia();
		this.nome = valoresToString[getValor()] + " de " + naipesToString[getNaipe()];
	}
	
	protected int calculaValorImportancia() {
		return getValor();	
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
	
	public static int menorValor() {
		return AS;
	}
	
	public static int maiorValor() {
		return REI;
	}
	
	public static int primeiroNaipe() {
		return COPAS;
	}
	
	public static int ultimoNaipe() {
		return PAUS;
	}
}