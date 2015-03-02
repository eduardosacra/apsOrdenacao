package br.com.ordenacao.controler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import br.com.ordenacao.modelo.metodos.BubbleSort;
import br.com.ordenacao.modelo.metodos.Ordenacao;
import br.com.ordenacao.modelo.vetor.Aleatorio;
import br.com.ordenacao.modelo.vetor.OrdenadoCrescente;
import br.com.ordenacao.modelo.vetor.OrdenadoDecrescente;
import br.com.ordenacao.modelo.vetor.QuaseOrdenado;
import br.com.ordenacao.modelo.vetor.Vetor;
import br.com.ordenacao.visao.Visao;

public class ControlerVisao {
	int tamanho;
	public Vetor[] vetores;
	public Ordenacao[] metodos;

	public ArrayList itensLista;
	public long tempos[];
	public Visao visao;
	public ControleModelo controleModelo;
	public int indiceDosVetores;// armazena qual o indice que estou usando dos
								// vetores
	String[] nomeVetores;
	int[] qtdTamanhovetores;
	String[] nomeMetodos;

	public ControlerVisao() {

		// eles se conversao
		this.controleModelo = new ControleModelo(this);

		nomeMetodos = this.controleModelo.getMetodos();
		qtdTamanhovetores = this.controleModelo.getQtdDeVetores();
		nomeVetores = this.controleModelo.getNomeVetores();

		// this.itensDaLista = new
		// ItensDaLista[controleModelo.getNomeMetodos().length];
		this.itensLista = new ArrayList();

		for (String ordenacao : nomeMetodos) {
			ItensDaLista item = new ItensDaLista();

			item.lbNoMetodo = new JLabel(ordenacao);

			item.lbSituacao = new JLabel("Pronto para uso");

			item.lbTempoGasto = new JLabel("mm:ss:SSS");
			item.lbComparacao = new JLabel("0");
			item.lbMovimentacao = new JLabel("0");

			this.itensLista.add(item);

		}

	}

	// teste
	public void alertaUsario(String texto) {
		this.visao.alertaUsuario(texto);
	}

	// chamado para fazer a ordenação
	public void ordenacao(int quantidade, int tipoDeVetor) {

		this.visao.zeraDetalhesOrdenacao();
		this.visao.alertaUsuario("Carregando... \n Preparando Vetor com "
				+ quantidade + " elementos");
		controleModelo.getOrdenacao(quantidade, tipoDeVetor);//co,unicacao entre objetos.
		this.visao.alertaUsuario("Pronto!");

	}

	// Aqui é feito acrecimó nas barras de rolagens
	public void contaMovimentos(int valor, int indice) {
		this.visao.atualizaBarraDeProgreco(valor, indice);

		// JProgressBar bar = ((ItensDaLista)
		// itensLista.get(indice)).bpProgreco;
		// bar.setValue(bar.getValue() + valor);
	}

	// aqui atualiza os tempos
	public void atualizaDetalhesOrdenacao(long tempoGasto,long comparacao,long movimentacao, int indice) {
		SimpleDateFormat formato = new SimpleDateFormat("mm:ss.SSS");

		Date tempo = new Date(tempoGasto);
		System.out.printf("%.3f ms%n", (tempoGasto) / 1000d);
		((ItensDaLista) itensLista.get(indice)).lbTempoGasto.setText((formato
				.format(tempo)));
		((ItensDaLista) itensLista.get(indice)).lbComparacao.setText(String.valueOf(comparacao));
		((ItensDaLista) itensLista.get(indice)).lbMovimentacao.setText(String.valueOf(movimentacao));
		

	}

	public void getVisao() {
		this.visao = new Visao(this, this.itensLista, this.nomeVetores,
				this.qtdTamanhovetores);
		this.visao.setVisible(true);
	}

	public void situacaoAtualiza(String texto, int indice) {
		this.visao.situacaoAtualiza(texto, indice);

	}

	

}
