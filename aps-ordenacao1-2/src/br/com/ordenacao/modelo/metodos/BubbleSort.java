package br.com.ordenacao.modelo.metodos;

import br.com.ordenacao.controler.ControleModelo;
import br.com.ordenacao.visao.Visao;

public class BubbleSort extends Ordenacao {
	

	@Override
	public int[] getOrdenar(int[] vetor) {
		this.comparacao = 0;
		this.movimentacao = 0;
		int teste = 0;
		for (int i = vetor.length; i >= 1; i--) {
			this.comparacao += 1;
			for (int j = 1; j < i; j++) {
				this.comparacao += 1;
				this.movimentacao+=1;
				if (vetor[j - 1] > vetor[j]) {
					int aux = vetor[j];
					vetor[j] = vetor[j - 1];
					vetor[j - 1] = aux;
				}
			}
			
		}	
		
		return vetor;
	}

	@Override
	public String getNameMetodo() {

		return "BubbleSort";
	}

	@Override
	public long getComparacao() {
		// TODO Auto-generated method stub
		return this.comparacao;
	}
	
	@Override
	public long getMovimentacao() {
		return movimentacao;
	}
}
