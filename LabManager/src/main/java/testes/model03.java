package testes;

import javax.swing.table.AbstractTableModel;

import com.mysql.cj.protocol.Resultset;

import DAO_DBlabManager.conecta_DBlabmanager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class model03 extends AbstractTableModel {
	private ArrayList<Object[]> linhas = new ArrayList<>();
	private String [] colunas;
	private Statement stmt;
	private String query;
	
	
	//construtor
	public model03 (String query ) {
		try {
			this.query = query;
			stmt = conecta_DBlabmanager.getInstance().getConnection().createStatement();
			loadData();
			
		} catch (Exception e) {
			System.out.printf("%s[%s]\n", getClass().getSimpleName(), e.toString());
		}
		
	}
	
	//carrega dados do BD no modelo
	public void loadData() {
		System.out.println("P1017QueryTableModel[loadData()]");
		try(ResultSet rs = stmt.executeQuery(query);) {
			ResultSetMetaData rsmd = rs.getMetaData(); // confirmar parametrização classe "ResultSetMetaData"
			int numCols = rsmd.getColumnCount();
			colunas = new String [numCols];
			for (int i=0; i < numCols; i++) {
				colunas[i] = rsmd.getColumnLabel(i+1);// definição dos nomes das colunas 
				
			}
			linhas.clear();
			while(rs.next()) { // percorre cada linha do rs com dados do banco
				Object l[] = new Object[numCols]; //instancia o array l[] com tatas coluna necessaria para receber os dados de rs
				for (int i = 0; i < numCols; i++) { // percorre cada unidade do arranjo objct l[],
						l[i] = rs.getObject(i+1);  //adiciona a cada unidade do array l[] e adiciona dados do rs 
				}
				linhas.add(l);
			}
			fireTableDataChanged();
			
		} catch (Exception e) {
			System.out.printf("%s[%s]\n", getClass().getSimpleName(), e.toString());
		}
		
	}


	//metodos de implementação obrigadtoria AbstractTableModel
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return linhas.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public Object getValueAt(int lin, int col) {
		try {
			
			Object l[] = linhas.get(lin);
			return l[col];
			
		} catch (Exception e) {
			System.out.printf("%s[%s]\n", getClass().getSimpleName(), e.toString());			
		}
		
		
		return null;
	}
	
	// Metodos de implementacao opcional
		@Override
		public String getColumnName(int col) {
			return colunas[col];
		}  

		@Override
		public boolean isCellEditable(int lin, int col) {
			return true;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}

		@Override
		public void setValueAt(Object value, int lin, int col) {
			Object l[] = linhas.get(lin);
			l[col] = value;
			fireTableCellUpdated(lin, col);
		}

}
