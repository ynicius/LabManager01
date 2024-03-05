package views.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import DAO_DBlabManager.conecta_DBlabmanager;

public class modelComobox extends DefaultComboBoxModel<String> {

	private List<String> lista = new ArrayList<String>();
	Statement stmt;
	ResultSet rs;
	int i = 0;	
	public String retorno;
	private String sql = null;
	private int params = 0;	
	String codigo;
	
	
	public modelComobox() {
		try {			
			loadData();

		} catch (Exception e) {
			System.out.printf("%s[%s]\n", getClass().getSimpleName(), e.toString());
		}
	}
	
	
	public List<String> loadData() {
		try {
		
			String query = "select * from db_labManager.nomes_tabelasValor;";

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			// System.out.println(rs.getRow());

			while (rs.next()) {

				lista.add(rs.getString(1));

			}
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "ERRO! Convenio: , NÃO GRAVADO," + e);
		}

		return lista;
	}	
	
	
	
}
