package br.com.ordenacao.modelo.vetor;

import java.util.Random;

public class Aleatorio implements Vetor {

	
	private int[] gereVetorAleatorioNaoRepetido(int tamanho) {
		int[] vetor = new int[tamanho];
		Random rd = new Random();

		for (int i = 0; i < vetor.length; i++) {
			int valor=0;			
			valor = rd.nextInt(tamanho);
			vetor[valor]=i;
		}
		
		return vetor;
	}

	private boolean testaValorCasoExista(int[] vetor, int valor) {
		for (int i = 0; i < vetor.length; i++) {
			if(valor == vetor[i]){
				return true;
			}
		}
		return false;
	}

	@Override
	public int[] getVetor(int tamanho) {
		
		return gereVetorAleatorioNaoRepetido(tamanho);
	}

	@Override
	public String getNomeVetor() {
		// TODO Auto-generated method stub
		return "Aleatorio";
	}

}
