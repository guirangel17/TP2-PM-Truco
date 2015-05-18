package truco;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.*;

public class Main {

	public static void main(String[] args) {
		Scanner e = new Scanner(System.in);
		String nome;
		int opUsuario = 0;
		
		while (opUsuario != 3) {
			System.out.println("\n\n\t\t\tTP 2 Programação Modular - Jogo de Truco");
			System.out.println("\n\n\t\t\t----------------- Menu -----------------");
			System.out.println("\t\t\t\t <1> Iniciar Jogo");
			System.out.println("\t\t\t\t <2> Sobre");
			System.out.println("\t\t\t\t <3> Sair");
			System.out.println("\n\n\t\t\tDigite uma opção <1, 2 ou 3>:");
			
			opUsuario = e.nextInt();
			
			if (opUsuario == 1) { // Iniciar o Jogo
				// Lê do teclado o nome dos jogadores e realiza a instância dos mesmos
				System.out.println("\nDUPLA 1:");
				System.out.println("Digite o nome do primeiro Jogador da dupla: ");
				nome = e.next();
				Jogador jogador1 = new Jogador(nome);
				System.out.println("Digite o nome do segundo Jogador da dupla: ");
				nome = e.next();
				Jogador jogador2 = new Jogador(nome);

				System.out.println("\nDUPLA 2:");
				System.out.println("Digite o nome do primeiro Jogador da dupla: ");
				nome = e.next();
				Jogador jogador3 = new Jogador(nome);
				System.out.println("Digite o nome do segundo Jogador da dupla: ");
				nome = e.next();
				Jogador jogador4 = new Jogador(nome);
				
				// Instâncias das duplas
				Dupla dupla1 = new Dupla(jogador1, jogador2);
				Dupla dupla2 = new Dupla(jogador3, jogador4);
				
				JogoTruco jogo = new JogoTruco(dupla1, dupla2);	// Instância do novo Jogo	
				jogo.comecarNovoJogo(); // Começa novo Jogo
			}
			
			if (opUsuario == 2) { // Sobre
				try {
					File arquivo = new File("C:\\Users\\Lucas Henrique\\git\\TP2-PM-Truco\\TP2\\src\\truco\\sobre.txt"); // Abre o arquivo comoJogar.txt
					FileReader fileReader = new FileReader(arquivo);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					
					if (arquivo.exists()) { // Verifica se o arquivo existe
						while (bufferedReader.ready()) { // Lê o arquivo até o seu final
							String linha = bufferedReader.readLine();
							System.out.println(linha);
						}
					}
				} catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (NumberFormatException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		}
		
	}
		
}
