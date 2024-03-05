package DAO_DBlabManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.swing.JOptionPane;

public class conecta_DBlabmanager {

	private Connection con = null;
	private static conecta_DBlabmanager instance = null;
	private int clients = 0;

	private conecta_DBlabmanager() {
		try {
			//CHAMA O MÉTODO COM O IMPUSTREAM DO ARQUIVO
			Properties prop = getProp();
			String dbDriver = prop.getProperty("db.driver");
			String dbUrl = prop.getProperty("db.url");
			String dbUser = prop.getProperty("db.user");
			String dbPwd = prop.getProperty("db.pwd");

			// String url =
			// "jdbc:mysql://localhost:3306/dbstudent?user=root&password=admin";
			// con = DriverManager.getConnection(url);
			if (dbUser.length() != 0) { // para acesso com usuário e senha
				con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			} else { // para acesso direto (sem usuário e senha)
				con = DriverManager.getConnection(dbUrl);
			}
			// System.out.println("DB[conexao OK]");

			//JOptionPane.showMessageDialog(null, "construtor instanciado com sussesso ");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao iniciar construtor url de conexão: " + e);
		}
		// return con;
	}

	public static conecta_DBlabmanager getInstance() { // Retorna instância única
		if (instance == null) {
			instance = new conecta_DBlabmanager();
		}
		return instance;
	}

	public Connection getConnection() { // Retorna Conexão
		if (con == null) {
			throw new RuntimeException("connetion ==null");
		}
		clients++;
		return con;
	}

	public void shutdow() { // Efetua fechamento controlado da conexão.
		System.out.println("DB[shutdown cliente]");
		clients--;
		if (clients > 0) {
			return;
		}

		try {
			con.close();
			instance = null;
			con = null;
			// JOptionPane.showMessageDialog(null, "conetaBD[conexao fechada]");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/DB.properties");
		props.load(file);
		return props;

	}

}
