package testes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class teste_scroll extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JTable table;
	private JLabel txt_label;
	SizeX xx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teste_scroll frame = new teste_scroll();
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
	public teste_scroll() {
		// xx = (SizeX) new JScrollPane();
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setSize(500, 400);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		table.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column",
						"New column", "New column" }));
		table.getTableHeader().setReorderingAllowed(false);
		
		
		if (table.getColumnModel().getColumnCount() > 0) {
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(4).setPreferredWidth(50);
			table.getColumnModel().getColumn(5).setPreferredWidth(50);
			table.getColumnModel().getColumn(6).setPreferredWidth(150);
			table.getColumnModel().getColumn(7).setPreferredWidth(200);
		}

		scrollPane.setViewportView(table);

		txt_label = new JLabel("New label");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(2)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(txt_label).addGap(18)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(scrollPane,
						GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup().addContainerGap(199, Short.MAX_VALUE)
						.addComponent(txt_label).addGap(139)));
		panel.setLayout(gl_panel);

		System.out.println("teste");
		
		
		
	}
	
	
	
	

	public void larguraCol() {
		int i1 = table.getColumnModel().getColumn(0).getPreferredWidth();
		int i2 = table.getColumnModel().getColumn(1).getPreferredWidth();
		int i3 = table.getColumnModel().getColumn(2).getPreferredWidth();
		int i4 = table.getColumnModel().getColumn(3).getPreferredWidth();
		int i5 = table.getColumnModel().getColumn(4).getPreferredWidth();
		int i6 = table.getColumnModel().getColumn(5).getPreferredWidth();
		int i7 = table.getColumnModel().getColumn(6).getPreferredWidth();
		int i8 = table.getColumnModel().getColumn(6).getPreferredWidth();
		int total = i1 + i2 +i3+i4+i5+i6+i7+i8;
		
		int j = table.getColumnModel().getColumn(6).getPreferredWidth();
		
		txt_label.setText(Integer.toString(total));
		
		if(i8 > 210) {
			table.getColumnModel().getColumn(6).setPreferredWidth(i6++);
		}else {
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		
		
	}
}
