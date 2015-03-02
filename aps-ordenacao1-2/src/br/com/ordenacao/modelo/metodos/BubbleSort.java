package br.com.ordenacao.modelo.metodos;

import br.com.ordenacao.controler.ControleModelo;
import br.com.ordenacao.visao.Visao;

public class BubbleSort extends Ordenacao {
	

	public BubbleSort() {

		super();
	}

	@Override
	public int[] getOrdenar(int[] v) {
		this.comparacao = 0;
		this.movimentacao = 0;
		int teste = 0;
		for (int i = v.length; i >= 1; i--) {

			for (int j = 1; j < i; j++) {

				if (v[j - 1] > v[j]) {
					int aux = v[j];
					v[j] = v[j - 1];
					v[j - 1] = aux;

					movimentacao+=1;
					comparacao+=1;
				}else{
					comparacao+=1;
				}
			}

		}	
		
		return v;
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
