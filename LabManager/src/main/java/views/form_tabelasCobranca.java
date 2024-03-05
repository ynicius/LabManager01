package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import DAO_DBlabManager.DAO_cadastraExames;
import DAO_DBlabManager.DAO_tabelaCobraca;
import DTO.exameProcedimento_DTO;
import DTO.tabelaCobranca_DTO;
import views.util.limitaFormato;
import views.util.modelComobox;
import views.util.model_tabelaCobranca;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.attribute.AclEntry;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class form_tabelasCobranca extends JFrame {

	private JPanel contentPane;
	private JTextField txt_tabela;
	private DAO_tabelaCobraca list2;
	tabelaCobranca_DTO tabela;
	private JComboBox combo_tabelas;
	private JComboBox combo_tabelas1;
	DefaultComboBoxModel defaultComboBox;
	DefaultComboBoxModel defaultComboBox1;
	modelComobox list;
	public int cont;
	private JPanel panel_2;
	private List<String> lis;
	model_tabelaCobranca model;
	private JTable tabela1;
	JFormattedTextField cel, cel1, cel2, cel3;
	private JButton btn_confirma;
	private JButton btn_mais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form_tabelasCobranca frame = new form_tabelasCobranca();
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
	public form_tabelasCobranca() {
		
		
		model = new model_tabelaCobranca();
		list2 = new DAO_tabelaCobraca();
		cont = 0;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("TABELA DE COBRANCA");

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_2 = new JPanel();
		panel_2.setBorder(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(1)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(15)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(30)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
							.addGap(5)))
					.addGap(2)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
		);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		tabela1 = new JTable(model);		
		JScrollPane scrollPane = new JScrollPane(tabela1);
		
		tabela1.getActionMap().put("enterAction", new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Manda o foco para a outra coluna.
				// ao_table.setColumnSelectionInterval(); Não lembro o nome direito do método...
				grava();
			}
		});
	
		
		
		//DEFINIÇÃO DOS TIPOS DE CARACTERES DO JTABLE
		cel = new JFormattedTextField();
		cel.setDocument(new limitaFormato(11, limitaFormato.TipoEntrada.NADA, false));
		tabela1.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cel));
		
		cel1 = new JFormattedTextField();
		cel1.setDocument(new limitaFormato(10, limitaFormato.TipoEntrada.NUMERETRACO, false));
		tabela1.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cel1));
		
		cel2 = new JFormattedTextField();
		cel2.setDocument(new limitaFormato(45, limitaFormato.TipoEntrada.NOMENUMEROS, true));
		tabela1.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cel2));
		
		cel3 = new JFormattedTextField();
		cel3.setDocument(new limitaFormato(6, limitaFormato.TipoEntrada.NUMERODECIMAL, false));
		tabela1.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cel3));
			//***//
		
		if (tabela1.getColumnModel().getColumnCount() > 0) {
			tabela1.getColumnModel().getColumn(0).setPreferredWidth(50);
			tabela1.getColumnModel().getColumn(1).setPreferredWidth(150);
			// tabelaExames.getColumnModel().getColumn(1).setMaxWidth(250);
			tabela1.getColumnModel().getColumn(2).setPreferredWidth(300);
			tabela1.getColumnModel().getColumn(3).setPreferredWidth(110);
			
		}
		
		//bloqueia mudança na locação das colunas
		tabela1.getTableHeader().setReorderingAllowed(false);
		panel_3.add(scrollPane, BorderLayout.CENTER);

		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tabela de Cobrança:");
		lblNewLabel_1.setBounds(10, 15, 180, 15);
		panel_2.add(lblNewLabel_1);

		// ***CODIGO DE DIFÍCIL RESOLUÇÃO (COMBOBOX ATUALIZAÇÃO DINAMICA)***
		combo_tabelas = new JComboBox();
		combo_tabelas.addItemListener(new ItemListener() {

	public void itemStateChanged(ItemEvent e) {
				carregaTabela();

			}
		});
		defaultComboBox = new DefaultComboBoxModel();
		defaultComboBox.addElement("");
		for (String L : list2.listConvenio()) {
			defaultComboBox.addElement(L);
		}
		// defaultComboBox = new DefaultComboBoxModel(list2.listConvenio().toArray());

		combo_tabelas.setModel(defaultComboBox);
				

		combo_tabelas.setBounds(10, 30, 210, 30);

		panel_2.add(combo_tabelas);
		
		btn_mais = new JButton("+");
		btn_mais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adicionaLinha();
			}
		});
		btn_mais.setForeground(new Color(28, 113, 216));
		btn_mais.setFont(new Font("Dialog", Font.BOLD, 13));
		btn_mais.setBounds(240, 30, 45, 30);
		panel_2.add(btn_mais);
		
		btn_confirma = new JButton("v");
		
		btn_confirma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				grava();
				
			}
		});
		btn_confirma.setForeground(new Color(28, 113, 216));
		btn_confirma.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		btn_confirma.setBounds(297, 30, 45, 30);
		panel_2.add(btn_confirma);

		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome Tabela:");
		lblNewLabel.setBounds(12, 23, 150, 15);
		panel_1.add(lblNewLabel);

		txt_tabela = new JTextField();
		txt_tabela.setDocument(new limitaFormato(45, limitaFormato.TipoEntrada.NOMENUMEROS, true));
		txt_tabela.setBounds(5, 40, 200, 30);
		panel_1.add(txt_tabela);
		txt_tabela.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Tabela Ref.:");
		lblNewLabel_2_1.setBounds(12, 85, 100, 15);
		panel_1.add(lblNewLabel_2_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(5, 100, 200, 30);
		panel_1.add(comboBox_2);

		JButton btn_criar = new JButton("Criar");
		btn_criar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vericaNomeTabela();

			}
		});
		btn_criar.setBounds(128, 142, 90, 35);
		panel_1.add(btn_criar);

		JButton btnDelet = new JButton("Deleta");
		btnDelet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				vericaNomeTabela();
			}
		});
		btnDelet.setBounds(23, 142, 90, 35);
		panel_1.add(btnDelet);
		panel.setLayout(gl_panel);
		//

	}

	public void gravarTabela() {

		int resposta = JOptionPane.showConfirmDialog(null, "Criar nova tabela de cobranca? ", "ATUALIZAR EXAME",
				JOptionPane.YES_NO_OPTION);
		if (resposta == 0) {

			tabela = new tabelaCobranca_DTO();
			tabela.setNomeTabela(txt_tabela.getText());
			list2.gravaNomeTabela(tabela);
			populartabela();
		}

	}

	public void populartabela() {

		defaultComboBox.addElement(txt_tabela.getText());

		/*
		 * for (String L : list2.listConvenio()) { defaultComboBox.addElement(L); }
		 * defaultComboBox.removeAllElements();
		 * 
		 * //combo_tabelas.setModel(defaultComboBox);
		 */

	}

	public void carregaTabela() {
		tabela = new tabelaCobranca_DTO();
		tabela.setNomeTabela(combo_tabelas.getSelectedItem().toString());
		model.buscaTabela(tabela);

	}

	public void vericaNomeTabela() {

		if (txt_tabela.getText().length() <= 0) {
			JOptionPane.showMessageDialog(null, "Codigo em branco, Invalido!");
		} else {

			DAO_tabelaCobraca verifica = new DAO_tabelaCobraca();
			tabela = new tabelaCobranca_DTO();
			tabela.setNomeTabela(txt_tabela.getText());
			if (verifica.verificar_nomeTabela(tabela)) {
				JOptionPane.showMessageDialog(null, "O codigo: " + txt_tabela.getText() + " já existe!");
				limparCampos();
			} else {

				gravarTabela();
				limparCampos();
			}
		}

	}

	public void limparCampos() {
		txt_tabela.setText("");
		combo_tabelas.setSelectedIndex(0);
	}

	public void grava() {
		if (tabela1.getSelectedRow() >= 0) {
			tabela1.editingStopped(null);
			tabela1.requestFocus();
			int linha = tabela1.getSelectedRow();
			DAO_tabelaCobraca grava = new DAO_tabelaCobraca();
			tabela = new tabelaCobranca_DTO();
			tabela.setId_cobranca((int) tabela1.getValueAt(linha, 0));
			tabela.setCodigo(tabela1.getValueAt(linha, 1).toString());
			tabela.setNomeDesc(tabela1.getValueAt(linha, 2).toString());
			tabela.setValor(tabela1.getValueAt(linha, 3).toString());
			tabela.setNomeTabela(combo_tabelas.getSelectedItem().toString());
			grava.gravaTabela(tabela);
			//carregaTabela();
		}

	}
	
	public void adicionaLinha() {
		if (combo_tabelas.getSelectedIndex() >0) {
			System.out.println("ok");
			tabela = new tabelaCobranca_DTO();
			tabela.setNomeTabela(combo_tabelas.getSelectedItem().toString());
			list2.addLinha(tabela);
			carregaTabela();
			
		}
	}
}
