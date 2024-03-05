package testes;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import views.util.limitaFormato;
import views.util.dateTimes;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class form_testeDiversos extends JFrame {

	private JPanel contentPane;
	private JTextField txt_mes;
	private JTextField txt_dia;
	private JTextField txt_ano;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form_testeDiversos frame = new form_testeDiversos();
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
	public form_testeDiversos() throws ParseException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		MaskFormatter mascaraCPF = new MaskFormatter("##/##/####");

		JFormattedTextField txt_dataEntra = new JFormattedTextField(mascaraCPF);
		txt_dataEntra.setBounds(21, 86, 97, 30);
		contentPane.add(txt_dataEntra);

		JEditorPane txt_data_sai = new JEditorPane();
		txt_data_sai.setBounds(130, 86, 107, 21);
		contentPane.add(txt_data_sai);

		JLabel txt_idade = new JLabel("idade");
		txt_idade.setBounds(21, 23, 200, 15);
		contentPane.add(txt_idade);

		JButton btnEnter = new JButton("Conveter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dateTimes txt_data_banco = new dateTimes(txt_dataEntra.getText());
				txt_data_sai.setText(txt_data_banco.conveter_data_string());

				txt_idade.setText(txt_data_banco.calcula_idade());

			}
		});
		btnEnter.setBounds(167, 185, 117, 25);
		contentPane.add(btnEnter);

		JButton btnNewButton = new JButton("validar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dateTimes validacao01 = new dateTimes(txt_dataEntra.getText());

				if (validacao01.valida_data()) {
					dateTimes txt_data_banco = new dateTimes(txt_dataEntra.getText());
					txt_data_sai.setText(txt_data_banco.conveter_data_string());
					txt_idade.setText(txt_data_banco.calcula_idade());
				} else {
					txt_dataEntra.setText("");
				}

			}
		});
		btnNewButton.setBounds(21, 160, 117, 25);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Anos");
		lblNewLabel.setBounds(259, 69, 46, 15);
		contentPane.add(lblNewLabel);

		JLabel lblMes = new JLabel("Meses");
		lblMes.setBounds(317, 69, 46, 15);
		contentPane.add(lblMes);

		JLabel lblDias = new JLabel("dias");
		lblDias.setBounds(392, 69, 46, 15);
		contentPane.add(lblDias);

		txt_mes = new JTextField();
		txt_mes.setText("0");
		txt_mes.setColumns(2);
		txt_mes.setBounds(323, 86, 40, 30);
		contentPane.add(txt_mes);

		txt_dia = new JTextField();
		txt_dia.setText("0");
		txt_dia.setColumns(3);
		txt_dia.setBounds(392, 86, 40, 30);
		contentPane.add(txt_dia);

		JButton btnDtnasc = new JButton("dtNasc");
		btnDtnasc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String anos;
				anos = txt_ano.getText();				
				dateTimes data_nasc = new dateTimes();

				txt_dataEntra.setText(data_nasc.calc_dataNacimento(anos));

			}
		});
		btnDtnasc.setBounds(317, 128, 89, 25);
		contentPane.add(btnDtnasc);
		
		txt_ano = new JTextField();
		txt_ano.setText("0");
		txt_ano.setColumns(2);
		txt_ano.setBounds(260, 86, 40, 30);
		contentPane.add(txt_ano);

	}
}
