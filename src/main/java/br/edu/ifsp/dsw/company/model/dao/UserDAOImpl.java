package br.edu.ifsp.dsw.company.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.edu.ifsp.dsw.company.model.entity.User;

public class UserDAOImpl implements UserDAO {
	
	private Connection conn;
	
	private final String INSERT = "INSERT INTO users(username, identifier) VALUES (?,?)";
	private final String GET_BY_CREDENTIALS = "SELECT * FROM users WHERE username = ? AND identifier = ?";
	private final String GET_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
	private final String EXISTS_LOGIN = "SELECT user_id FROM users WHERE username = ?";
	
	public UserDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean insert(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			return stmt.execute();
		}
		catch(Throwable t) {
			System.err.println("Erro ao inserir User");
			t.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean authenticate(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_BY_CREDENTIALS);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			ResultSet result = stmt.executeQuery();
			return result.next();
		}
		catch(Throwable t) {
			System.err.println("Erro ao inserir User");
			t.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean existsUsername(String username) {
		try {
			PreparedStatement stmt = conn.prepareStatement(EXISTS_LOGIN);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			return result.next();
		}
		catch(Throwable t) {
			System.err.println("Erro ao procurar username");
			t.printStackTrace();
			return false;
		}
	}
	
	@Override
	public User getByUsername(String username) {
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_BY_USERNAME);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			
			User user = null;
			
			while (result.next()) {
				Integer id = result.getInt("user_id");
				String password = result.getString("identifier");
				user = new User(id, username, password);
			}
			
			return user;
		}
		catch(Throwable t) {
			System.err.println("Erro ao obter pelo username");
			t.printStackTrace();
			return null;
		}
	}
}
