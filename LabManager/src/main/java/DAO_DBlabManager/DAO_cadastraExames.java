package DAO_DBlabManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.exameProcedimento_DTO;
import DTO.paciente_DTO;

public class DAO_cadastraExames {

	ResultSet rs;
	Statement stmt; 
	public static String idUltimo;
	String codigo;
	public String retorno;

	public void inserir_exame(exameProcedimento_DTO exame_dto) {

		try {

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();

			// criar comando preparado
			String sql = "insert into exameProcedimento (codigo, descExame, tipoMaterial, tempoResultado, setorGrupo, executaProcessa,"
					+ " labExec, data_atualizacao )\n" + "values\n" + "(?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, exame_dto.getCodigo());
			pstmt.setString(2, exame_dto.getDescExame());
			pstmt.setString(3, exame_dto.getTipoMaterial());
			pstmt.setInt(4, exame_dto.getPrazo());
			pstmt.setString(5, exame_dto.getSetorGrupo());
			pstmt.setString(6, exame_dto.getExecutaProcessa());
			pstmt.setString(7, exame_dto.getLabExec());
			pstmt.setString(8, exame_dto.getDateAtualiza());

			// executa comando
			pstmt.executeUpdate();
			// encerra comando preparado
			pstmt.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Paciente: " + exame_dto.getDescExame() + ", adicionado ao Banco");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! Paciente: , NÃO GRAVADO," + e);
		}

	}

	public boolean verificar_codigoExame(exameProcedimento_DTO codigo_consulta) {

		try {

			codigo = codigo_consulta.getCodigo();
			String query = "select codigo from exameProcedimento where codigo = '" + codigo + "';";

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			// verifica no resultSet se o código digitado ja existe no banco
			if (rs.isBeforeFirst())

				return true;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "ERRO ao consultar codigo:" + codigo +
			// "dfas" + e);
			return false;
		}
		return false;

	}

	public void apagarExame(String codApagar) {
		try {

			String query = "delete from exameProcedimento where codigo = ?;";
			Connection con = conecta_DBlabmanager.getInstance().getConnection();

			PreparedStatement pstmt = con.prepareStatement(query); 

			pstmt.setString(1, codApagar);
			pstmt.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO ao apagar codigo:" + codigo + "erro: " + e);

		}

	}

	public void atualizarExame(exameProcedimento_DTO dto) {
		try {

			String query = "update exameProcedimento\n" + "set\n" + "codigo =? ,\n" + "descExame =? ,\n"
					+ "tipoMaterial =? ,\n" + "tempoResultado=? ,\n" + "setorGrupo = ?,\n" + "executaProcessa = ?,\n"
					+ "labExec= ?,\n" + "data_atualizacao =? \n" + "where codigo = ?;";
			Connection con = conecta_DBlabmanager.getInstance().getConnection();

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, dto.getCodigo());
			pstmt.setString(2, dto.getDescExame());
			pstmt.setString(3, dto.getTipoMaterial());
			pstmt.setInt(4, dto.getPrazo());
			pstmt.setString(5, dto.getSetorGrupo());
			pstmt.setString(6, dto.getExecutaProcessa());
			pstmt.setString(7, dto.getLabExec());
			pstmt.setString(8, dto.getDateAtualiza());
			pstmt.setString(9, dto.getCodAtualiza());

			pstmt.executeUpdate();
			pstmt.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão
			JOptionPane.showMessageDialog(null, "ok! Exame Codigo: " + dto.getCodAtualiza() + ", atualizado");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO ao apagar codigo:" + codigo + "erro: " + e);

		}
	}

	public Boolean verificaAtualiza(exameProcedimento_DTO codigo) {
		try {
			// código digitado
			String cod = codigo.getCodigo();
			
			// código carredo do model, não varia
			String codModel = codigo.getProcurar();
			String query = "select codigo from exameProcedimento where codigo like '" + cod + "';";

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				String value = rs.getString(1);
				if (value.equals(codModel)) {
					//System.out.println("O codigo:" + cod + " é valido");
					return true;
				} else {
					//System.out.println("O codigo:" + cod + " já existe");
					return false;
				}

			} else {
				//System.out.println("Não retorna nada, codigo valido");
				return true;
			}

		} catch (Exception e) {
			System.out.println("dfasdf");
		}
		return null;
	}

}
