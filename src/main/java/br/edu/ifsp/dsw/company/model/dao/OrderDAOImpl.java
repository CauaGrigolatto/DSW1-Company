package br.edu.ifsp.dsw.company.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw.company.model.entity.Order;
import br.edu.ifsp.dsw.company.model.entity.User;

public class OrderDAOImpl implements OrderDAO {
	private Connection conn;
	
	private UserDAO userDAO;
	
	private final String INSERT = 
			"INSERT INTO orders(order_description, price, client_name, client_address, user_id) VALUES (?,?,?,?,?)";
	private final String GET_ALL = "SELECT * FROM orders";
	private final String GET_BY_USER = "SELECT * FROM orders WHERE user_id = ?";
	
	public OrderDAOImpl(Connection conn) {
		this.conn = conn;
		this.userDAO = new UserDAOImpl(conn);
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
			System.err.println("Erro ao inserir Order.");
			t.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Order> getAll() {
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_ALL);
			ResultSet result = stmt.executeQuery();
			List<Order> orders = getResultList(result);
			return orders;
		}
		catch(Throwable t) {
			System.err.println("Erro ao inserir Order.");
			t.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<Order> getByUser(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_BY_USER);
			stmt.setInt(1, user.getId());
			ResultSet result = stmt.executeQuery();
			List<Order> orders = getResultList(result);
			return orders;
		}
		catch(Throwable t) {
			System.err.println("Erro ao inserir Order.");
			t.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	private List<Order> getResultList(ResultSet result) throws Throwable {
		try {
			List<Order> orders = new LinkedList<Order>();
			
			while (result.next()) {
				Integer id = result.getInt("order_id");
				String description = result.getString("order_description");
				Double price = result.getDouble("price");
				String client = result.getString("client_name");
				String address = result.getString("client_address");
				Integer userId = result.getInt("user_id");
				User user = userDAO.getById(userId);
				orders.add(new Order(id, description, price, client, address, user));
			}
			
			return orders;
		}
		catch(Throwable t) {
			System.err.println("Erro ao recuperar dados.");
			throw t;
		}
	}
	
}
