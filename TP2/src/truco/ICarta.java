package truco;

public interface ICarta 
{
	public static final int COPAS = 0;
	public static final int ESPADAS = 1;
	public static final int OUROS = 2;
	public static final int PAUS = 3;
	
	public static final int AS = 1;
	public static final int VALETE = 11;
	public static final int DAMA = 12;
	public static final int REI = 13;
	
	public int getNaipe();
	public int getValor();
	public int getOrdemImportancia();
	public String getNome();
}
