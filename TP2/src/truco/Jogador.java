package truco;

public class Jogador 
{
	private String nome;
	private String login;
	
	public Jogador(String nome, String login)
	{
		this.nome = nome;
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
