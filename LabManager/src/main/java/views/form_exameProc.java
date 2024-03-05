package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.border.TitledBorder;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import DAO_DBlabManager.DAO_cadastraExames;
import DAO_DBlabManager.DAO_paciente;
import DTO.exameAtendimento_DTO;
import DTO.exameProcedimento_DTO;
import testes.dateTimes;
import testes.model03;
import views.util.limitaFormato;
import views.util.model_tableExames;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.ScrollPaneConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class form_exameProc extends JFrame {

	private JPanel contentPane;
	private JTextField txt_codigo;
	private JTextField txt_descricao;
	private JTextField txt_prazo;
	private JTextField txt_procurar;
	String sql = "select codigo as 'CODIGO', descExame as 'DESCRIÇÃO EXAME', tipoMaterial as 'MATERIAL', tempoResultado as 'PRAZO',"
			+ "setorGrupo as 'SETOR', executaProcessa as 'REALIZA', labExec as 'LOCAL', DATE_FORMAT(data_criacao, '%d/%m/%Y' ' %H:%i:%s') as 'CRIAÇÃO',"
			+ "data_atualizacao as 'ATUALIZACAO' from exameProcedimento";

	private form_exameProc set_DTOexames;
	private JComboBox txt_material; 
	private JComboBox txt_setor;
	private JComboBox txt_realizaExame;
	private JComboBox txt_executante;
	private model_tableExames model;
	private JTable tabelaExames;
	private exameProcedimento_DTO dto_exames;
	private JButton btn_deletar;
	int dig;
	private JButton btn_buscar;
	private JScrollPane scrollPane;
	private JButton btn_atualizar;
	// public DefaultTableModel defaul;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form_exameProc frame = new form_exameProc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public form_exameProc() {

		model = new model_tableExames(sql);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 346);
		setSize(1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		setTitle("EXAMES E PROCEDIMENTOS");

		JPanel panel_geral = new JPanel();
		contentPane.add(panel_geral);

		JPanel panel_esq = new JPanel();

		JPanel panel_inferior = new JPanel();

		JPanel panel_2 = new JPanel();

		JPanel panel_direito = new JPanel();
		panel_direito.setBorder(new TitledBorder(null, "Exames", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_geral = new GroupLayout(panel_geral);
		gl_panel_geral.setHorizontalGroup(
			gl_panel_geral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_geral.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_geral.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_inferior, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
						.addGroup(gl_panel_geral.createSequentialGroup()
							.addComponent(panel_esq, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_direito, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(1))
		);
		gl_panel_geral.setVerticalGroup(
			gl_panel_geral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_geral.createSequentialGroup()
					.addGroup(gl_panel_geral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_geral.createSequentialGroup()
							.addGap(1)
							.addComponent(panel_esq, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_geral.createSequentialGroup()
							.addGap(120)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_geral.createSequentialGroup()
							.addGap(1)
							.addComponent(panel_direito, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(86)
					.addComponent(panel_inferior, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);

		JPanel panel_3 = new JPanel();
		// table_exames.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// define a largura da coluna largura da coluna
		// TableColumn col = table_exames.getColumnModel().getColumn(0);
		// col.setPreferredWidth(55);

		JPanel painelTabela = new JPanel();

		painelTabela.setLayout(new GridLayout(0, 1, 0, 0));

		tabelaExames = new JTable(model);
		tabelaExames.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cli = e.getClickCount();

				if (cli == 2) {
					carregarLinha();
					btn_atualizar.setEnabled(true);

				}
			}
		});
		tabelaExames.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

			}
		});
		// jScrollPane1.add(painelTabela);

		scrollPane = new JScrollPane(tabelaExames, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrool2 = new JScrollPane(null);
		//tabelaExames.add(scrool2);
		//
		// tabelaExames.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// scrollPane.setViewportView(tabelaExames);
		// scrollPane.getHorizontalScrollBar();
		tabelaExames.getTableHeader().setReorderingAllowed(false);
		if (tabelaExames.getColumnModel().getColumnCount() > 0) {
			tabelaExames.getColumnModel().getColumn(0).setPreferredWidth(50);
			tabelaExames.getColumnModel().getColumn(1).setPreferredWidth(200);
			// tabelaExames.getColumnModel().getColumn(1).setMaxWidth(250);
			tabelaExames.getColumnModel().getColumn(2).setPreferredWidth(100);
			tabelaExames.getColumnModel().getColumn(7).setPreferredWidth(150);
			tabelaExames.getColumnModel().getColumn(8).setPreferredWidth(100);
		}
		// realiza a reodernação do coteudo
		tabelaExames.setRowSorter(new TableRowSorter(tabelaExames.getModel()));

		scrollPane.setMaximumSize(getMaximumSize());

		painelTabela.add(scrollPane, BorderLayout.CENTER);
		GroupLayout gl_panel_direito = new GroupLayout(panel_direito);
		gl_panel_direito.setHorizontalGroup(gl_panel_direito.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_direito.createSequentialGroup().addGap(1)
						.addGroup(gl_panel_direito.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
								.addComponent(painelTabela, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
						.addGap(1)));
		gl_panel_direito.setVerticalGroup(gl_panel_direito.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_direito.createSequentialGroup().addGap(1)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE).addGap(1)
						.addComponent(painelTabela, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(1)));

		panel_3.setLayout(null);

		JLabel lblProcuarExame = new JLabel("Procuar Exame:");
		lblProcuarExame.setBounds(5, 12, 210, 15);
		panel_3.add(lblProcuarExame);

		txt_procurar = new JTextField();
		txt_procurar.setColumns(10);
		txt_procurar.setBounds(5, 30, 210, 30);
		panel_3.add(txt_procurar);

		btn_buscar = new JButton("Buscar");
		btn_buscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscaExames();

			}
		});
		btn_buscar.setBounds(230, 30, 90, 30);
		panel_3.add(btn_buscar);
		panel_direito.setLayout(gl_panel_direito);
		panel_2.setLayout(null);

		btn_deletar = new JButton("Deletar");
		btn_deletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				removeLinha();
			}

		});
		btn_deletar.setBounds(1, 138, 100, 30);

		panel_2.add(btn_deletar);

		btn_atualizar = new JButton("Atualizar");
		btn_atualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verificaAntesAtualizar();
			}

		});
		btn_atualizar.setBounds(1, 93, 100, 30);
		panel_2.add(btn_atualizar);

		JButton btn_gravar = new JButton("Gravar");
		btn_gravar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// txt_coluna.setText(new model_03(query).getColumnName(1));
				verificaCampos();
				limparCampos();

			}
		});
		btn_gravar.setBounds(1, 48, 100, 30);
		panel_2.add(btn_gravar);

		JButton btn_limpar = new JButton("Limpar");
		btn_limpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparCampos();
			}
		});
		btn_limpar.setBounds(1, 180, 100, 30);
		panel_2.add(btn_limpar);
		panel_esq.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Criar Exames/Procedimento", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.setBounds(2, 2, 300, 479);
		panel_esq.add(panel_1);

		JLabel lblNewLabel = new JLabel("Minemônico exame:");
		lblNewLabel.setBounds(12, 36, 175, 15);
		panel_1.add(lblNewLabel);

		txt_codigo = new JTextField();
		txt_codigo.setDocument(new limitaFormato(5, limitaFormato.TipoEntrada.CODIGO, true));
		txt_codigo.setColumns(10);
		txt_codigo.setBounds(155, 30, 114, 30);
		panel_1.add(txt_codigo);

		JLabel lblDescrioDoExame = new JLabel("Descrição do Exame/Proced.");
		lblDescrioDoExame.setBounds(12, 80, 210, 15);
		panel_1.add(lblDescrioDoExame);

		txt_descricao = new JTextField();
		txt_descricao.setDocument(new limitaFormato(45, limitaFormato.TipoEntrada.NOMENUMEROS, false));
		txt_descricao.setColumns(10);
		txt_descricao.setBounds(12, 98, 276, 30);
		panel_1.add(txt_descricao);

		JLabel lblTipoDeMaterial = new JLabel("Tipo de Material:");
		lblTipoDeMaterial.setBounds(12, 149, 175, 15);
		panel_1.add(lblTipoDeMaterial);

		txt_material = new JComboBox();
		txt_material.setModel(new DefaultComboBoxModel(
				new String[] { "Sangue total", "Soro", "Plasma com cintrato", "Urina rotina", "Urina urocultura" }));
		txt_material.setFont(new Font("Dialog", Font.BOLD, 11));
		txt_material.setBounds(12, 166, 175, 30);
		panel_1.add(txt_material);

		JLabel lblPrazoDeEntregadias = new JLabel("Realiza procedimento:");
		lblPrazoDeEntregadias.setBounds(12, 335, 175, 15);
		panel_1.add(lblPrazoDeEntregadias);

		txt_prazo = new JTextField();
		txt_prazo.setColumns(10);
		txt_prazo.setBounds(195, 282, 70, 30);
		panel_1.add(txt_prazo);

		JLabel lblSetorGrupo = new JLabel("Setor / grupo");
		lblSetorGrupo.setBounds(12, 218, 175, 15);
		panel_1.add(lblSetorGrupo);

		txt_setor = new JComboBox();
		txt_setor.setModel(
				new DefaultComboBoxModel(new String[] { "Bioquímica", "Hematologia", "Urinálise", "Microbiologia" }));
		txt_setor.setFont(new Font("Dialog", Font.BOLD, 11));
		txt_setor.setBounds(12, 235, 175, 30);
		panel_1.add(txt_setor);

		JLabel lblTipoDeMaterial_1 = new JLabel("Prazo p/ Resultado(dias):");
		lblTipoDeMaterial_1.setBounds(12, 290, 185, 15);
		panel_1.add(lblTipoDeMaterial_1);

		txt_realizaExame = new JComboBox();
		txt_realizaExame.setModel(new DefaultComboBoxModel(new String[] { "Sim", "Não" }));
		txt_realizaExame.setFont(new Font("Dialog", Font.BOLD, 11));
		txt_realizaExame.setBounds(12, 350, 175, 30);
		panel_1.add(txt_realizaExame);

		txt_executante = new JComboBox();
		txt_executante.setModel(new DefaultComboBoxModel(new String[] { "Matriz", "Lab. Apoio", "Outros" }));
		txt_executante.setFont(new Font("Dialog", Font.BOLD, 11));
		txt_executante.setBounds(12, 419, 175, 30);
		panel_1.add(txt_executante);

		JLabel lblPrazoDeEntregadias_1 = new JLabel("Executante:");
		lblPrazoDeEntregadias_1.setBounds(12, 400, 175, 15);
		panel_1.add(lblPrazoDeEntregadias_1);
		panel_geral.setLayout(gl_panel_geral);
		desligaComponete();
	}
	///

	public void gravarExame() {

		dto_exames = new exameProcedimento_DTO();
		dto_exames.setCodigo(txt_codigo.getText());
		dto_exames.setDescExame(txt_descricao.getText());
		dto_exames.setTipoMaterial(txt_material.getSelectedItem().toString());
		dto_exames.setPrazo(Integer.parseInt(txt_prazo.getText()));
		dto_exames.setSetorGrupo(txt_setor.getSelectedItem().toString());
		dto_exames.setExecutaProcessa(txt_realizaExame.getSelectedItem().toString());
		dto_exames.setLabExec(txt_executante.getSelectedItem().toString());
		// dto_exames.setDateAtualiza("-");

		DAO_cadastraExames cadastraExame = new DAO_cadastraExames();
		try {
			cadastraExame.inserir_exame(dto_exames);
			// Atualiza o model da tabela com os novos dados adicionados
			model.loadData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void limparCampos() {

		txt_codigo.setText("");
		txt_descricao.setText("");
		txt_prazo.setText("");
		txt_material.setSelectedIndex(0);
		txt_codigo.requestFocus();
		tabelaExames.clearSelection();
		desligaComponete();

	}

	public void verificar() {

		DAO_cadastraExames verifica = new DAO_cadastraExames();
		dto_exames = new exameProcedimento_DTO();
		dto_exames.setCodigo(txt_codigo.getText());
		verifica.verificar_codigoExame(dto_exames);
	}

	public void verificaCampos() {

		DAO_cadastraExames verifica = new DAO_cadastraExames();
		dto_exames = new exameProcedimento_DTO();
		dto_exames.setCodigo(txt_codigo.getText());
		dig = txt_descricao.getText().length();

		if (verifica.verificar_codigoExame(dto_exames) || txt_codigo.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "O Codigo do exame nao e valido");
		} else if (dig <= 8) {
			JOptionPane.showMessageDialog(null, "A decrição do exame nao e valido");
		} else if (Integer.parseInt(txt_prazo.getText()) > 70) {
			JOptionPane.showMessageDialog(null, "O prazo não é válido");
		} else {
			gravarExame();
		}
	}

	public void buscaExames() {
		if (txt_procurar.getText().length() > 0) {

			dto_exames = new exameProcedimento_DTO();
			dto_exames.setProcurar(txt_procurar.getText());
			// table = null;
			model.buscaExames(dto_exames);

		} else {
			model.loadData();
		}

	}

	public void removeLinha() {

		if (tabelaExames.getModel().getRowCount() < 0 || tabelaExames.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "Selecione um exame da tabela!");
		} else if (tabelaExames.getSelectedRow() >= 0) {
			int resposta = JOptionPane.showConfirmDialog(null, "O exame selecionado será excluido.\n Voce confirma?",
					"EXCLUIR EXAME!", JOptionPane.YES_NO_OPTION);

			if (resposta == 0) {
				int linhaSelecionada = tabelaExames.getSelectedRow();
				String codigoApagar = (String) tabelaExames.getModel().getValueAt(linhaSelecionada, 0);
				DAO_cadastraExames apaga = new DAO_cadastraExames();
				apaga.apagarExame(codigoApagar);
				model.loadData();
				System.out.println(codigoApagar);
			}

		} 

	}

	public void carregarLinha() {

		// limparCampos();

		int lselect = tabelaExames.getSelectedRow();
		int cselect = 0;
		// obtem a linha selecionada
		String codAtualiza = (String) tabelaExames.getValueAt(lselect, cselect);

		// carregar campos
		txt_codigo.setText((String) tabelaExames.getModel().getValueAt(lselect, cselect));
		txt_descricao.setText(tabelaExames.getModel().getValueAt(lselect, 1).toString());
		txt_material.setSelectedItem((String) tabelaExames.getModel().getValueAt(lselect, 2));

		Integer xx = (Integer) tabelaExames.getModel().getValueAt(lselect, 3);
		String zz = xx.toString();
		txt_prazo.setText(zz);

		txt_setor.setSelectedItem((String) tabelaExames.getModel().getValueAt(lselect, 4));
		txt_realizaExame.setSelectedItem((String) tabelaExames.getModel().getValueAt(lselect, 5));
		txt_executante.setSelectedItem((String) tabelaExames.getModel().getValueAt(lselect, 6));

	}

	public String verificarDataAtual() {
		LocalDateTime dataAtual = LocalDateTime.now();
		views.util.dateTimes dataTexto = new views.util.dateTimes();
		String data = dataTexto.convertDateAtualText();
		System.out.println(data);
		return data;

	}

	public void atualizar() {
		int resposta = JOptionPane.showConfirmDialog(null, "Gotaria de salvar as modificações no exame?",
				"ATUALIZAR EXAME", JOptionPane.YES_NO_OPTION);
		if (resposta == 0) {
			LocalDateTime dataAtual = LocalDateTime.now();
			views.util.dateTimes dataTexto = new views.util.dateTimes();
			String data = dataTexto.convertDateAtualText();

			int lselect = tabelaExames.getSelectedRow();
			int cselect = 0;
			// obtem a linha selecionada
			String codAtualiza = (String) tabelaExames.getValueAt(lselect, cselect);
			dto_exames = new exameProcedimento_DTO();
			dto_exames.setCodAtualiza(codAtualiza);
			dto_exames = new exameProcedimento_DTO();

			dto_exames.setCodigo(txt_codigo.getText());
			dto_exames.setDescExame(txt_descricao.getText());
			dto_exames.setTipoMaterial(txt_material.getSelectedItem().toString());
			dto_exames.setPrazo(Integer.parseInt(txt_prazo.getText()));
			dto_exames.setSetorGrupo(txt_setor.getSelectedItem().toString());
			dto_exames.setExecutaProcessa(txt_realizaExame.getSelectedItem().toString());
			dto_exames.setLabExec(txt_executante.getSelectedItem().toString());
			dto_exames.setDateAtualiza(data);
			dto_exames.setCodAtualiza(codAtualiza);

			DAO_cadastraExames grava = new DAO_cadastraExames();

			grava.atualizarExame(dto_exames);
			model.loadData();
			limparCampos();
		}

	}

	public void verificaAntesAtualizar() {

		if ((btn_atualizar.isEnabled())) {
			if (txt_codigo.getText().length() <= 0) {
				JOptionPane.showMessageDialog(null, "Codigo em branco, Invalido!");
			} else {
				int lselect = tabelaExames.getSelectedRow();
				int cselect = 0;
				// obtem a linha selecionada
				String codAtualiza = tabelaExames.getModel().getValueAt(lselect, cselect).toString();
				DAO_cadastraExames verifica = new DAO_cadastraExames();
				dto_exames = new exameProcedimento_DTO();
				dto_exames.setCodigo(txt_codigo.getText());
				dto_exames.setProcurar(codAtualiza);
				if (!(verifica.verificaAtualiza(dto_exames))) {
					JOptionPane.showMessageDialog(null, "O codigo: " + txt_codigo.getText() + " já existe!");
				} else {
					atualizar();
				}
			}
		}else {
			
		}

	}

	//
	public void desligaComponete() {
		btn_atualizar.setEnabled(false);
	}
}
