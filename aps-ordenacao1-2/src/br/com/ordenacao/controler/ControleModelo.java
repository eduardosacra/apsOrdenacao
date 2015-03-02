package br.com.ordenacao.controler;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import br.com.ordenacao.modelo.metodos.BubbleSort;
import br.com.ordenacao.modelo.metodos.Ordenacao;
import br.com.ordenacao.modelo.metodos.QuickSort;
import br.com.ordenacao.modelo.metodos.teste1;
import br.com.ordenacao.modelo.metodos.teste2;
import br.com.ordenacao.modelo.vetor.Aleatorio;
import br.com.ordenacao.modelo.vetor.OrdenadoCrescente;
import br.com.ordenacao.modelo.vetor.OrdenadoDecrescente;
import br.com.ordenacao.modelo.vetor.QuaseOrdenado;
import br.com.ordenacao.modelo.vetor.Vetor;
import br.com.ordenacao.visao.Visao;

public class ControleModelo {

	int tamanho, metodoUtilizado;
	public ArrayList<Vetor> vetores;
	public ArrayList<Ordenacao> metodos;
	public long tempos[];
	public ControlerVisao controleVisao;
	public int indiceDoMetodoUtilizado;

	public ControleModelo(ControlerVisao controleVisao) {
		// TODO Auto-generated constructor stub
		this.controleVisao = controleVisao;

		vetores = new ArrayList<Vetor>();
		vetores.add(new Aleatorio());
		vetores.add(new QuaseOrdenado());
		vetores.add(new OrdenadoCrescente());
		vetores.add(new OrdenadoDecrescente());
		

		metodos = new ArrayList();
		metodos.add(new BubbleSort());
		metodos.add(new QuickSort());
		metodos.add(new teste1());
		metodos.add(new teste2());
		
		

	}

	public String[] getMetodos() {
		// retorna o nome dos metodos
		String[] nome = new String[this.metodos.size()];

		int i = 0;

		for (Ordenacao ordenacao : this.metodos) {
			nome[i] = ordenacao.getNameMetodo();
			i++;
		}

		return nome;
	}

	public String[] getNomeVetores() {
		// retorna o nome dos vetores

		String[] nome = new String[this.vetores.size()];

		int aux = 0;
		for (Vetor vetor : this.vetores) {
			nome[aux] = vetor.getNomeVetor();
			aux += 1;
		}

		return nome;

	}

	public void getOrdenacao(int tamanho, int qualVetor) {
		
		
		int[] vetor = vetores.get(qualVetor).getVetor(tamanho);	
		
		
		for (int i = 0; i < metodos.size(); i++) {
			this.indiceDoMetodoUtilizado = i;//aqui popula o indice para saber qual das linhas sera alterado
			
			
			int[] vetorClone = vetor.clone();
			System.out.println("************************");
			System.out.println("Metodo "+metodos.get(i).getNameMetodo());
			System.out.println("Aqui é vetor "+vetores.get(qualVetor).getNomeVetor());
			System.out.println("nao ordenado");
			for (int j = 0; j < vetorClone.length; j++) {
				System.out.print(vetorClone[j]+"  ");
			}
			System.out.println();
			System.out.println("************************");
			/*String vertorDeserdenado ="";
			for (int j = 0; j < vetorClone.length; j++) {
				 vertorDeserdenado += vetorClone[j]+" ";
			}
			JOptionPane.showMessageDialog(null, "Vertor Desordenado: n"
				+vertorDeserdenado);*/
			
			this.controleVisao.situacaoAtualiza("Processando",i);
			this.controleVisao.alertaUsario(
					"Processando: \n Metodo: "+metodos.get(i).getNameMetodo()+"\n"+
			"Vetor: "+vetores.get(qualVetor).getNomeVetor()
			);
			//Aqui caipitura a hora atual do SO em formato long
			long tempoInicial = Calendar.getInstance().getTimeInMillis();
			metodos.get(i).getOrdenar(vetorClone);
			long tempoFinal = Calendar.getInstance().getTimeInMillis();
			//aqui faz a diferenca dos valores para ser calculado o perido de processamento
			long diferenca =  tempoFinal - tempoInicial;
			System.out.println("************************");
			System.out.println("Metodo "+metodos.get(i).getNameMetodo());
			System.out.println("Aqui é vetor ja foi ordenado "+vetores.get(qualVetor).getNomeVetor());
			for (int j = 0; j < vetorClone.length; j++) {
				System.out.print(vetorClone[j]+"  ");
			}
			System.out.println("************************");
		
			this.controleVisao.atualizaDetalhesOrdenacao(diferenca,metodos.get(i).getComparacao(),metodos.get(i).getMovimentacao(), i);
			this.controleVisao.situacaoAtualiza("Pronto",i);
			this.controleVisao.alertaUsario("Metodo "+metodos.get(i).getNameMetodo()+" pronto.");
			
			/*String vetorOrdenado ="";
			for (int k : vetorClone) {
				vetorOrdenado += " "+String.valueOf(k);
			}
			
			JOptionPane.showMessageDialog(null, "Vetor Ordenado: \n"
			+ vetorOrdenado);*/
			
		}
		
		

	}

	//esse é chamado quando o modelo faz uma troca para movimentar o array de barra de progreço
	public void contaMovimentos(int movimentos ) {
		controleVisao.contaMovimentos(movimentos,this.indiceDoMetodoUtilizado);
	}

	public int[] getQtdDeVetores() {
		
		return Vetor.qtdVetores;
	}

	public void alertaUsuario(String texto) {
		this.controleVisao.alertaUsario(texto);
	}
}
