package br.edu.ifsp.dsw.company.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static Connection conn;
	
	private static final String URL = "jdbc:mysql://localhost:3306/company";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static void createConnection() throws Throwable {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static Connection getConnection() {
		try {
			if (conn == null) createConnection();
		}
		catch(Throwable t) {
			System.err.println("Erro ao criar conexão com o banco de dados");
			t.printStackTrace();
		}
		
		return conn;
	}
}
