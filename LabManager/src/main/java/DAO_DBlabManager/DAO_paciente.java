package DAO_DBlabManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import java.sql.Statement;

import DTO.paciente_DTO;

public class DAO_paciente {
	ResultSet rs;
	Statement stmt;
	String query = "SELECT LAST_INSERT_ID() as coluna";
	public static String idUltimo;

	public void inserir_paciente(paciente_DTO pc_dto) {

		try {

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();

			// criar comando preparado
			String sql = "insert into paciente (nome, sexo, data_nascimento, nome_mae, cpf, RG, cns, cep, endereco, numero, cidade, celular, telefone, email,"
					+ "obsevacao, gestante )\n" + "values\n" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, pc_dto.getNome());
			pstmt.setString(2, pc_dto.getSexo());
			pstmt.setString(3, pc_dto.getDate());
			pstmt.setString(4, pc_dto.getNome_mae());
			pstmt.setString(5, pc_dto.getCpf());
			pstmt.setString(6, pc_dto.getRg());
			pstmt.setString(7, pc_dto.getCns());
			pstmt.setString(8, pc_dto.getCep());
			pstmt.setString(9, pc_dto.getEndereco());
			pstmt.setString(10, pc_dto.getNumero());
			pstmt.setString(11, pc_dto.getCidade());
			pstmt.setString(12, pc_dto.getCelular());
			pstmt.setString(13, pc_dto.getTelefone());
			pstmt.setString(14, pc_dto.getEmail());
			pstmt.setString(15, pc_dto.getObservacao());
			pstmt.setInt(16, pc_dto.getGesta());

			pstmt.executeUpdate(); // executa comando
			// encerra comando preparado
			pstmt.close();

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				idUltimo = rs.getString("coluna");
			}

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Paciente: " + pc_dto.getNome() + ", adicionado ao Banco");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! Paciente: , NÃO GRAVADO," + e);
		}

	}

}
