package views.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAO_DBlabManager.conecta_DBlabmanager;
import DTO.tabelaCobranca_DTO;

public class model_valor2 extends AbstractTableModel {
	private ArrayList<Object[]> linhas = new ArrayList<>();
	private String[] colunas;
	private Statement stmt;
	private String tabela;
	private String query;
	tabelaCobranca_DTO tabela_consulta;
	int numCols;

	public model_valor2(String x) {
		try {
			colunas = new String[] { "CODIGO", "DESCRIÇÃO", "VALOR", "NOME COBRANCA", "TABELA", "ID" };
			this.query = query;
			stmt = conecta_DBlabmanager.getInstance().getConnection().createStatement();
			loadData(x);

		} catch (Exception e) {
			System.out.printf("%s[%s]\n", getClass().getSimpleName(), e.toString());
		}
	}


	
	

	public void loadData(String x) {
		// faz um select geral, carrega todos os dado no model
		
		String y = x;

		String query2 = "select exameProcedimento.codigo, exameProcedimento.descExame, tabelas_Cobranca.valor,\n"
				+ " tabelas_Cobranca.nomeDescricao, tabelas_Cobranca.nomeTabela, cobrancaExames.idcobrancaExames\n"
				+ " from exameProcedimento \n"
				+ " left join cobrancaExames\n"
				+ " on exameProcedimento.id_exameProce = cobrancaExames.id_exameProce\n"
				+ " left join tabelas_Cobranca\n"
				+ " on cobrancaExames.id_convenioCobranca = tabelas_Cobranca.id_convenioCobranca\n"
				+ " where exameProcedimento.codigo = '"+y+"';";
		

		try (ResultSet rs = stmt.executeQuery(query2)) {

			linhas.clear();
			ResultSetMetaData rsmd = rs.getMetaData(); // confirmar parametrização classe "ResultSetMetaData"
			int numCols = rsmd.getColumnCount();
			while (rs.next()) { // percorre cada linha do rs com dados do banco
				Object l[] = new Object[numCols]; // instancia o array l[] com tatas coluna necessaria para receber os
													// dados de rs
				for (int i = 0; i < numCols; i++) { // percorre cada unidade do arranjo objct l[],

					if (rs.getObject(i + 1) == null) {
						l[i] = "***";
					} else {
						l[i] = rs.getObject(i + 1); // adiciona a cada unidade do array l[] e adiciona dados do rs
					}

				}
				linhas.add(l);

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		// linhas.clear();
		// linhas.add(colunas);

		fireTableDataChanged();

	}

	// metodos de implementação obrigadtoria AbstractTableModel
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		// int l = 10 + linhas.size();
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
			// o objeto recebe uma instacia de linhas
			Object l[] = linhas.get(lin);
			// retorna a celula ferente a coluna especificada
			return l[col];

		} catch (Exception e) {
			System.out.printf("%s[%s]\nEROO", getClass().getSimpleName(), e.toString());
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

	public void remove(int indiceLinha) {
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}
