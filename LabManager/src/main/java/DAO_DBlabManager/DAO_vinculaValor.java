package DAO_DBlabManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import DTO.exameProcedimento_DTO;
import DTO.tabelaCobranca_DTO;
import DTO.vinculaValores_DTO;

public class DAO_vinculaValor {
	ResultSet rs;
	Statement stmt;
	public static String idUltimo;
	public String retorno;

	private String sql = null;
	private String sql1 = null;
	private int params = 0;
	String codigo;
	public int id;

	public void vincula_valor(vinculaValores_DTO dtoVincula) {

		try {

			Properties prop = getProp();

			sql = prop.getProperty("DataInsert.sql");
			params = Integer.parseInt(prop.getProperty("DataInsert.params"));

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();

			// criar comando preparado
			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setInt(1, dtoVincula.getIdExame());
			pstm.setInt(2, dtoVincula.getIdValor());

			// executa comando
			pstm.executeUpdate();
			// encerra comando preparado
			pstm.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Valor vinculado ao exame");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! Valor não vinculado:NÃO GRAVADO," + e);
		}

	}

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/vincula.properties");
		props.load(file);
		return props;

	}

	public void deleteVinculo(vinculaValores_DTO dtoID) {

		try {
			Properties prop = getProp();

			sql = prop.getProperty("DataInsert.sql1");
			params = Integer.parseInt(prop.getProperty("DataInsert.params"));

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();

			// criar comando preparado
			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setInt(1, dtoID.getId());

			// executa comando
			pstm.executeUpdate();
			// encerra comando preparado
			pstm.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Valor desviculado");
		} catch (

		Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! Valor não vinculado:NÃO GRAVADO," + e);
		}
	}

	public boolean buscaIdvalor(vinculaValores_DTO dtoIdValor) {
		try {
			int idVal = dtoIdValor.getIdValor();
			int idExa = dtoIdValor.getIdExame();

			sql = "select id_convenioCobranca from cobrancaExames where id_convenioCobranca =" + idVal
					+ " and id_exameProce = " + idExa + ";";

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();

			// executa comando
			rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst())
				return true;

			// encerra comando preparado
			rs.close();
			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Valor consultado");
		} catch (

		Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! Valor não vinculado:NÃO GRAVADO," + e);
			return false;
		}
		return false;
	}

	public boolean buscaExaTable(vinculaValores_DTO dtoIdValor, tabelaCobranca_DTO dtoTabela) {
		try {
			int idExa = dtoIdValor.getIdExame();
			String tabela = dtoTabela.getNomeTabela();

			sql = "select exameProcedimento.codigo, exameProcedimento.descExame, tabelas_Cobranca.valor, tabelas_Cobranca.nomeDescricao,\n"
					+ " tabelas_Cobranca.nomeTabela, cobrancaExames.idcobrancaExames\n" + " from exameProcedimento \n"
					+ " left join cobrancaExames\n"
					+ " on exameProcedimento.id_exameProce = cobrancaExames.id_exameProce\n"
					+ " left join tabelas_Cobranca\n"
					+ " on cobrancaExames.id_convenioCobranca = tabelas_Cobranca.id_convenioCobranca\n"
					+ " where cobrancaExames.id_exameProce = '" + idExa + "'\n" + " and tabelas_Cobranca.nomeTabela = '"
					+ tabela + "';";

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();

			// executa comando
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				id = Integer.parseInt(rs.getString(6));
				
				return true;
			}

			// encerra comando preparado
			rs.close();
			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Valor consultado");
		} catch (

		Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! Valor não vinculado:NÃO GRAVADO," + e);
			return false;
		}
		return false;
	}

	public void updateCobranca(vinculaValores_DTO dtoVincula) {
		try {
			
			Properties prop = getProp();
			sql = prop.getProperty("DataInsert.sql3");
			params = Integer.parseInt(prop.getProperty("DataInsert.params"));				

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, dtoVincula.getIdValor() );
			pstm.setInt(2, dtoVincula.getId());
			System.out.println(dtoVincula.getId() +"-"+ dtoVincula.getIdValor());
			
			pstm.executeUpdate();
			pstm.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Valor Atualizado");
		} catch (

		Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! Valor não vinculado: NÃO Atualizado," + e);

		}

	}

}
