package views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import DAO_DBlabManager.DAO_convenios;
import DAO_DBlabManager.DAO_dadoPedido;
import DAO_DBlabManager.DAO_paciente;
import DAO_DBlabManager.conecta_DBlabmanager;
import DTO.dadoPedido_DTO;
import DTO.exameAtendimento_DTO;
import DTO.paciente_DTO;
import teste.model.youtube.model_teste01;
import views.util.dateTimes;
import views.util.limitaFormato;
import views.util.model_exameAtendimento;
import views.util.model_tableExames;
import views.util.model_teste1;

import javax.swing.JTextPane;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class form_Atendimento extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nome;
	private JTextField txt_idade1;
	private JTextField txt_mae;
	private JFormattedTextField txt_cpf;
	private JFormattedTextField txt_rg;
	private JFormattedTextField txt_cns;
	private JFormattedTextField txt_cep;
	private JTextField txt_endereco;
	private JTextField txt_numero;
	private JTextField txt_cidade;
	private JFormattedTextField txt_cel;
	private JFormattedTextField txt_telefone;
	private JTextField txt_email;
	private JTextField txt_codConvenio;
	private JTextField txt_nMatric;
	private JTextField txt_plano;
	private JTextField txt_titular;
	private JTextField txt_medico;
	private JTextField txt_codDoenca;
	private JTextField txt_doeDesc;
	private JTextField txt_coment;
	private JTextField txt_medicamento;
	private JTextField txt_codigo;
	private JTextField txt_descric;
	private JTable table;
	private JTable table_1;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_28;
	JFormattedTextField txt_data;
	private String dataBanco;
	private int gestante;
	private String prioridade;
	private int preferencia;

	MaskFormatter mascaraData;
	private JFormattedTextField txt_data1;
	private JComboBox txt_sexo;
	private JTextArea txt_obs;
	private JCheckBox txt_gesta;
	private JLabel txt_testeData;
	paciente_DTO set_dto = new paciente_DTO();
	dadoPedido_DTO set_dado = new dadoPedido_DTO();
	DAO_paciente grava_pacient_banco;
	private ButtonGroup radioPref;
	private JRadioButton txt_normal;
	private JRadioButton txt_urgente;
	private JRadioButton txt_emergencia;
	private JRadioButton txt_imediata;
	private JFormattedTextField txt_validade;
	private JCheckBox txt_preferencia;
	private JTextField txt_crm;
	private JLabel lblMedicamento;
	private JPanel panel_6;
	private JComboBox txt_jejum;
	private JLabel txt_radio;
	private JButton btn_radio;	
	private JButton btn_add;
	public model_teste1 model01;
	JTable tabExame;
	public int seq;
	private DAO_convenios lista1;
	private DefaultComboBoxModel defaultComboBox;
	private JComboBox txt_convenio;
	private JLabel lbl_convenio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form_Atendimento frame = new form_Atendimento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public form_Atendimento() throws ParseException {
		lista1 = new DAO_convenios();
		model01 = new model_teste1();
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(780, 560);
		setTitle("ATENDIMENTO - PACIENTE");
		setMinimumSize(new Dimension(850, 700));
		setResizable(true);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_id = new JPanel();
		panel_id.setBackground(new Color(153, 193, 241));
		contentPane.add(panel_id, BorderLayout.NORTH);
		panel_id.setPreferredSize(new Dimension(10, 80));
		panel_id.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cód. Atend.:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setBounds(12, 10, 80, 15);
		panel_id.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("00.000");
		lblNewLabel_1.setBounds(90, 10, 100, 15);
		panel_id.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Convênio:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(12, 40, 70, 15);
		panel_id.add(lblNewLabel_2);

		lbl_convenio = new JLabel("");
		lbl_convenio.setBounds(75, 40, 400, 15);
		panel_id.add(lbl_convenio);

		JLabel lblNewLabel_4 = new JLabel("Nome:");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(150, 10, 70, 15);
		panel_id.add(lblNewLabel_4);

		JLabel txt_cabecalho = new JLabel("Xxxxxxx Xx Xxxxx Xxxxxxxx");
		txt_cabecalho.setFont(new Font("Dialog", Font.BOLD, 17));
		txt_cabecalho.setBounds(190, 8, 335, 20);
		panel_id.add(txt_cabecalho);

		JLabel lblNewLabel_6 = new JLabel("Data Nasc:");
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(530, 11, 80, 15);
		panel_id.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("00/00/0000");
		lblNewLabel_7.setBounds(600, 11, 85, 15);
		panel_id.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Idade:");
		lblNewLabel_8.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(685, 10, 70, 15);
		panel_id.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("000");
		lblNewLabel_9.setBounds(725, 10, 38, 15);
		panel_id.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("anos");
		lblNewLabel_10.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(750, 10, 70, 15);
		panel_id.add(lblNewLabel_10);

		JPanel panel_inferior = new JPanel();
		panel_inferior.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(panel_inferior, BorderLayout.SOUTH);
		panel_inferior.setPreferredSize(new Dimension(10, 150));
		panel_inferior.setLayout(new BorderLayout(0, 0));

		JPanel panel_butao = new JPanel();
		panel_butao.setBackground(UIManager.getColor("Button.background"));
		panel_inferior.add(panel_butao, BorderLayout.NORTH);
		panel_butao.setPreferredSize(new Dimension(10, 80));

		JPanel panel_1 = new JPanel();
		GroupLayout gl_panel_butao = new GroupLayout(panel_butao);
		gl_panel_butao.setHorizontalGroup(gl_panel_butao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_butao.createSequentialGroup().addGap(2)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 825, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(797, Short.MAX_VALUE)));
		gl_panel_butao.setVerticalGroup(
				gl_panel_butao.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_butao.createSequentialGroup()
						.addGap(2).addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE).addGap(5)));
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));

		JButton btc_gravar = new JButton("GRAVAR");
		btc_gravar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				grava_pacienteDTO();
				txt_testeData.setText(grava_pacient_banco.idUltimo);

			}
		});

		btn_radio = new JButton("RADIO");

		btn_radio.addActionListener((e) -> {
			txt_radio.setText(prioridades());
		});

		/*
		 * btn_radio.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent e) {
		 * txt_radio.setText(prioridades());
		 * 
		 * } });
		 */

		txt_radio = new JLabel("New label");
		panel_1.add(txt_radio);
		panel_1.add(btn_radio);

		txt_testeData = new JLabel("data");
		panel_1.add(txt_testeData);
		panel_1.add(btc_gravar);

		JButton btnNewButton_1 = new JButton("CANCELAR");
		panel_1.add(btnNewButton_1);

		JButton btnNewButton = new JButton("FINALIZAR");
		panel_1.add(btnNewButton);
		panel_butao.setLayout(gl_panel_butao);

		JPanel panel_rodaPe = new JPanel();
		panel_rodaPe.setBorder(null);
		panel_rodaPe.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		panel_inferior.add(panel_rodaPe, BorderLayout.SOUTH);
		panel_rodaPe.setPreferredSize(new Dimension(10, 27));

		JLabel lblNewLabel_11_2 = new JLabel("Unidade");

		JLabel lblNewLabel_11 = new JLabel("Usuário");

		JLabel lblNewLabel_12 = new JLabel("Data/Hora");
		panel_rodaPe.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));
		panel_rodaPe.add(lblNewLabel_11_2);
		panel_rodaPe.add(lblNewLabel_11);
		panel_rodaPe.add(lblNewLabel_12);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(UIManager.getColor("Button.highlight"));
		tabbedPane.setPreferredSize(new Dimension(840, 10));
		contentPane.add(tabbedPane, BorderLayout.WEST);

		JPanel panel_paciente = new JPanel();
		panel_paciente.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Cliente", null, panel_paciente, null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51)), "Observa\u00E7\u00F5es:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_4.setBackground(UIManager.getColor("Button.background"));
		GroupLayout gl_panel_paciente = new GroupLayout(panel_paciente);
		gl_panel_paciente.setHorizontalGroup(gl_panel_paciente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_paciente.createSequentialGroup().addGap(5)
						.addGroup(gl_panel_paciente.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 830, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 827, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_paciente.setVerticalGroup(gl_panel_paciente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_paciente.createSequentialGroup().addGap(1)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE).addGap(5)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane);

		txt_obs = new JTextArea();
		txt_obs.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_obs.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_obs.setBackground(new Color(255, 255, 255));
			}
		});
		txt_obs.setDocument(new limitaFormato(110, limitaFormato.TipoEntrada.EMAIL, false));
		scrollPane.setViewportView(txt_obs);
		panel.setLayout(null);

		JPanel panel_nome_1 = new JPanel();
		panel_nome_1.setLayout(null);
		panel_nome_1.setPreferredSize(new Dimension(10, 125));
		panel_nome_1.setMinimumSize(new Dimension(10, 125));
		panel_nome_1.setMaximumSize(new Dimension(10, 125));
		panel_nome_1.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51)), "Paciente", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_nome_1.setBounds(1, 0, 825, 125);
		panel.add(panel_nome_1);

		JLabel lblNewLabel_11_1 = new JLabel("Fichário");
		lblNewLabel_11_1.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_11_1.setBounds(12, 15, 70, 15);
		panel_nome_1.add(lblNewLabel_11_1);

		JLabel lblNewLabel_12_1 = new JLabel("Nome: *");
		lblNewLabel_12_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_12_1.setBounds(74, 15, 70, 15);
		panel_nome_1.add(lblNewLabel_12_1);

		JLabel lblNewLabel_13_1 = new JLabel("00.000");
		lblNewLabel_13_1.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_13_1.setBounds(15, 30, 50, 15);
		panel_nome_1.add(lblNewLabel_13_1);

		txt_nome = new JTextField();
		txt_nome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_nome.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_nome.setBackground(new Color(255, 255, 255));
			}
		});

		txt_nome.setDocument(new limitaFormato(50, limitaFormato.TipoEntrada.EMAIL, true));
		txt_nome.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				txt_cabecalho.setText(txt_nome.getText());
			}
		});
		txt_nome.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_nome.setColumns(10);
		txt_nome.setBounds(73, 30, 315, 30);
		panel_nome_1.add(txt_nome);

		JLabel lblNewLabel_14_2 = new JLabel("F2");
		lblNewLabel_14_2.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_14_2.setBounds(391, 32, 30, 30);
		panel_nome_1.add(lblNewLabel_14_2);

		JLabel lblNewLabel_14_1_1 = new JLabel("F2");
		lblNewLabel_14_1_1.setForeground(new Color(26, 95, 180));
		lblNewLabel_14_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_14_1_1.setBounds(420, 31, 35, 30);
		panel_nome_1.add(lblNewLabel_14_1_1);

		txt_sexo = new JComboBox();
		txt_sexo.setFont(new Font("Dialog", Font.BOLD, 11));
		txt_sexo.setModel(
				new DefaultComboBoxModel(new String[] { "Selecione...", "Masculino", "Feminino", "Não inform." }));
		txt_sexo.setBounds(440, 32, 102, 25);
		panel_nome_1.add(txt_sexo);

		JLabel lblNewLabel_15_1 = new JLabel("Sexo:*");
		lblNewLabel_15_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_15_1.setBounds(450, 15, 55, 15);
		panel_nome_1.add(lblNewLabel_15_1);

		JLabel lblNewLabel_16_1 = new JLabel("Data Nasc.:");
		lblNewLabel_16_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_16_1.setBounds(560, 15, 100, 15);
		panel_nome_1.add(lblNewLabel_16_1);

		JLabel lblNewLabel_17_1 = new JLabel("Idade:");
		lblNewLabel_17_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_17_1.setBounds(670, 15, 70, 15);
		panel_nome_1.add(lblNewLabel_17_1);

		txt_idade1 = new JTextField();
		txt_idade1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_idade1.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_idade1.setBackground(new Color(255, 255, 255));
			}

		});
		txt_idade1.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_idade1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String anos;
				anos = txt_idade1.getText();
				dateTimes data_nasc = new dateTimes();

				txt_data1.setText(data_nasc.calc_dataNacimento(anos));

			}

		});
		txt_idade1.setColumns(10);
		txt_idade1.setBounds(665, 30, 60, 30);
		panel_nome_1.add(txt_idade1);

		txt_gesta = new JCheckBox("Gestante");
		txt_gesta.setFont(new Font("Dialog", Font.PLAIN, 12));
		txt_gesta.setBounds(732, 32, 90, 23);
		panel_nome_1.add(txt_gesta);

		JLabel lblNewLabel_18_1 = new JLabel("Nome da Mãe:");
		lblNewLabel_18_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_18_1.setBounds(15, 65, 100, 15);
		panel_nome_1.add(lblNewLabel_18_1);

		txt_mae = new JTextField();
		txt_mae.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_mae.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_mae.setBackground(new Color(255, 255, 255));
			}
		});
		txt_mae.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_mae.setDocument(new limitaFormato(30, limitaFormato.TipoEntrada.EMAIL, true));
		txt_mae.setColumns(10);
		txt_mae.setBounds(15, 82, 200, 25);
		panel_nome_1.add(txt_mae);

		JLabel lblNewLabel_19_1 = new JLabel("CPF:");
		lblNewLabel_19_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_19_1.setBounds(241, 65, 70, 15);
		panel_nome_1.add(lblNewLabel_19_1);

		MaskFormatter mascara_cpf = new MaskFormatter("###.###.###-##");
		txt_cpf = new JFormattedTextField(mascara_cpf);
		txt_cpf.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				txt_data1.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_data1.setBackground(new Color(255, 255, 255));
			}
		});
		txt_cpf.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_cpf.setColumns(10);
		txt_cpf.setBounds(239, 82, 114, 25);
		panel_nome_1.add(txt_cpf);

		JLabel lbl_rg = new JLabel("RG:");

		lbl_rg.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbl_rg.setBounds(380, 65, 60, 15);
		panel_nome_1.add(lbl_rg);

		txt_rg = new JFormattedTextField();
		txt_rg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_rg.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_rg.setBackground(new Color(255, 255, 255));
			}
		});
		txt_rg.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_rg.setDocument(new limitaFormato(10, limitaFormato.TipoEntrada.NUMERODECIMAL, false));
		txt_rg.setColumns(10);
		txt_rg.setBounds(378, 82, 85, 25);
		panel_nome_1.add(txt_rg);

		JLabel lblNewLabel_21_1 = new JLabel("CNS:");
		lblNewLabel_21_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_21_1.setBounds(482, 65, 70, 15);
		panel_nome_1.add(lblNewLabel_21_1);

		MaskFormatter mascara_cns = new MaskFormatter("###.####.####.####");
		txt_cns = new JFormattedTextField(mascara_cns);
		txt_cns.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_cns.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_cns.setBackground(new Color(255, 255, 255));
			}
		});
		txt_cns.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_cns.setColumns(10);
		txt_cns.setBounds(482, 82, 140, 25);
		panel_nome_1.add(txt_cns);

		MaskFormatter mascara_data = new MaskFormatter("##/##/####");
		txt_data1 = new JFormattedTextField(mascara_data);
		txt_data1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_data1.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_data1.setBackground(new Color(255, 255, 255));
			}

		});
		txt_data1.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_data1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				// dateTimes validacao01 = new dateTimes(txt_data1.getText());
				dateTimes validacao = new dateTimes(txt_data1.getText());

				if (validacao.valida_data()) {
					dateTimes txt_data_banco = new dateTimes(txt_data1.getText());
					dataBanco = txt_data_banco.conveter_data_string();
					txt_idade1.setText(txt_data_banco.calcula_idade());
					txt_testeData.setText(dataBanco);

					// dataBanco = txt_data_banco.calc_dataNacimento(txt_data1.getText());
					// txt_idade1.setText(txt_data_banco.calcula_idade());

				} else {
					txt_data1.setText("");
					txt_idade1.setText("");
				}

			}
		});
		txt_data1.setBounds(557, 30, 90, 30);
		panel_nome_1.add(txt_data1);

		JPanel panel_end_1 = new JPanel();
		panel_end_1.setLayout(null);
		panel_end_1.setPreferredSize(new Dimension(50, 115));
		panel_end_1.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51)), "Endere\u00E7o/Contato",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_end_1.setBounds(1, 125, 825, 121);
		panel.add(panel_end_1);

		JLabel lblNewLabel_22_5 = new JLabel("CEP:");
		lblNewLabel_22_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_22_5.setBounds(15, 18, 70, 15);
		panel_end_1.add(lblNewLabel_22_5);

		JLabel lblNewLabel_22_1_1 = new JLabel("Endereço:");
		lblNewLabel_22_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_22_1_1.setBounds(137, 18, 90, 15);
		panel_end_1.add(lblNewLabel_22_1_1);

		JLabel lblNewLabel_22_2_1 = new JLabel("Número:");
		lblNewLabel_22_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_22_2_1.setBounds(385, 18, 70, 15);
		panel_end_1.add(lblNewLabel_22_2_1);

		JLabel lblNewLabel_22_3_1 = new JLabel("Cidade:");
		lblNewLabel_22_3_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_22_3_1.setBounds(560, 18, 70, 15);
		panel_end_1.add(lblNewLabel_22_3_1);

		MaskFormatter masc_cep = new MaskFormatter("##.###-###");
		txt_cep = new JFormattedTextField(masc_cep);
		txt_cep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_cep.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_cep.setBackground(new Color(255, 255, 255));
			}
		});
		txt_cep.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_cep.setColumns(10);
		txt_cep.setBounds(12, 35, 90, 25);
		panel_end_1.add(txt_cep);

		txt_endereco = new JTextField();
		txt_endereco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_endereco.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_endereco.setBackground(new Color(255, 255, 255));
			}
		});
		txt_endereco.setFont(new Font("Dialog", Font.BOLD, 12));
		// txt_endereco.setDocument(new limitaFormato (10, limitaFormato.EMAIL));
		txt_endereco.setDocument(new limitaFormato(40, limitaFormato.TipoEntrada.EMAIL, false));
		txt_endereco.setColumns(5);
		txt_endereco.setBounds(126, 35, 230, 25);
		panel_end_1.add(txt_endereco);

		txt_numero = new JTextField();
		txt_numero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_numero.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_numero.setBackground(new Color(255, 255, 255));
			}
		});
		txt_numero.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_numero.setDocument(new limitaFormato(12, limitaFormato.TipoEntrada.EMAIL, false));
		txt_numero.setColumns(5);
		txt_numero.setBounds(380, 35, 150, 25);
		panel_end_1.add(txt_numero);

		txt_cidade = new JTextField();
		txt_cidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_cidade.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_cidade.setBackground(new Color(255, 255, 255));
			}
		});
		txt_cidade.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_cidade.setDocument(new limitaFormato(25, limitaFormato.TipoEntrada.EMAIL, false));
		txt_cidade.setColumns(1);
		txt_cidade.setBounds(557, 35, 200, 25);
		panel_end_1.add(txt_cidade);

		JLabel lblNewLabel_22_4_3 = new JLabel();
		lblNewLabel_22_4_3.setText("Celular/whats:");
		lblNewLabel_22_4_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_22_4_3.setBounds(15, 65, 110, 15);
		panel_end_1.add(lblNewLabel_22_4_3);

		JLabel lblNewLabel_22_4_1_1 = new JLabel("Telefone:");
		lblNewLabel_22_4_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_22_4_1_1.setBounds(180, 65, 70, 15);
		panel_end_1.add(lblNewLabel_22_4_1_1);

		JLabel lblNewLabel_22_4_2_1 = new JLabel("e-Mail");
		lblNewLabel_22_4_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_22_4_2_1.setBounds(350, 65, 70, 15);
		panel_end_1.add(lblNewLabel_22_4_2_1);

		MaskFormatter masc_celular = new MaskFormatter("(##)#####-####");
		txt_cel = new JFormattedTextField(masc_celular);
		txt_cel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_cel.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_cel.setBackground(new Color(255, 255, 255));
			}
		});
		txt_cel.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_cel.setColumns(10);
		txt_cel.setBounds(12, 80, 130, 25);
		panel_end_1.add(txt_cel);

		MaskFormatter masc_telefone = new MaskFormatter("(##)####-####");
		txt_telefone = new JFormattedTextField(masc_telefone);
		txt_telefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_telefone.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_telefone.setBackground(new Color(255, 255, 255));
			}
		});
		txt_telefone.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_telefone.setColumns(10);
		txt_telefone.setBounds(175, 80, 130, 25);
		panel_end_1.add(txt_telefone);

		txt_email = new JTextField();
		txt_email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_email.setBackground(new Color(255, 255, 205));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_email.setBackground(new Color(255, 255, 255));
			}
		});
		txt_email.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_email.setDocument(new limitaFormato(35, limitaFormato.TipoEntrada.EMAIL, false));
		txt_email.setColumns(10);
		txt_email.setBounds(340, 80, 200, 25);
		panel_end_1.add(txt_email);
		panel_paciente.setLayout(gl_panel_paciente);

		JPanel panel_dados = new JPanel();
		panel_dados.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Dados do Pedido", null, panel_dados, null);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Conv\u00EAnio", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel_6 = new JPanel();
		panel_6.setBorder(null);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(null);
		GroupLayout gl_panel_dados = new GroupLayout(panel_dados);
		gl_panel_dados.setHorizontalGroup(gl_panel_dados.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_dados.createSequentialGroup().addGap(2)
						.addGroup(gl_panel_dados.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGap(2))
				.addGroup(gl_panel_dados.createSequentialGroup().addGap(2)
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(2)));
		gl_panel_dados.setVerticalGroup(gl_panel_dados.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_dados.createSequentialGroup().addGap(2)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addGap(2)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE).addGap(2)
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 6, Short.MAX_VALUE).addGap(5)));
		panel_9.setLayout(null);

		JLabel lblComentrio = new JLabel("Comentário:");
		lblComentrio.setBounds(20, 2, 90, 15);
		lblComentrio.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_9.add(lblComentrio);

		txt_coment = new JTextField();
		txt_coment.setColumns(10);
		txt_coment.setBounds(15, 20, 350, 30);
		panel_9.add(txt_coment);

		lblMedicamento = new JLabel("Medicamento");
		lblMedicamento.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMedicamento.setBounds(422, 2, 90, 15);
		panel_9.add(lblMedicamento);

		txt_medicamento = new JTextField();
		txt_medicamento.setColumns(10);
		txt_medicamento.setBounds(417, 20, 350, 30);
		panel_9.add(txt_medicamento);
		panel_6.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51), 2), "Prioridade", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_7.setBounds(15, 10, 550, 60);
		panel_6.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));

		txt_normal = new JRadioButton("NORMAL");
		txt_normal.setSelected(true);
		panel_7.add(txt_normal);

		txt_urgente = new JRadioButton("URGÊNCIA");
		panel_7.add(txt_urgente);

		txt_emergencia = new JRadioButton("EMERGÊNCIA");
		panel_7.add(txt_emergencia);

		txt_imediata = new JRadioButton("IMEDIATA");
		panel_7.add(txt_imediata);

		radioPref = new ButtonGroup();
		radioPref.add(txt_normal);
		radioPref.add(txt_urgente);
		radioPref.add(txt_emergencia);
		radioPref.add(txt_imediata);

		txt_preferencia = new JCheckBox("Atendimento Prefencial");
		txt_preferencia.setBounds(596, 25, 200, 23);
		panel_6.add(txt_preferencia);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51)), "Solicitante", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_8.setBounds(3, 80, 824, 85);
		panel_6.add(panel_8);
		panel_8.setLayout(null);

		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setBounds(12, 20, 47, 15);
		lblCrm.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_8.add(lblCrm);

		txt_crm = new JTextField();
		txt_crm.setColumns(10);
		txt_crm.setBounds(12, 38, 80, 30);
		panel_8.add(txt_crm);

		JLabel lblNewLabel_14_2_1 = new JLabel("F2");
		lblNewLabel_14_2_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_14_2_1.setBounds(98, 38, 30, 30);
		panel_8.add(lblNewLabel_14_2_1);

		JLabel lblMdicosolicitante = new JLabel("Médico/Solicitante");
		lblMdicosolicitante.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMdicosolicitante.setBounds(142, 20, 125, 15);
		panel_8.add(lblMdicosolicitante);

		txt_medico = new JTextField();
		txt_medico.setColumns(10);
		txt_medico.setBounds(140, 38, 250, 30);
		panel_8.add(txt_medico);

		JLabel lblDoenacid = new JLabel("Doença/Cid");
		lblDoenacid.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDoenacid.setBounds(420, 20, 125, 15);
		panel_8.add(lblDoenacid);

		txt_codDoenca = new JTextField();
		txt_codDoenca.setColumns(10);
		txt_codDoenca.setBounds(420, 38, 100, 30);
		panel_8.add(txt_codDoenca);

		JLabel lblNewLabel_14_2_1_1 = new JLabel("F2");
		lblNewLabel_14_2_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_14_2_1_1.setBounds(530, 38, 30, 30);
		panel_8.add(lblNewLabel_14_2_1_1);

		txt_doeDesc = new JTextField();
		txt_doeDesc.setColumns(10);
		txt_doeDesc.setBounds(562, 38, 250, 30);
		panel_8.add(txt_doeDesc);

		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDescrio.setBounds(571, 20, 125, 15);
		panel_8.add(lblDescrio);
		panel_5.setLayout(null);

		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCdigo.setBounds(13, 15, 70, 15);
		panel_5.add(lblCdigo);

		JLabel lblConvnio = new JLabel("Convênio");
		lblConvnio.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblConvnio.setBounds(121, 15, 70, 15);
		panel_5.add(lblConvnio);

		JLabel lblNmatrcula = new JLabel("Nº.Matrícula");
		lblNmatrcula.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNmatrcula.setBounds(360, 15, 84, 15);
		panel_5.add(lblNmatrcula);

		JLabel lblCdigo_3 = new JLabel("Plano:");
		lblCdigo_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCdigo_3.setBounds(490, 15, 70, 15);
		panel_5.add(lblCdigo_3);

		txt_codConvenio = new JTextField();
		txt_codConvenio.setBounds(12, 33, 80, 30);
		panel_5.add(txt_codConvenio);
		txt_codConvenio.setColumns(10);
		
		defaultComboBox = new DefaultComboBoxModel();
		txt_convenio = new JComboBox(defaultComboBox);		
		txt_convenio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				titleConvenio();
				
			}
		});
		defaultComboBox.addElement("");
		for (String L : lista1.listaConvenio()) {
			defaultComboBox.addElement(L);
		}
		
		txt_convenio.setBounds(120, 33, 210, 30);
		panel_5.add(txt_convenio);

		txt_nMatric = new JTextField();
		txt_nMatric.setColumns(10);
		txt_nMatric.setBounds(355, 33, 110, 30);
		panel_5.add(txt_nMatric);

		txt_plano = new JTextField();
		txt_plano.setColumns(10);
		txt_plano.setBounds(489, 33, 110, 30);
		panel_5.add(txt_plano);

		JLabel lblCdigo_3_1 = new JLabel("Valid.Cartão");
		lblCdigo_3_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCdigo_3_1.setBounds(628, 15, 90, 15);
		panel_5.add(lblCdigo_3_1);

		txt_validade = new JFormattedTextField();
		txt_validade.setText("__ /__ /____");
		txt_validade.setBounds(625, 33, 90, 30);
		panel_5.add(txt_validade);

		JLabel lblCdigo_3_1_1 = new JLabel("Titular");
		lblCdigo_3_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCdigo_3_1_1.setBounds(736, 15, 90, 15);
		panel_5.add(lblCdigo_3_1_1);

		txt_titular = new JTextField();
		txt_titular.setColumns(10);
		txt_titular.setBounds(735, 33, 85, 30);
		panel_5.add(txt_titular);
		panel_dados.setLayout(gl_panel_dados);

		JPanel panel_exames = new JPanel();
		tabbedPane.addTab("Exames", null, panel_exames, null);

		JPanel panel_2 = new JPanel();

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_panel_exames = new GroupLayout(panel_exames);
		gl_panel_exames.setHorizontalGroup(gl_panel_exames.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_exames.createSequentialGroup().addGap(1).addGroup(gl_panel_exames
						.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addGroup(gl_panel_exames.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(1)))
						.addGap(1)));
		gl_panel_exames.setVerticalGroup(gl_panel_exames.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_exames
				.createSequentialGroup().addGap(1)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(1)
				.addGroup(gl_panel_exames.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_exames.createSequentialGroup()
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(1))
						.addGroup(gl_panel_exames.createSequentialGroup()
								.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE).addGap(1)))));

		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Sub Total", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_18 = new JPanel();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(5)
						.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(5))
				.addGroup(gl_panel_3.createSequentialGroup().addGap(2)
						.addComponent(panel_18, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(2)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(78)
						.addComponent(panel_18, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
						.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGap(5)));
		panel_18.setLayout(null);

		JLabel lblNewLabel_18 = new JLabel("Previsão de Result.");
		lblNewLabel_18.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_18.setBounds(2, 12, 150, 15);
		panel_18.add(lblNewLabel_18);

		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(2, 30, 100, 30);
		panel_18.add(formattedTextField_2);
		panel_12.setLayout(null);

		JLabel lblNewLabel_15 = new JLabel("Valor da Fatura:");
		lblNewLabel_15.setBounds(5, 28, 120, 15);
		panel_12.add(lblNewLabel_15);

		JTextPane textPane = new JTextPane();
		textPane.setText("0,00 ");
		textPane.setFont(new Font("Dialog", Font.BOLD, 14));
		textPane.setBounds(20, 45, 45, 21);
		panel_12.add(textPane);

		JLabel lblR = new JLabel("R$");
		lblR.setBounds(70, 45, 40, 15);
		panel_12.add(lblR);

		JLabel lblNewLabel_15_2 = new JLabel("Particular:");
		lblNewLabel_15_2.setBounds(5, 86, 120, 15);
		panel_12.add(lblNewLabel_15_2);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("0,00 ");
		textPane_1.setFont(new Font("Dialog", Font.BOLD, 14));
		textPane_1.setBounds(20, 103, 45, 21);
		panel_12.add(textPane_1);

		JLabel lblR_1 = new JLabel("R$");
		lblR_1.setBounds(70, 103, 40, 15);
		panel_12.add(lblR_1);
		panel_3.setLayout(gl_panel_3);

		JPanel panel_11 = new JPanel();

		table = new JTable(model01);
		// table.add(new JScrollPane(table));
		// scrollPane_1.setViewportView(table);

		JPanel panelTabela = new JPanel();
		panelTabela.add(new JScrollPane(table));
		panelTabela.setLayout(new GridLayout(0, 1, 0, 0));
		// adição do model ao panel
		// tabExame = new JTable ( new model_exameAtendimento());
		// panelTabela.add(new JScrollPane(tabExame));

		JPanel panel_13 = new JPanel();
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(gl_panel_10.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_10
				.createSequentialGroup().addGap(5)
				.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_10.createSequentialGroup()
								.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelTabela, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(23, Short.MAX_VALUE)));
		gl_panel_10.setVerticalGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_10.createSequentialGroup().addGap(1).addComponent(panel_11,
										GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_10.createSequentialGroup().addGap(2).addComponent(panel_13,
										GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
						.addGap(10).addComponent(panelTabela, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addGap(1)));
		panel_13.setLayout(null);

		btn_add = new JButton("Adic.");
		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addDadoTabela();
			}
		});
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDadoTabela();
				
				
			}
		});
		btn_add.setBounds(2, 5, 70, 40);
		panel_13.add(btn_add);

		JButton btnNewButton_4_1 = new JButton("Excl.");
		btnNewButton_4_1.setBounds(85, 5, 70, 40);
		panel_13.add(btnNewButton_4_1);

		panel_11.setLayout(null);

		JLabel lblNewLabel_13 = new JLabel("Código:");
		lblNewLabel_13.setBounds(5, 5, 70, 15);
		panel_11.add(lblNewLabel_13);

		txt_codigo = new JTextField();
		txt_codigo.setForeground(new Color(26, 95, 180));
		txt_codigo.setFont(new Font("Dialog", Font.BOLD, 14));
		txt_codigo.setBounds(2, 21, 80, 30);
		panel_11.add(txt_codigo);
		txt_codigo.setColumns(10);

		JLabel lblNewLabel_13_2 = new JLabel("Descrição do Exame");
		lblNewLabel_13_2.setBounds(107, 5, 150, 15);
		panel_11.add(lblNewLabel_13_2);

		txt_descric = new JTextField();
		txt_descric.setForeground(new Color(26, 95, 180));
		txt_descric.setFont(new Font("Dialog", Font.BOLD, 14));
		txt_descric.setColumns(5);
		txt_descric.setBounds(100, 21, 320, 30);
		panel_11.add(txt_descric);

		JLabel lblNewLabel_13_2_1 = new JLabel("Fig");
		lblNewLabel_13_2_1.setIcon(new ImageIcon("/home/vinicius/eclipse-workspace/LabManager/imag/busca.png"));
		lblNewLabel_13_2_1.setBounds(422, 21, 30, 30);
		panel_11.add(lblNewLabel_13_2_1);

		JLabel lblNewLabel_13_2_1_1 = new JLabel("F2");
		lblNewLabel_13_2_1_1.setBounds(450, 21, 25, 30);
		panel_11.add(lblNewLabel_13_2_1_1);
		panel_10.setLayout(gl_panel_10);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnNewButton_3 = new JButton("Orçamento");
		panel_2.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("Material");
		panel_2.add(btnNewButton_2);
		panel_exames.setLayout(gl_panel_exames);

		JPanel panel_pagam = new JPanel();
		tabbedPane.addTab("Pagamento", null, panel_pagam, null);

		JPanel panel_14 = new JPanel();

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(null, "Descri\u00E7\u00E3o dos exam.", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(null, "Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(null, "Dados Gerais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_pagam = new GroupLayout(panel_pagam);
		gl_panel_pagam.setHorizontalGroup(gl_panel_pagam.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_pagam.createSequentialGroup().addGap(2)
						.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(2))
				.addGroup(gl_panel_pagam.createSequentialGroup().addGap(1)
						.addComponent(panel_15, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(1))
				.addGroup(gl_panel_pagam.createSequentialGroup().addGap(1)
						.addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(panel_17, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(10)));
		gl_panel_pagam.setVerticalGroup(gl_panel_pagam.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_pagam
				.createSequentialGroup().addGap(2)
				.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(1)
				.addComponent(panel_15, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_pagam.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_pagam.createSequentialGroup().addGap(5)
								.addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(5))
						.addGroup(gl_panel_pagam.createSequentialGroup().addGap(5)
								.addComponent(panel_17, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(50)))));
		panel_17.setLayout(null);

		JLabel lblNewLabel_17 = new JLabel("Data de Entrega");
		lblNewLabel_17.setBounds(10, 25, 135, 15);
		panel_17.add(lblNewLabel_17);

		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextField_1.setText("__/__/____");
		formattedTextField_1.setBounds(15, 40, 80, 30);
		panel_17.add(formattedTextField_1);

		JLabel lblNewLabel_16_3_2 = new JLabel("IC");
		lblNewLabel_16_3_2.setBounds(101, 40, 30, 30);
		panel_17.add(lblNewLabel_16_3_2);

		JLabel lblNewLabel_17_2 = new JLabel("Hora:");
		lblNewLabel_17_2.setBounds(157, 25, 59, 15);
		panel_17.add(lblNewLabel_17_2);

		JFormattedTextField formattedTextField_1_1 = new JFormattedTextField();
		formattedTextField_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextField_1_1.setBounds(150, 40, 80, 30);
		panel_17.add(formattedTextField_1_1);

		txt_jejum = new JComboBox();
		txt_jejum.setModel(new DefaultComboBoxModel(new String[] { "Sim", "Não" }));
		txt_jejum.setBounds(280, 40, 80, 30);
		panel_17.add(txt_jejum);

		JLabel lblNewLabel_17_2_1 = new JLabel("Jejum:");
		lblNewLabel_17_2_1.setBounds(290, 25, 59, 15);
		panel_17.add(lblNewLabel_17_2_1);
		panel_16.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("SUB TOTAL");
		lblNewLabel_14.setBounds(10, 20, 100, 15);
		panel_16.add(lblNewLabel_14);

		JLabel lblNewLabel_16 = new JLabel("Desconto:  %");
		lblNewLabel_16.setBounds(20, 47, 100, 15);
		panel_16.add(lblNewLabel_16);

		JLabel lblNewLabel_16_2 = new JLabel("R$");
		lblNewLabel_16_2.setBounds(299, 20, 30, 15);
		panel_16.add(lblNewLabel_16_2);

		JLabel lblNewLabel_16_2_1 = new JLabel("0,00");
		lblNewLabel_16_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_16_2_1.setBounds(325, 20, 70, 15);
		panel_16.add(lblNewLabel_16_2_1);

		textField_12 = new JTextField();
		textField_12.setBounds(120, 45, 80, 30);
		panel_16.add(textField_12);
		textField_12.setColumns(10);

		JLabel lblNewLabel_16_3 = new JLabel("R$");
		lblNewLabel_16_3.setBounds(225, 47, 30, 15);
		panel_16.add(lblNewLabel_16_3);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(250, 45, 100, 30);
		panel_16.add(textField_13);

		JLabel lblNewLabel_14_1 = new JLabel("TOTAL");
		lblNewLabel_14_1.setBounds(250, 85, 57, 15);
		panel_16.add(lblNewLabel_14_1);

		JLabel lblNewLabel_14_1_2 = new JLabel("0,00");
		lblNewLabel_14_1_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_14_1_2.setBounds(300, 85, 80, 15);
		panel_16.add(lblNewLabel_14_1_2);

		JLabel lblNewLabel_16_4 = new JLabel("Forma de Pagamento:");
		lblNewLabel_16_4.setBounds(10, 98, 200, 15);
		panel_16.add(lblNewLabel_16_4);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(20, 125, 150, 30);
		panel_16.add(comboBox_2);

		JLabel lblNewLabel_16_3_1 = new JLabel("R$");
		lblNewLabel_16_3_1.setBounds(225, 130, 30, 15);
		panel_16.add(lblNewLabel_16_3_1);

		textField_28 = new JTextField();
		textField_28.setColumns(10);
		textField_28.setBounds(250, 125, 100, 30);
		panel_16.add(textField_28);

		JLabel lblNewLabel_14_1_3 = new JLabel("TOTAL PAGO  R$");
		lblNewLabel_14_1_3.setForeground(new Color(53, 132, 228));
		lblNewLabel_14_1_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_14_1_3.setBounds(50, 164, 150, 20);
		panel_16.add(lblNewLabel_14_1_3);

		JLabel lblNewLabel_14_1_3_1 = new JLabel("0,00");
		lblNewLabel_14_1_3_1.setForeground(new Color(53, 132, 228));
		lblNewLabel_14_1_3_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_14_1_3_1.setBounds(200, 167, 70, 15);
		panel_16.add(lblNewLabel_14_1_3_1);

		JLabel lblNewLabel_14_1_3_1_1 = new JLabel("0,00");
		lblNewLabel_14_1_3_1_1.setForeground(new Color(224, 27, 36));
		lblNewLabel_14_1_3_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_14_1_3_1_1.setBounds(220, 190, 70, 15);
		panel_16.add(lblNewLabel_14_1_3_1_1);

		JLabel lblNewLabel_14_1_3_2 = new JLabel("SAUDO DEVEDOR  R$");
		lblNewLabel_14_1_3_2.setForeground(new Color(224, 27, 36));
		lblNewLabel_14_1_3_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_14_1_3_2.setBounds(50, 187, 170, 20);
		panel_16.add(lblNewLabel_14_1_3_2);
		panel_15.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_15.add(scrollPane_2);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
				new String[] { "S", "Item", "Sigla", "Descrição do exame", "Código AMB", "Data da Coleta", "Valor" }));
		scrollPane_2.setViewportView(table_1);
		panel_14.setLayout(null);

		JButton btnNewButton_5 = new JButton("Etiqueta");
		btnNewButton_5.setBounds(700, 3, 95, 25);
		panel_14.add(btnNewButton_5);
		panel_pagam.setLayout(gl_panel_pagam);

	}

	public void grava_pacienteDTO() {
		// envia identificação do formulario paciente para DTO
		set_dto.setNome(txt_nome.getText());
		set_dto.setSexo(txt_sexo.getSelectedItem().toString());
		set_dto.setDate(dataBanco);
		set_dto.setNome_mae(txt_mae.getText());
		set_dto.setCpf(txt_cpf.getText());
		set_dto.setRg(txt_rg.getText());
		set_dto.setCns(txt_cns.getText());
		set_dto.setCep(txt_cep.getText());
		set_dto.setEndereco(txt_endereco.getText());
		set_dto.setNumero(txt_numero.getText());
		set_dto.setCidade(txt_cidade.getText());
		set_dto.setCelular(txt_cel.getText());
		set_dto.setTelefone(txt_telefone.getText());
		set_dto.setEmail(txt_email.getText());
		set_dto.setObservacao(txt_obs.getText());

		if (txt_gesta == txt_gesta.getSelectedIcon()) {
			gestante = 1;
		} else {
			gestante = 0;
		}
		set_dto.setGesta(gestante);
		// fim paciente

		JOptionPane.showMessageDialog(null, "ok! Paciente DTO" + grava_pacient_banco.idUltimo + ": gravando");
		grava_pacient_banco = new DAO_paciente();
		try {
			grava_pacient_banco.inserir_paciente(set_dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// envia dado do paciente para DTO
		set_dado.setIdPaciente(grava_pacient_banco.idUltimo);
		set_dado.setCodConvenio(txt_codConvenio.getText());
		set_dado.setNumMatricula(txt_nMatric.getText());
		set_dado.setPlano(txt_plano.getText());
		set_dado.setValidadeCart(txt_validade.getText());
		set_dado.setPrioridade(prioridades());
		if (txt_preferencia.isSelected() == true) {
			preferencia = 1;
		} else {
			preferencia = 0;
		}

		set_dado.setPreferencia(preferencia);
		set_dado.setCodSolicita(txt_crm.getText());
		set_dado.setDescDoenca(txt_doeDesc.getText());
		set_dado.setComentario(txt_coment.getText());
		set_dado.setJejum(txt_jejum.getSelectedItem().toString());

		try {
			DAO_dadoPedido dados = new DAO_dadoPedido(set_dado);
			JOptionPane.showMessageDialog(null, "ok! Paciente DTO" + grava_pacient_banco.idUltimo + ": gravando");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public String prioridades() {
		if (txt_normal.isSelected() == true) {
			prioridade = txt_normal.getText();
		} else if (txt_urgente.isSelected() == true) {
			prioridade = txt_urgente.getText();
		} else if (txt_emergencia.isSelected()) {
			prioridade = txt_emergencia.getText();
		} else if (txt_imediata.isSelected() == true) {
			prioridade = txt_imediata.getText();
		}

		return prioridade;
	}

	public void addDadoTabela() {
		exameAtendimento_DTO add_exameDTO = new exameAtendimento_DTO();
		seq = model01.getRowCount()+1;
		String seq01 = Integer.toString(seq);
		String cod = "GLI";
		String desc = "GLICEMIA";
		String data = "10/10/2023";
		String valor = "10,20";
		String mat = "sim";
		Object[] obj = {seq01, cod, desc, data, valor, mat};
		
		model01.setValueAt(obj, 0,0); 
		
	}
	
	public void titleConvenio() {
		if(txt_convenio.getSelectedIndex()>0) {
			lbl_convenio.setText(txt_convenio.getSelectedItem().toString());
		}else {
			lbl_convenio.setText("");
		}
	}
	
	

}
