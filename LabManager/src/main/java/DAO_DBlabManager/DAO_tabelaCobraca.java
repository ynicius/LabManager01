package DAO_DBlabManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

import DTO.convenio_DTO;
import DTO.exameProcedimento_DTO;
import DTO.tabelaCobranca_DTO;

public class DAO_tabelaCobraca {

	private List<String> lista = new ArrayList<String>();
	// private String[] lista ={"fadf", "dfadf"};
	Statement stmt;
	Statement stmt1;
	ResultSet rs;
	int i = 2;

	public String retorno;
	private String sql = null;
	private String sql1 = null;
	private int params = 0;
	String codigo;

	public List<String> listConvenio() {

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

	public void gravaNomeTabela(tabelaCobranca_DTO nome_dto) {
		try {
			Properties prop = getProp();
			Properties prop2 = getProp();

			sql = prop.getProperty("DataInsert.sql");
			sql1 = prop2.getProperty("DataInsert.sql1");
			params = Integer.parseInt(prop.getProperty("DataInsert.params"));

			Connection con = conecta_DBlabmanager.getInstance().getConnection();

			stmt = con.createStatement();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, nome_dto.getNomeTabela());

			stmt1 = con.createStatement();
			PreparedStatement pstm1 = con.prepareStatement(sql1);
			pstm1.setString(1, nome_dto.getNomeTabela());

			// executa comando
			pstm.executeUpdate();
			for (int x = 0; x < i; x++) {
				pstm1.executeUpdate();
			}

			// encerra comando preparado
			pstm.close();
			pstm1.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Tabela: , adicionado ao Banco");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao iniciar construtor url de conexão: " + e);
		}
		// return con;
	}

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/tableCobranca.properties");
		props.load(file);
		return props;

	}

	public boolean verificar_nomeTabela(tabelaCobranca_DTO codigo_consulta) {

		try {

			codigo = codigo_consulta.getNomeTabela();
			String query = "select * from nomes_tabelasValor where nomeTabelas = '" + codigo + "';";

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			// verifica no resultSet se o código digitado ja existe no banco
			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "ERRO ao consultar codigo:" + codigo +
			// "dfas" + e);
			return false;
		}

	}

	public void gravaTabela(tabelaCobranca_DTO tabela) {

		try {
			Properties prop = getProp();

			sql = prop.getProperty("DataUpdate.sql2");
			params = Integer.parseInt(prop.getProperty("DataInsert.params"));

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, tabela.getCodigo());
			pstm.setString(2, tabela.getNomeDesc());
			pstm.setString(3, tabela.getValor());
			pstm.setInt(4, tabela.getId_cobranca());

			// executa query de atualização
			pstm.executeUpdate();
			pstm.close();
			System.out.println("gravado");

			conecta_DBlabmanager.getInstance().shutdow();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO ao atualizar codigo:" + tabela.getNomeDesc() + "erro: " + e);
		}

	}

	public void addLinha(tabelaCobranca_DTO nome_dto) {
		try {

			Properties prop2 = getProp();

			sql1 = prop2.getProperty("DataInsert.sql1");
			params = Integer.parseInt(prop2.getProperty("DataInsert.params"));

			Connection con = conecta_DBlabmanager.getInstance().getConnection();

			stmt1 = con.createStatement();
			PreparedStatement pstm1 = con.prepareStatement(sql1);
			pstm1.setString(1, nome_dto.getNomeTabela());

			// executa comando

			pstm1.executeUpdate();

			// encerra comando preparado
			pstm1.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao iniciar construtor url de conexão: " + e);
		}
	}

}
