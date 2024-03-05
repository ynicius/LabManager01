package views.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import DTO.exameAtendimento_DTO;
import teste.model.youtube.Funcionario;

public class model_exameAtendimento extends AbstractTableModel {
	private List<exameAtendimento_DTO> linhas;
	private String[] colunas = new String[] { "CODIGO", "DESCRIÇÃO" };

	public model_exameAtendimento() {
		linhas = new ArrayList<exameAtendimento_DTO>();
	}

	public model_exameAtendimento(List<exameAtendimento_DTO> lista) {
		linhas = new ArrayList<exameAtendimento_DTO>(lista);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		/*
		 * switch(columnIndex){ case 0: return Integer.class; default: return
		 * String.class; }
		 */
		return String.class;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		exameAtendimento_DTO f = linhas.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return f.getCodigo();
		case 1:
			return f.getDescExame();

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

		// return null;
	}

	@Override
	// modifica na linha e coluna especificada
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		exameAtendimento_DTO f = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado

		switch (columnIndex) { // Seta o valor do campo respectivo
		case 0:
			f.setCodigo(aValue.toString());
			break;
		case 1:
			f.setDescExame(aValue.toString());
			break;

		default:
			// Isto não deveria acontecer...
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	// modifica na linha especificada
	public void setValueAt(exameAtendimento_DTO aValue, int rowIndex) {
		exameAtendimento_DTO f = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado

		f.setCodigo(aValue.getCodigo());
		f.setDescExame(aValue.getDescExame());
		//f.setCPF(aValue.getCPF());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		//fireTableCellUpdated(rowIndex, 2);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public exameAtendimento_DTO getFuncionario(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	public void addExames(exameAtendimento_DTO f) {
		// Adiciona o registro.
		linhas.add(f);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	/* Remove a linha especificada. */
	public void remove(int indiceLinha) {
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	/* Adiciona uma lista de Cliente ao final dos registros. */
	public void addLista(List<exameAtendimento_DTO> f) {
		// Pega o tamanho antigo da tabela.
		int tamanhoAntigo = getRowCount();

		// Adiciona os registros.
		linhas.addAll(f);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	/* Remove todos os registros. */
	public void limpar() {
		linhas.clear();
		fireTableDataChanged();
	}

	/* Verifica se este table model esta vazio. */
	public boolean isEmpty() {
		return linhas.isEmpty();
	}
}
