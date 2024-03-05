package DAO_DBlabManager;

import java.sql.Connection;

import javax.swing.JOptionPane;

public class exec_conexao {

	
	public static void main(String arg[]) throws Exception {

		try {
			Connection con = conecta_DBlabmanager.getInstance().getConnection();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na tentativa de conexão" + e);
		}

		conecta_DBlabmanager.getInstance().shutdow();

	}

}
