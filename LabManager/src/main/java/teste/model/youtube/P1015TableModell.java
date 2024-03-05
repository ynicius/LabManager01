package teste.model.youtube;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class P1015TableModell extends AbstractTableModel {

	/*private Object[][] dados = {
			{ "He", "Hélio", Integer.valueOf(2), Double.valueOf(4.0026), Double.valueOf(0.179), Boolean.TRUE },
			{ "Ne", "Neônio", Integer.valueOf(10), Double.valueOf(20.17), Double.valueOf(0.90), Boolean.TRUE },
			{ "Ar", "Argônio", Integer.valueOf(18), Double.valueOf(39.94), Double.valueOf(1.78), Boolean.TRUE },
			{ "Kr", "Criptônio", Integer.valueOf(36), Double.valueOf(83.80), Double.valueOf(3.7), Boolean.TRUE },
			{ "Xe", "Xenônio", Integer.valueOf(54), Double.valueOf(131.30), Double.valueOf(5.85), Boolean.TRUE },
			{ "Rd", "Radônio", Integer.valueOf(86), Double.valueOf(222), Double.valueOf(9.73), Boolean.TRUE } };*/

	private String [] colunas = {"Simb","Nome","NAtom","M atom","M esp","Nobre"};

	private List<Object[]> dados = new ArrayList ();
	
	
	public P1015TableModell() {
		dados.add(colunas);
	}
	
	//Métodos de implemetação obrigatoria 
	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public int getColumnCount() {
		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int lin, int col) {
		
		try {
			Object l[] = dados.get(lin);
			return l[col];
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Erro na execução do metodo: getValueAt" + e);
		}
		return null;
	}
	
	// Métodos de implementação opcional
		@Override
		public String getColumnName(int col) {
			return colunas[col];
		}

		@Override
		public boolean isCellEditable(int lin, int col) {
			return false;
		}

		/*@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Class getColumnClass(int col) {
			return dados[0][col].getClass();
		}*/

}
