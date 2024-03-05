package teste.model.youtube;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class model_teste02 extends AbstractTableModel {
	/*
	 * Esse model, adiciona uma lista ao model da tabela
	 */

	private String[] colunas = new String[] { "SEQ.", "CÓDIGO", "DESCRIÇÃO" };
	public String[] dados = new String[] { "05", "GLI", "DESCRIÇÃO" };

	// definição da lista que será carregada no model da tabela
	private List<Object[]> linhas;

	public model_teste02() {
		linhas = new ArrayList<>();

		linhas.add(dados);
	}

	// adiciona uma lista a tabela
	public void addLinha() {
		linhas.add(dados);

		// código que atualiza o idex a ultima linha da tabela
		int ultimoIndice = getRowCount();
		fireTableRowsInserted(ultimoIndice, ultimoIndice);

	}

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
	public Object getValueAt(int rowIndex, int columnIndex) {
		/*
		 * Retorna o valor da célula em columnIndexe rowIndex. Parâmetros: rowIndex- a
		 * linha cujo valor deve ser consultado columnIndex- a coluna cujo valor será
		 * consultado Retorna: o valor Object na célula especificada
		 */
		try {
			Object l[] = linhas.get(rowIndex);
			return l[columnIndex];
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Erro na execução do metodo: getValueAt" + e);
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}

}
