package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.protobuf.TextFormat.ParseException;
import com.google.protobuf.TextFormatParseInfoTree;

import DAO_DBlabManager.DAO_cadastraExames;
import DAO_DBlabManager.DAO_convenios;
import DAO_DBlabManager.DAO_tabelaCobraca;
import DTO.convenio_DTO;
import views.util.limitaFormato;
import views.util.model_tableConvenios;
import views.util.model_tableExames;

import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JComboBox;

public class form_convenios extends JFrame {

	private JPanel contentPane;
	private JTextField txt_convenio;
	private JTextField txt_cober;
	private convenio_DTO dto_conv;
	private DAO_convenios grava;
	private String porpFile = "/home/vinicius/eclipse-workspace/LabManager/propeties/dataInsert.properties";
	private JTable table_convenios;
	private model_tableConvenios model;
	private JTextField txt_idConv;
	private JButton btnAtualizar;
	DefaultComboBoxModel defaultComboBox;
	private DAO_tabelaCobraca  list2;
	private JComboBox boxTabelas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form_convenios frame = new form_convenios();
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
	public form_convenios() {
		
		list2 = new DAO_tabelaCobraca();
		model = new model_tableConvenios();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(1)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE).addGap(1)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(1)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(10)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGap(10)));
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		table_convenios = new JTable(model);
		table_convenios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cli = e.getClickCount();

				if (cli == 2) {
					// carregarLinha();
					// btn_atualizar.setEnabled(true);
					carregaAtualiza();
					btnAtualizar.setEnabled(true);

				}
			}
		});
		// Bloqueia a reordenação das colunas
		table_convenios.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table_convenios);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Convênio:");
		lblNewLabel.setBounds(10, 53, 70, 15);
		panel_1.add(lblNewLabel);

		txt_convenio = new JTextField();
		txt_convenio.setFont(new Font("Dialog", Font.BOLD, 13));
		txt_convenio.setDocument(new limitaFormato(40, limitaFormato.TipoEntrada.MAIUSCULO, true));
		txt_convenio.setBounds(5, 68, 210, 30);
		panel_1.add(txt_convenio);
		txt_convenio.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Cobertura(%):");
		lblNewLabel_1.setBounds(10, 168, 130, 15);
		panel_1.add(lblNewLabel_1);

		txt_cober = new JTextField();
		txt_cober.setFont(new Font("Dialog", Font.BOLD, 13));
		txt_cober.setColumns(10);
		txt_cober.setDocument(new limitaFormato(3, limitaFormato.TipoEntrada.NUMEROINTEIRO, false));
		txt_cober.setBounds(5, 184, 100, 30);
		panel_1.add(txt_cober);

		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gravar();
			}
		});
		btnNewButton.setBounds(120, 256, 98, 30);
		panel_1.add(btnNewButton);

		JButton btnRemover = new JButton("Deletar");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				apagar();
			}
		});
		btnRemover.setBounds(120, 344, 98, 30);
		panel_1.add(btnRemover);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verificar();
			}
		});
		btnAtualizar.setBounds(120, 301, 98, 30);
		panel_1.add(btnAtualizar);

		JLabel lblNewLabel_2 = new JLabel("Código convênio:");
		lblNewLabel_2.setBounds(10, 20, 130, 15);
		panel_1.add(lblNewLabel_2);

		txt_idConv = new JTextField();
		txt_idConv.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		txt_idConv.setEnabled(false);
		txt_idConv.setColumns(10);
		txt_idConv.setBounds(135, 13, 59, 30);
		panel_1.add(txt_idConv);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			limpar();
			btnAtualizar.setEnabled(false);
			}
			
		});
		btnLimpar.setBounds(120, 381, 98, 30);
		panel_1.add(btnLimpar);
		
		JLabel lblTabelaDeCobrana = new JLabel("Tabela de Cobrança:");
		lblTabelaDeCobrana.setBounds(10, 110, 155, 15);
		panel_1.add(lblTabelaDeCobrana);
		
		boxTabelas = new JComboBox();
		
		defaultComboBox = new DefaultComboBoxModel();
		defaultComboBox.addElement("");
		for (String L : list2.listConvenio()) {
			defaultComboBox.addElement(L);
		}
		// defaultComboBox = new DefaultComboBoxModel(list2.listConvenio().toArray());

		boxTabelas.setModel(defaultComboBox);
		
		
		//comboBox.setModel(defaultComboBox);
		
		boxTabelas.setBounds(5, 125, 210, 35);
		panel_1.add(boxTabelas);
		panel.setLayout(gl_panel);

		//
		desligaComponete();
	}

	public void gravar() {
		grava = new DAO_convenios();
		dto_conv = new convenio_DTO();
		dto_conv.setNomeConvenio(txt_convenio.getText());
		if (txt_convenio.getText().length() == 0 || txt_cober.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "O nome do convênio não é válido");
		} else if (grava.verificar_codigoExame(dto_conv)) {
			JOptionPane.showMessageDialog(null, "O nome do convêni já existe");
			limpar();
		} else {
			dto_conv = new convenio_DTO();
			dto_conv.setNomeConvenio(txt_convenio.getText());
			int x = Integer.parseInt(txt_cober.getText());
			dto_conv.setCobertura(x);
			dto_conv.setTabelaCobranca(boxTabelas.getSelectedItem().toString());
			grava = new DAO_convenios();
			grava.inserir_exame(dto_conv);
			model.loadData();
			//limpar();
			
		}
	}

	public void limpar() {
		txt_convenio.setText("");
		txt_cober.setText("");
		txt_idConv.setText("");
		txt_convenio.requestFocus();
		

	}

	//EXCLUIR
	public void verificar() {
		if ((btnAtualizar.isEnabled())) {
			grava = new DAO_convenios();
			String cod;
			cod = txt_convenio.getText();

			dto_conv = new convenio_DTO();
			int linha = table_convenios.getSelectedRow();
			dto_conv.setNomeConvenio(table_convenios.getModel().getValueAt(linha, 1).toString());

			if (txt_convenio.getText().length() == 0 || txt_cober.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "O nome do convênio não é válido");
			} else if (grava.verificaAtualiza(cod, dto_conv)) {
				int resposta = JOptionPane.showConfirmDialog(null, "Modificar nome do convênio.\n Confirma?",
						"EXCLUIR EXAME!", JOptionPane.YES_NO_OPTION);
				if (resposta == 0) {
					dto_conv.setNomeConvenio(txt_convenio.getText());
					dto_conv.setCobertura(Integer.parseInt(txt_cober.getText()));
					dto_conv.setIdConvenio(Integer.parseInt(txt_idConv.getText()));
					grava.atualizarConvenio(dto_conv);
					limpar();
					table_convenios.clearSelection();
					model.loadData();
					desligaComponete();
				}
			} else {
				JOptionPane.showMessageDialog(null, "O nome do convênio já existe");
			}
		}

	}

	public void carregaAtualiza() {
		int linha = table_convenios.getSelectedRow();
		txt_idConv.setText(table_convenios.getValueAt(linha, 0).toString());
		txt_convenio.setText(table_convenios.getValueAt(linha, 1).toString());
		txt_cober.setText(table_convenios.getValueAt(linha, 2).toString());
	}

	public void apagar() {
		if (table_convenios.getModel().getRowCount() < 0 || table_convenios.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "Selecione um convenio da tabela!");
		} else if (table_convenios.getSelectedRow() >= 0) {
			int resposta = JOptionPane.showConfirmDialog(null, "O convênio selecionado será excluido.\n Voce confirma?",
					"EXCLUIR EXAME!", JOptionPane.YES_NO_OPTION);

			if (resposta == 0) {
				int linhaSelecionada = table_convenios.getSelectedRow();
				int codigoApagar = (int) table_convenios.getModel().getValueAt(linhaSelecionada, 0);
				DAO_convenios apaga = new DAO_convenios();
				apaga.apagarExame(codigoApagar);
				model.loadData();
				table_convenios.clearSelection();
				limpar();
			}

		}
	}

	public void desligaComponete() {
		btnAtualizar.setEnabled(false);
	}
}
