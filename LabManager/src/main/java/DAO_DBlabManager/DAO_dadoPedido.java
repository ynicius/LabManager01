package DAO_DBlabManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import DTO.dadoPedido_DTO;

public class DAO_dadoPedido {

	Connection con;

	public DAO_dadoPedido(dadoPedido_DTO pc_dto) {

		try {

			con = conecta_DBlabmanager.getInstance().getConnection();

			// criar comando preparado
			String sql = "INSERT INTO db_labManager.dadoAtend"
					+ "(id_paciente, codConvenio, numMatricula, plano, validadeCart,"
					+ "titular, prioridade, preferencia, codSolicita, CodDoenca, descDoenca,"
					+ "comentario, medicamento, jejum)\n" + "values\n" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, pc_dto.getIdPaciente());
			pstmt.setString(2, pc_dto.getCodConvenio());
			pstmt.setString(3, pc_dto.getNumMatricula());
			pstmt.setString(4, pc_dto.getPlano());
			pstmt.setString(5, pc_dto.getValidadeCart());
			pstmt.setString(6, pc_dto.getTitula());
			pstmt.setString(7, pc_dto.getPrioridade());
			pstmt.setInt(8, pc_dto.getPreferencia());
			pstmt.setString(9, pc_dto.getCodSolicita());
			pstmt.setString(10, pc_dto.getCodDoenca());
			pstmt.setString(11, pc_dto.getDescDoenca());
			pstmt.setString(12, pc_dto.getComentario());
			pstmt.setString(13, pc_dto.getMedicamento());
			pstmt.setString(14, pc_dto.getJejum());

			pstmt.executeUpdate(); // executa comando
			pstmt.close();

			JOptionPane.showMessageDialog(null, "ok! dados do paciente: " + pc_dto.getIdPaciente() + " cadastrados ");

		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! (DAO_dadoPedido): , NÃO GRAVADO-> " + e);
		}

	}

}
