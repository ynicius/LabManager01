package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import DAO_DBlabManager.DAO_tabelaCobraca;
import DAO_DBlabManager.DAO_vinculaValor;
import DTO.exameProcedimento_DTO;
import DTO.tabelaCobranca_DTO;
import DTO.vinculaValores_DTO;
import views.util.model_formVincula;
import views.util.model_valor2;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class form_vinculaValor extends JFrame {

	private JPanel contentPane;
	private JTextField txt_exame;
	private JTextField txt_exameValor;
	private JTextField txt_proced;
	private JTextField txt_valor;
	private JTable tableExame, table_Valor, tableVincula;
	private model_formVincula model1, model2, model3;
	private model_valor2 model4;
	private String query1 = "select id_exameProce, codigo, descExame from exameProcedimento order by descExame;";
	private String query2;
	private String query3;
	private JComboBox comboBox1;
	tabelaCobranca_DTO tabela;
	DefaultComboBoxModel defaultComboBox;
	private DAO_tabelaCobraca list2;
	private String exameCod = null;
	private JLabel txt_exaCod;
	private JLabel txt_exaNome;
	private JLabel txt_idValor;
	private JLabel txt_idExa;
	tabelaCobranca_DTO nomeTable;
	private int idCobranca;
	private exameProcedimento_DTO dto_exames;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form_vinculaValor frame = new form_vinculaValor();
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
	public form_vinculaValor() {
		String x = "";
		int y = 0;
		model1 = new model_formVincula(query1);
		model2 = new model_formVincula();
		model3 = new model_formVincula(y, x);
		model4 = new model_valor2(exameCod);
		list2 = new DAO_tabelaCobraca();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("VINCULAR COBRANÇA DE EXAMES");

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();

		JPanel panel_4 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(1)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE).addGap(5)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(5)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(1)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(1))
				.addGroup(gl_panel.createSequentialGroup().addGap(5)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(5)));
		panel_4.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Vincular valor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 500, 350, 140);
		panel_4.add(panel_5);
		panel_5.setLayout(null);

		txt_proced = new JTextField();
		txt_proced.setForeground(new Color(26, 95, 180));
		txt_proced.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_proced.setEditable(false);
		txt_proced.setBounds(15, 43, 100, 35);
		panel_5.add(txt_proced);
		txt_proced.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Procedimento");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(15, 30, 90, 15);
		panel_5.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("+");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3.setBounds(120, 53, 40, 15);
		panel_5.add(lblNewLabel_3);

		txt_valor = new JTextField();
		txt_valor.setForeground(new Color(26, 95, 180));
		txt_valor.setFont(new Font("Dialog", Font.PLAIN, 15));
		txt_valor.setEditable(false);
		txt_valor.setColumns(10);
		txt_valor.setBounds(140, 43, 190, 35);
		panel_5.add(txt_valor);

		JLabel lblNewLabel_2_1 = new JLabel("Exame - Valor");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(146, 30, 90, 15);
		panel_5.add(lblNewLabel_2_1);

		JButton btnNewButton_2_1 = new JButton("Vincular");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vincular();

			}
		});
		btnNewButton_2_1.setFont(new Font("Dialog", Font.BOLD, 11));
		btnNewButton_2_1.setBounds(15, 95, 90, 30);
		panel_5.add(btnNewButton_2_1);

		JButton btnNewButton_2_2 = new JButton("Remover");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desvicular();
			}
		});
		btnNewButton_2_2.setFont(new Font("Dialog", Font.BOLD, 11));
		btnNewButton_2_2.setBounds(120, 95, 90, 30);
		panel_5.add(btnNewButton_2_2);

		tableVincula = new JTable(model4);
		tableVincula.getTableHeader().setReorderingAllowed(false);
		if (tableVincula.getColumnModel().getColumnCount() > 0) {
			tableVincula.getColumnModel().getColumn(0).setPreferredWidth(50);
			tableVincula.getColumnModel().getColumn(1).setPreferredWidth(110);
			tableVincula.getColumnModel().getColumn(2).setPreferredWidth(60);
			tableVincula.getColumnModel().getColumn(3).setPreferredWidth(130);
			tableVincula.getColumnModel().getColumn(5).setPreferredWidth(40);

		}

		JScrollPane scrollPane_2 = new JScrollPane(tableVincula);
		scrollPane_2.setBounds(5, 130, 450, 330);
		panel_4.add(scrollPane_2);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 12, 335, 75);
		panel_4.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Exame:");
		lblNewLabel_4.setBounds(12, 40, 70, 15);
		panel_6.add(lblNewLabel_4);

		txt_exaCod = new JLabel("");
		txt_exaCod.setForeground(new Color(38, 162, 105));
		txt_exaCod.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_exaCod.setBounds(70, 40, 70, 15);
		panel_6.add(txt_exaCod);

		txt_exaNome = new JLabel("");
		txt_exaNome.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_exaNome.setForeground(new Color(38, 162, 105));
		txt_exaNome.setBounds(116, 40, 170, 15);
		panel_6.add(txt_exaNome);

		txt_idExa = new JLabel("");
		txt_idExa.setEnabled(false);
		txt_idExa.setBounds(51, 470, 70, 15);
		panel_4.add(txt_idExa);

		txt_idValor = new JLabel("");
		txt_idValor.setEnabled(false);
		txt_idValor.setBounds(149, 470, 70, 15);
		panel_4.add(txt_idValor);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Porcedimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(5, 5, 350, 280);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Procurar exame");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setBounds(12, 22, 110, 15);
		panel_2.add(lblNewLabel);

		txt_exame = new JTextField();
		txt_exame.setBounds(10, 37, 233, 30);
		panel_2.add(txt_exame);
		txt_exame.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscaExames();
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 11));
		btnNewButton.setBounds(255, 37, 90, 30);
		panel_2.add(btnNewButton);

		tableExame = new JTable(model1);
		tableExame.getTableHeader().setReorderingAllowed(false);
		if (tableExame.getColumnModel().getColumnCount() > 0) {
			tableExame.getColumnModel().getColumn(0).setPreferredWidth(40);
			tableExame.getColumnModel().getColumn(1).setPreferredWidth(60);
			tableExame.getColumnModel().getColumn(2).setPreferredWidth(200);

		}

		tableExame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int linha = tableExame.getSelectedRow();
				String id = tableExame.getModel().getValueAt(linha, 1).toString();
				txt_exaCod.setText(id + " -");
				txt_exaNome.setText(tableExame.getModel().getValueAt(linha, 2).toString());
				panel_6.setBackground(new Color(255, 255, 205));
			}
		});
		tableExame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = tableExame.getSelectedRow();
				String id = tableExame.getModel().getValueAt(linha, 1).toString();
				txt_exaCod.setText(id + " -");
				txt_exaNome.setText(tableExame.getModel().getValueAt(linha, 2).toString());
				panel_6.setBackground(new Color(255, 255, 205));
			}
		});
		JScrollPane scrollPane = new JScrollPane(tableExame);
		scrollPane.setBounds(5, 70, 340, 175);
		panel_2.add(scrollPane);

		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelExamevincula();
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 13));
		btnNewButton_1.setBounds(280, 245, 45, 30);
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("-");
		btnNewButton_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		btnNewButton_1_1.setBounds(220, 245, 45, 30);
		panel_2.add(btnNewButton_1_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(
				new TitledBorder(null, "Tabela de cobran\u00E7a", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(5, 300, 350, 350);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblTabela = new JLabel("Tabela");
		lblTabela.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTabela.setBounds(12, 20, 110, 15);
		panel_3.add(lblTabela);

		comboBox1 = new JComboBox();
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				carregaTabela();
			}
		});
		defaultComboBox = new DefaultComboBoxModel();
		defaultComboBox.addElement("");
		for (String L : list2.listConvenio()) {
			defaultComboBox.addElement(L);
		}

		comboBox1.setModel(defaultComboBox);

		comboBox1.setBounds(8, 35, 225, 30);
		panel_3.add(comboBox1);

		JLabel lblNewLabel_1 = new JLabel("Procurar exame");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(12, 77, 110, 15);
		panel_3.add(lblNewLabel_1);

		txt_exameValor = new JTextField();
		txt_exameValor.setColumns(10);
		txt_exameValor.setBounds(8, 92, 233, 30);
		panel_3.add(txt_exameValor);

		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarValor();
			}
		});
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 11));
		btnNewButton_2.setBounds(250, 92, 90, 30);
		panel_3.add(btnNewButton_2);

		table_Valor = new JTable(model2);
		table_Valor.getTableHeader().setReorderingAllowed(false);
		if (table_Valor.getColumnModel().getColumnCount() > 0) {
			table_Valor.getColumnModel().getColumn(0).setPreferredWidth(40);
			table_Valor.getColumnModel().getColumn(1).setPreferredWidth(60);
			table_Valor.getColumnModel().getColumn(2).setPreferredWidth(200);

		}

		JScrollPane scrollPane_1 = new JScrollPane(table_Valor);
		scrollPane_1.setBounds(5, 130, 340, 175);
		panel_3.add(scrollPane_1);

		JButton btnNewButton_1_1_1 = new JButton("-");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton_1_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		btnNewButton_1_1_1.setBounds(235, 310, 45, 30);
		panel_3.add(btnNewButton_1_1_1);

		JButton btn_addValor = new JButton("+");
		btn_addValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modelValorVincula();

			}

		});
		btn_addValor.setFont(new Font("Dialog", Font.BOLD, 13));
		btn_addValor.setBounds(295, 310, 45, 30);
		panel_3.add(btn_addValor);
		panel.setLayout(gl_panel);
	}

	public void carregaTabela() {
		tabela = new tabelaCobranca_DTO();
		tabela.setNomeTabela(comboBox1.getSelectedItem().toString());
		model2.buscaTabela(tabela);

	}

	public void buscaExamesValor() {
		if (txt_exameValor.getText().length() > 0 & table_Valor.getSelectedRowCount() > 0) {

			// parei aqui
			/*
			 * tabela = new tabelaCobranca_DTO();
			 * tabela.setProcurar(txt_procurar.getText()); // table = null;
			 * model.buscaExames(dto_exames);
			 */

		} else {
			// model.loadData();
		}

	}

	public void modelExamevincula() {
		if (tableExame.getSelectedRow() >= 0) {
			int linha = tableExame.getSelectedRow();
			String id = tableExame.getModel().getValueAt(linha, 1).toString();
			txt_idExa.setText(tableExame.getModel().getValueAt(linha, 0).toString());
			txt_proced.setText(id);
			model4.loadData(id);

		}

	}

	public void modelValorVincula() {
		if (table_Valor.getModel().getRowCount() >= 0) {
			int linha = table_Valor.getSelectedRow();
			String nome = table_Valor.getModel().getValueAt(linha, 2).toString();
			String id = table_Valor.getModel().getValueAt(linha, 0).toString();
			txt_valor.setText(nome);
			txt_idValor.setText(id);
		}

	}

	public void vincular() {

		if (txt_idExa.getText().length() > 0 & txt_idValor.getText().length() > 0) {

			if (validValorUnicoTabela()) {

				atualiza();
			} else {
				int idExa = Integer.parseInt(txt_idExa.getText());
				int idValor = Integer.parseInt(txt_idValor.getText());
				vinculaValores_DTO dtoVincula = new vinculaValores_DTO();
				dtoVincula.setIdExame(idExa);
				dtoVincula.setIdValor(idValor);
				DAO_vinculaValor vinc = new DAO_vinculaValor();
				vinc.vincula_valor(dtoVincula);
				modelExamevincula();
				limpar();

			}

		}

	}

	public void desvicular() {
		if (tableVincula.getSelectedRow() >= 0) {
			int linha = tableVincula.getSelectedRow();
			int id = Integer.parseInt(tableVincula.getModel().getValueAt(linha, 5).toString());
			vinculaValores_DTO idDesvincula = new vinculaValores_DTO();
			idDesvincula.setId(id);
			DAO_vinculaValor desvincula = new DAO_vinculaValor();
			desvincula.deleteVinculo(idDesvincula);
			modelExamevincula();
			System.out.println(id);
		}
	}

	public void limpar() {
		txt_idValor.setText("");
		txt_valor.setText("");

	}

	public boolean consultarVinculo() {
		int idValor = Integer.parseInt(txt_idValor.getText());
		int idExa = Integer.parseInt(txt_idExa.getText());
		vinculaValores_DTO dtoVincula = new vinculaValores_DTO();
		dtoVincula.setIdValor(idValor);
		dtoVincula.setIdExame(idExa);
		DAO_vinculaValor consultVinculo = new DAO_vinculaValor();

		if (consultVinculo.buscaIdvalor(dtoVincula)) {
			return true;
		}

		return false;

	}

	// pausa 31/10/2023
	public boolean validValorUnicoTabela() {
		int idExa = Integer.parseInt(txt_idExa.getText());
		String tabela = comboBox1.getSelectedItem().toString();
		vinculaValores_DTO dtoVincula = new vinculaValores_DTO();
		tabelaCobranca_DTO dtoTabela = new tabelaCobranca_DTO();
		dtoVincula.setIdExame(idExa);
		dtoTabela.setNomeTabela(tabela);
		DAO_vinculaValor table = new DAO_vinculaValor();
		if (table.buscaExaTable(dtoVincula, dtoTabela)) {
			idCobranca = table.id;
			return true;

		}
		return false;

	}

	public void atualiza() {
		int idValor = Integer.parseInt(txt_idValor.getText());
		vinculaValores_DTO dtoVincula = new vinculaValores_DTO();
		dtoVincula.setId(idCobranca);
		dtoVincula.setIdValor(idValor);
		DAO_vinculaValor table = new DAO_vinculaValor();
		table.updateCobranca(dtoVincula);
		modelExamevincula();
		limpar();
	}

	public void buscaExames() {
		if (txt_exame.getText().length() > 0) {

			dto_exames = new exameProcedimento_DTO();
			dto_exames.setProcurar(txt_exame.getText());
			// table = null;
			model1.buscaExames(dto_exames);

		} else {
			model1.loadData1();
		}

	}
	
	public void buscarValor() {
		if(comboBox1.getSelectedIndex()>0 & txt_exameValor.getText().length() > 0) {
			tabela = new tabelaCobranca_DTO();
			tabela.setNomeTabela(comboBox1.getSelectedItem().toString());
			tabela.setCodigo(txt_exameValor.getText());
			tabela.setNomeDesc(txt_exameValor.getText());
			model2.buscarValor(tabela);
			//System.out.println(comboBox1.getSelectedItem().toString());
		}else {
			model2.buscaTabela(tabela);
		}
		
	}

}
