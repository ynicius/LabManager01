package testes;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import DAO_DBlabManager.DAO_tabelaCobraca;


public class SizeX  {

	public static void main (String [] args) throws IOException {
		
		/*String login; //Variavel que guardará o login do servidor.
		String host; //Variavel que guardará o host do servidor.
		String password; //Variável que guardará o password do usúario.
		System.out.println("************Teste de leitura do arquivo de propriedades************");
		
		Properties prop = getProp();
		
		login = prop.getProperty("DataInsert.params");
		host = prop.getProperty("DataInsert.sql");
		password = prop.getProperty("DataInsert.type0");
		
		System.out.println("Login = " + login);
		System.out.println("Host = " + host);
		System.out.println("Password = " + password);*/
		
		DAO_tabelaCobraca tt = new DAO_tabelaCobraca();
		int [] numero = {5,2,3,4,7};		
		
		for (String L : tt.listConvenio()) {
			System.out.println(L);
		}
		
		
		
	}
	
	


public static Properties getProp() throws IOException {
	Properties props = new Properties();
	FileInputStream file = new FileInputStream(
			"./properties/dataInsert.properties");
	props.load(file);
	return props;

}
}
