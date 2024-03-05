package DAO_DBlabManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import DTO.convenio_DTO;
import DTO.exameProcedimento_DTO;

public class DAO_convenios {

	public String retorno;	
	private List<String> lista1 = new ArrayList<String>();
	private String sql = null;
	private int params = 0;
	Statement stmt;
	String codigo;
	ResultSet rs;
	
	
	public List<String> listaConvenio(){
		try {
			String query = "select nomeConvenio from convenios;";

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			// System.out.println(rs.getRow());

			while (rs.next()) {

				lista1.add(rs.getString(1));

			}
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "ERRO! Convenio: , NÃO GRAVADO," + e);
		}

		return lista1;
	}

	

	public void inserir_exame(convenio_DTO conc_dto) {

		try {

			Properties prop = getProp();

			sql = prop.getProperty("DataInsert.sql");
			params = Integer.parseInt(prop.getProperty("DataInsert.params"));

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();

			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setString(1, conc_dto.getNomeConvenio());
			pstm.setString(2, conc_dto.getTabelaCobranca());
			pstm.setInt(3, conc_dto.getCobertura());

			// executa comando
			pstm.executeUpdate();
			// encerra comando preparado
			pstm.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão

			JOptionPane.showMessageDialog(null, "ok! Convenio: , adicionado ao Banco");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO! Convenio: , NÃO GRAVADO," + e);
			// System.out.println("fasdfsad" + e);

		}

	}

	// propriedades de inserção convenio
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/dataInsert.properties");
		props.load(file);
		return props;

	}

	public boolean verificar_codigoExame(convenio_DTO nomeConvenio) {

		try {

			codigo = nomeConvenio.getNomeConvenio();
			String query = "select nomeConvenio from convenios where nomeConvenio = '" + codigo + "';";

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

	public Boolean verificaAtualiza(String txtConv, convenio_DTO model1) {
		try {
			// código digitado
			String cod = txtConv;
			String model = model1.getNomeConvenio();

			// código do model, não varia
			// String codModel = codigo.getProcurar();
			String query = "select nomeConvenio from convenios where nomeConvenio like '" + cod + "';";

			Connection con = conecta_DBlabmanager.getInstance().getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				String value = rs.getString(1);
				if (value.equals(model)) {
					// System.out.println("O codigo:" + model + value + " é valido");
					return true;
				} else {
					// System.out.println("O codigo:" + cod + " já existe");
					return false;
				}

			} else {
				// System.out.println("Não retorna nada, codigo valido");
				return true;
			}

		} catch (Exception e) {
			System.out.println("ERRO! ERRO!" + e);
		}
		return null;
	}

	public void apagarExame(int codApagar) {
		try {

			String query = "delete from convenios where id_convenios = ?;";
			Connection con = conecta_DBlabmanager.getInstance().getConnection();

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, codApagar);
			pstmt.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO ao apagar codigo:" + codigo + "erro: " + e);

		}

	}

	public void atualizarConvenio(convenio_DTO dto) {
		String sql = "update db_labManager.convenios set nomeConvenio = ? , cobertura = ? where id_convenios = ?;";

		try {

			String query = sql;
			Connection con = conecta_DBlabmanager.getInstance().getConnection();

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, dto.getNomeConvenio());
			pstmt.setInt(2, dto.getCobertura());
			pstmt.setInt(3, dto.getIdConvenio());

			pstmt.executeUpdate();
			pstmt.close();

			conecta_DBlabmanager.getInstance().shutdow(); // encerra conexão
			JOptionPane.showMessageDialog(null, "ok! Exame Codigo: " + dto.getIdConvenio() + ", atualizado");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO ao apagar codigo:" + codigo + "erro: " + e);

		}
	}
	
	
	

}
