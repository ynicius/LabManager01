package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.image.DataBufferFloat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Font;

public class form01 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form01 frame = new form01();
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
	public form01() {
		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 0, 0);
		//define o tamanho do form
		setSize(1000, 600);
		//define o tamanho minimo do form
		setMinimumSize(new Dimension(600, 400));		
		contentPane = new JPanel();		
		//setResizable(false); impede o ajuste do tamanho do form
		
		//centraliza o form na tela
		setLocationRelativeTo(null);
		
		//Maximiza o form no tamanho maximo da tela
		//setExtendedState(MAXIMIZED_BOTH);
		
		setTitle("SIS LAB-MANAGE");
		
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setPreferredSize(new Dimension(400,400));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_menus = new JPanel();
		panel_menus.setBackground(new Color(153, 193, 241));
		contentPane.add(panel_menus, BorderLayout.NORTH);
		panel_menus.setPreferredSize(new Dimension(10,54));
		panel_menus.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_menus.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel_2.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_5 = new JMenu("New menu");
		mnNewMenu.add(mnNewMenu_5);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_6 = new JMenu("New menu");
		mnNewMenu_1.add(mnNewMenu_6);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_7 = new JMenu("New menu");
		mnNewMenu_2.add(mnNewMenu_7);
		
		JPanel panel_3 = new JPanel();
		panel_menus.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JMenuBar menuBar_1 = new JMenuBar();
		panel_3.add(menuBar_1);
		
		JMenu mnNewMenu_3 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_4);

		JPanel panel_rodaPe = new JPanel();
		panel_rodaPe.setBackground(new Color(153, 193, 241));
		panel_rodaPe.setPreferredSize(new Dimension(10, 27));

		JLabel lblNewLabel = new JLabel("New label");

		JLabel lblNewLabel_1 = new JLabel("New label");

		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_rodaPe.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 5));//define a possição das label no rodapé
		panel_rodaPe.add(lblNewLabel);
		panel_rodaPe.add(lblNewLabel_1);
		panel_rodaPe.add(lblNewLabel_2);
		contentPane.add(panel_rodaPe, BorderLayout.SOUTH);
		
				
		
		
		JPanel panel_dir = new JPanel();
		contentPane.add(panel_dir, BorderLayout.EAST);
		panel_dir.setPreferredSize(new Dimension(400, 200));
		panel_dir.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_dir.add(panel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Novo Atendimento");
		lblNewLabel_6.setFont(new Font("Nimbus Roman", Font.BOLD, 16));
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap(160, Short.MAX_VALUE)
					.addComponent(lblNewLabel_6)
					.addGap(100))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addComponent(lblNewLabel_6)
					.addGap(28))
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_dir.add(panel_8);
		
		JLabel lblNewLabel_7 = new JLabel("Consultar Paciente");
		lblNewLabel_7.setFont(new Font("Nimbus Roman", Font.BOLD, 16));
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap(160, Short.MAX_VALUE)
					.addComponent(lblNewLabel_7)
					.addGap(100))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addComponent(lblNewLabel_7)
					.addGap(29))
		);
		panel_8.setLayout(gl_panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_dir.add(panel_9);
		
		JLabel lblNewLabel_8 = new JLabel("Central de Exames");
		lblNewLabel_8.setFont(new Font("Nimbus Roman", Font.BOLD, 16));
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap(160, Short.MAX_VALUE)
					.addComponent(lblNewLabel_8)
					.addGap(100))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap(55, Short.MAX_VALUE)
					.addComponent(lblNewLabel_8)
					.addGap(24))
		);
		panel_9.setLayout(gl_panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_dir.add(panel_10);
		
		JLabel lblNewLabel_9 = new JLabel("Relatorios");
		lblNewLabel_9.setFont(new Font("Nimbus Roman", Font.BOLD, 16));
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(165)
					.addComponent(lblNewLabel_9)
					.addContainerGap(100, Short.MAX_VALUE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addComponent(lblNewLabel_9)
					.addGap(27))
		);
		panel_10.setLayout(gl_panel_10);
		
		JPanel panel_6 = new JPanel();
		panel_dir.add(panel_6);
		
		JLabel lblNewLabel_10 = new JLabel("Administrar Usuários");
		lblNewLabel_10.setFont(new Font("Nimbus Roman", Font.BOLD, 16));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(160)
					.addComponent(lblNewLabel_10)
					.addContainerGap(100, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addComponent(lblNewLabel_10)
					.addGap(28))
		);
		panel_6.setLayout(gl_panel_6);
		
		JPanel panel_esq = new JPanel();
		contentPane.add(panel_esq, BorderLayout.WEST);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("/home/vinicius/eclipse-workspace/LabManager/imag/img-01.png"));
		GroupLayout gl_panel_esq = new GroupLayout(panel_esq);
		gl_panel_esq.setHorizontalGroup(
			gl_panel_esq.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_esq.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_esq.setVerticalGroup(
			gl_panel_esq.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_esq.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(5))
		);
		panel_esq.setLayout(gl_panel_esq);
		//panel_4.setPreferredSize(new Dimension(300,10));
		

	}
}
