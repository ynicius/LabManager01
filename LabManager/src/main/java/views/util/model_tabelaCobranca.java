package views.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAO_DBlabManager.conecta_DBlabmanager;
import DTO.exameProcedimento_DTO;
import DTO.tabelaCobranca_DTO;

public class model_tabelaCobranca extends AbstractTableModel {

	private ArrayList<Object[]> linhas = new ArrayList<>();
	private String[] colunas = new String[] {"ID", "CÓDIGO", "NOME", "VALOR(R$)"};
	private Statement stmt;
	private String tabela;
	private String query = "select id_convenioCobranca, codigo, nomeDescricao, valor from db_labManager.tabelas_Cobranca where nomeTabela ='" +tabela+"'";	
	tabelaCobranca_DTO tabela_consulta;
	int numCols;	
	

	// construtor
	public model_tabelaCobranca() {
		try {
			this.query = query;
			stmt = conecta_DBlabmanager.getInstance().getConnection().createStatement();
			loadData();

		} catch (Exception e) {
			System.out.printf("%s[%s]\n", getClass().getSimpleName(), e.toString());
		}
	}

	

	// carrega dados do BD no modelo
	public void loadData() {
		// faz um select geral, carrega todos os dado no model
		
		
		try (ResultSet rs = stmt.executeQuery(query);) {
			ResultSetMetaData rsmd = rs.getMetaData(); // confirmar parametrização classe "ResultSetMetaData"
			int numCols = colunas.length;
			
			
			linhas.clear();
			while (rs.next()) { // percorre cada linha do rs com dados do banco
				Object l[] = new Object[numCols]; // instancia o array l[] com tatas coluna necessaria para receber os
													// dados de rs
				for (int i = 0; i < numCols; i++) { // percorre cada unidade do arranjo objct l[],

					if (rs.getObject(i + 1) == null) {
						l[i] = "";
					} else {
						l[i] = rs.getObject(i + 1); // adiciona a cada unidade do array l[] e adiciona dados do rs
					}

				}
				linhas.add(l);
			}
			fireTableDataChanged();

		} catch (Exception e) {
			System.out.printf("%s[%s]\n.ERRO.", getClass().getSimpleName(), e.toString());
		}

	}
	
	public void buscaTabela(tabelaCobranca_DTO tab) {
		// faz um select geral, carrega todos os dado no model
		this.tabela = tab.getNomeTabela();
		String query1 = "select id_convenioCobranca, codigo, nomeDescricao, valor from db_labManager.tabelas_Cobranca where nomeTabela ='" +tabela+"'";
		
		try (ResultSet rs = stmt.executeQuery(query1);) {
			ResultSetMetaData rsmd = rs.getMetaData(); // confirmar parametrização classe "ResultSetMetaData"
			int numCols = colunas.length;
			
			
			linhas.clear();
			while (rs.next()) { // percorre cada linha do rs com dados do banco
				Object l[] = new Object[numCols]; // instancia o array l[] com tatas coluna necessaria para receber os
													// dados de rs
				for (int i = 0; i < numCols; i++) { // percorre cada unidade do arranjo objct l[],

					if (rs.getObject(i + 1) == null) {
						l[i] = "";
					} else {
						l[i] = rs.getObject(i + 1); // adiciona a cada unidade do array l[] e adiciona dados do rs
					}

				}
				linhas.add(l);
			}
			fireTableDataChanged();

		} catch (Exception e) {
			System.out.printf("%s[%s]\n.ERRO.", getClass().getSimpleName(), e.toString());
		}

	}

	public void buscaExames(exameProcedimento_DTO codigo_consulta) {
		// faz um select geral, carrega todos os dado no model
		String exameConsulta = codigo_consulta.getProcurar();

		String query2 = "select codigo as 'CODIGO', descExame as 'DESCRIÇÃO EXAME', tipoMaterial as 'MATERIAL', tempoResultado as 'PRAZO',"
				+ "setorGrupo as 'SETOR', executaProcessa as 'REALIZA', labExec as 'LOCAL', DATE_FORMAT(data_criacao, '%d/%m/%Y' ' %H:%i:%s') as 'CRIAÇÃO',"
				+ "data_atualizacao as 'ATUALIZACAO' from exameProcedimento where codigo like '" + exameConsulta
				+ "%' OR descExame like'" + exameConsulta + "%';";

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
		}

		// linhas.clear();
		// linhas.add(colunas);

		fireTableDataChanged();

	}

	// metodos de implementação obrigadtoria AbstractTableModel
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		//int l = 10 + linhas.size();
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
