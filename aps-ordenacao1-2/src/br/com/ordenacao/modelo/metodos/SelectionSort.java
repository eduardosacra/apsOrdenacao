package br.com.ordenacao.modelo.metodos;

public class SelectionSort extends Ordenacao {

	@Override
	public String getNameMetodo() {

		return "Selection Sort";
	}
	
	@Override
	public int[] getOrdenar(int[] vetor) {
		
		
		return selectionSort(vetor);
	}
	@Override
	public long getComparacao() {
		
		return this.comparacao;
	}

	@Override
	public long getMovimentacao() {
		
		return this.movimentacao;
	}
	private int[] selectionSort(int[] vetor) {
		
		
	        int  j, eleito, menor, pos;
	        
	 
	        for (int i = 0; i <= 3; i++) {
	            eleito = vetor[i];
	            menor = vetor[i + 1];
	            pos = i + 1;
	            for (j = i + 2; j <= 4; j++) {
	                if (vetor[j] < menor) {
	                    menor = vetor[j];
	                    pos = j;
	                    this.comparacao +=1;
	                    this.movimentacao +=1;
	                }else{
	                	this.comparacao +=1;	                	
	                }
	            }
	            if (menor < eleito) {
	                vetor[i] = vetor[pos];
	                vetor[pos] = eleito;
	                this.comparacao +=1;
	                this.movimentacao +=1;
                }else{
                	this.comparacao +=1;
                }
	        }
	 
	       
	    
		return vetor;
	}
}
