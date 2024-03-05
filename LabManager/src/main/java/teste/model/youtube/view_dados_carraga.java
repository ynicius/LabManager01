package teste.model.youtube;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class view_dados_carraga extends JFrame {

	private JPanel contentPane;
	private JTextField c_mat;
	private JTextField c_nome;
	private JTextField txt_cpf;
	JTable tbl_funcionarios;
	model_teste01 model;
	model_teste02 model2;
	private JLabel txt_lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_dados_carraga frame = new view_dados_carraga();
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
	public view_dados_carraga() {

		model = new model_teste01();
		model2 = new model_teste02();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel c_cpf = new JPanel();
		c_cpf.setBounds(1, 1, 271, 234);
		panel.add(c_cpf);
		c_cpf.setLayout(null);

		c_mat = new JTextField();
		c_mat.setBounds(23, 73, 114, 19);
		c_cpf.add(c_mat);
		c_mat.setColumns(10);

		c_nome = new JTextField();
		c_nome.setBounds(23, 104, 114, 19);
		c_cpf.add(c_nome);
		c_nome.setColumns(10);

		txt_cpf = new JTextField();
		txt_cpf.setColumns(10);
		txt_cpf.setBounds(23, 135, 114, 19);
		c_cpf.add(txt_cpf);

		tbl_funcionarios = new javax.swing.JTable(model);

		JPanel panel_tabela = new JPanel();
		panel_tabela.add(new JScrollPane(tbl_funcionarios), "Center");
		panel_tabela.setBounds(279, 12, 499, 440);
		panel.add(panel_tabela);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model2.addLinha();
				txt_lbl.setText(c_mat.getText());
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario F = new Funcionario();// cria a instancia de dados

				// envia as informações para classe de dados
				F.setMatricula(c_mat.getText());
				F.setNome(c_nome.getText());
				F.setCPF(txt_cpf.getText());

				model.addFuncionario(F);

			}
		});
		btnNewButton.setBounds(42, 273, 117, 25);
		panel.add(btnNewButton);

		txt_lbl = new JLabel("New label");
		txt_lbl.setBounds(119, 365, 70, 15);
		panel.add(txt_lbl);
	}
}
