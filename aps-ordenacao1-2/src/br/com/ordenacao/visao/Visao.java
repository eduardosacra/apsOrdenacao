package br.com.ordenacao.visao;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import br.com.ordenacao.controler.ControlerVisao;
import br.com.ordenacao.controler.ItensDaLista;

public class Visao extends JFrame {
	public JPanel pnPrincipal, pnBotoes, pnTitulos, pnElementos;
	JLabel lbBubleSort, lbBubleSortMelhorado, lbQuickSort, lbMetodos, lbStatus,
			lbTempoNecessario, lbTempo, lbComparacao, lbMovimentacao;
	JProgressBar pbProgreco, pbProgreco2, pbProgreco3;
	JButton btOrdenar, btTeste;
	JComboBox cbQuantidade, cbTipoDeVetor;
	JScrollPane js;

	public String[] nomeMetodos, tempoGasto;
	public JProgressBar[] barraDeprogreco;
	String[] nomeVetores;
	ArrayList<ItensDaLista> itensLista;
	int[] qtdVetores;

	ControlerVisao controler;// controlador da Visao

	public Visao(ControlerVisao controle, ArrayList<ItensDaLista> itensLista,
			String[] nomeVetores, int[] qtdVetores) {
		this.controler = controle;// controler
		this.itensLista = itensLista;// itens que agregara o conteudo ordenado
		this.nomeVetores = nomeVetores;// nome dos vetores que serao utilizados
		this.qtdVetores = qtdVetores;// tamanhos utilizados.

		inicializaComponentes();
		definirEvento();

	}

	private void inicializaComponentes() {

		// JPanel
		this.pnPrincipal = new JPanel(new GridLayout(2, 3));

		this.pnElementos = new JPanel(new GridLayout(this.itensLista.size(), 5,
				5, 5));
		this.pnTitulos = new JPanel(new GridLayout(1, 5, 5, 5));

		for (ItensDaLista item : this.itensLista) {
			this.pnElementos.add(item.lbNoMetodo);
			this.pnElementos.add(item.lbSituacao);
			this.pnElementos.add(item.lbTempoGasto);
			this.pnElementos.add(item.lbComparacao);
			this.pnElementos.add(item.lbMovimentacao);

		}

		// JLabel
		lbMetodos = new JLabel("Metodos de ordenação");
		lbMetodos.setFont(new Font("nome_fonte", Font.BOLD, 14));
		lbStatus = new JLabel("Status");
		lbStatus.setFont(new Font("nome_fonte", Font.BOLD, 14));
		lbTempoNecessario = new JLabel("Tempo necessário");
		lbTempoNecessario.setFont(new Font("nome_fonte", Font.BOLD, 14));
		lbComparacao = new JLabel("Comparações");
		lbComparacao.setFont(new Font("nome_fonte", Font.BOLD, 14));
		lbMovimentacao = new JLabel("Movimentações");
		lbMovimentacao.setFont(new Font("nome_fonte", Font.BOLD, 14));

		// JComboBox
		String[] qtd = new String[this.qtdVetores.length + 1];// Coloca-se mais
																// um para
																// definir como
																// titulo
		qtd[0] = "Quantidade de elementos";
		for (int i = 0; i < this.qtdVetores.length; i++) {
			qtd[i + 1] = String.valueOf(this.qtdVetores[i]);
		}
		cbQuantidade = new JComboBox(qtd);
		cbQuantidade.setToolTipText("Escolha a quantidade de elementos");

		cbTipoDeVetor = new JComboBox(this.nomeVetores);
		cbTipoDeVetor
				.setToolTipText("Escolha aqui qual tipo de vetor sera ordenado");

		// Botões
		btOrdenar = new JButton("Ordenar");

		// Adicionando no painel
		pnTitulos.add(lbMetodos);
		pnTitulos.add(lbStatus);
		pnTitulos.add(lbTempoNecessario);
		pnTitulos.add(lbComparacao);
		pnTitulos.add(lbMovimentacao);

		// JScrollPane
		js = new JScrollPane();
		js.setViewportView(pnElementos);
		js.setSize(859, 400);

		// adicionando nos paineis
		pnPrincipal.add(pnTitulos);
		pnPrincipal.add(js);

		// Painel que conterá os botões
		pnBotoes = new JPanel(new GridLayout(0, 3, 5, 5));
		pnBotoes.add(btOrdenar);
		pnBotoes.add(cbQuantidade);
		pnBotoes.add(cbTipoDeVetor);

		//JFrame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Medir Performance");
		this.setLayout(new BorderLayout());
		this.getContentPane().add(pnPrincipal, BorderLayout.CENTER);
		this.getContentPane().add(pnBotoes, BorderLayout.SOUTH);
		this.setSize(900, 300);
		
	}

	private void definirEvento() {
		// Evento relacionado quando cliacado no botoao para realizar a
		// ordenação
		this.btOrdenar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int valor = 0;
				if (cbQuantidade.getSelectedIndex() == 0
						|| cbQuantidade.getSelectedIndex() == 1) {
					cbQuantidade.setSelectedIndex(1);// para evitar erros.

					valor = 10;
				} else if (cbQuantidade.getSelectedIndex() == 2) {
					valor = 100;
				} else if (cbQuantidade.getSelectedIndex() == 3) {
					valor = 1000;
				} else if (cbQuantidade.getSelectedIndex() == 4) {
					valor = 10000;
				} else if (cbQuantidade.getSelectedIndex() == 5) {
					valor = 100000;
				}

				// Aqui inicia o processo detroca de mensagem pelos objetos
				controler.ordenacao(valor, cbTipoDeVetor.getSelectedIndex());

			}
		});

	}

	// Aqui recebe um indice para localizar qual metodo esta sendo atualizado
	// texto usado para noficiar o usuario sobre o status da ordenacao
	public void situacaoAtualiza(String texto, int indice) {
		this.itensLista.get(indice).lbSituacao.setText(texto);

	}

	// apenas para notificar o usuario que comecara outra ordenacao
	public void zeraDetalhesOrdenacao() {
		for (ItensDaLista item : this.itensLista) {
			item.lbSituacao.setText("Pronto para uso");
			item.lbTempoGasto.setText("00:00.000");
			item.lbComparacao.setText("0");
			item.lbMovimentacao.setText("0");
		}
	}

	// Aqui para notificar ao usuario algum detatalhe sobre o processo de
	// ordenacao
	public void alertaUsuario(String texto) {

		JOptionPane.showMessageDialog(this, texto);
	}

	// aqui e atualizado o tempo gasto em cada ordenacao
	public void setTime(Calendar tempo) {
		String time = "";// new
							// SimpleDateFormat("hh:mm:ss:SSS").format(contato.getDataNascimento().getTimeInMillis())
		String tempoformatado = new SimpleDateFormat("HH:mm:ss:SSS")
				.format(tempo.getTimeInMillis());

	}

}
