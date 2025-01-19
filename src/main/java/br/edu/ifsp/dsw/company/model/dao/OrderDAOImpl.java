package br.edu.ifsp.dsw.company.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.edu.ifsp.dsw.company.model.entity.Order;

public class OrderDAOImpl implements OrderDAO {
	private Connection conn;
	
	private final String INSERT = 
			"INSERT INTO orders(order_description, price, client_name, client_address, user_id) VALUES (?,?,?,?,?)";
	
	public OrderDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean insert(Order order) {
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, order.getDescription());
			stmt.setDouble(2, order.getPrice());
			stmt.setString(3, order.getClient());
			stmt.setString(4, order.getAddress());
			stmt.setInt(5, order.getUser().getId());
			return stmt.execute();
		}
		catch(Throwable t) {
			System.err.println("Erro ao inserir Order");
			t.printStackTrace();
			return false;
		}
	}
}
