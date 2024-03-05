package teste.model.youtube;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class model_teste01 extends AbstractTableModel {

	private String[] colunas = new String[] { "SEQ.", "CÓASDFASDFDIGO", "DESCRIÇÃO" };
	private List<Funcionario> linhas;

	public model_teste01(List<Funcionario> lista) {

		linhas = new ArrayList<Funcionario>(lista);

	}

	public model_teste01() {

		linhas = new ArrayList<Funcionario>();

	}

	public void addLinhaxxx() {

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
		Funcionario f = linhas.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return f.getMatricula();
		case 1:
			return f.getNome();
		case 2:
			return f.getCPF();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}

	public void addFuncionario(Funcionario f) {
		// Adiciona o registro.
		linhas.add(f);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

}
