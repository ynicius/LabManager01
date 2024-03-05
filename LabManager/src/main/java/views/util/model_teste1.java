package views.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import DTO.exameAtendimento_DTO;

public class model_teste1 extends AbstractTableModel {

	private String[] colunas = new String[] { "SEQ.", "CÓDIGO", "DESCRIÇÃO DO EXAME", "DATA DA COLETA", " A PAGAR", "M." };
	private List<exameAtendimento_DTO> linhas;

	public model_teste1(List<exameAtendimento_DTO> lista) {

		linhas = new ArrayList<exameAtendimento_DTO>(lista);

	}

	public model_teste1() {

		linhas = new ArrayList<exameAtendimento_DTO>();

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
		exameAtendimento_DTO f = linhas.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return f.getSeq();
		case 1:
			return f.getCodigo();
		case 2:
			return f.getDescExame();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}

	public void addExameModel(exameAtendimento_DTO f) {
		// Adiciona o registro.
		linhas.add(f);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

}
