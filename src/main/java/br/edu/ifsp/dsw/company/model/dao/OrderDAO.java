package br.edu.ifsp.dsw.company.model.dao;

import java.util.List;

import br.edu.ifsp.dsw.company.model.entity.Order;
import br.edu.ifsp.dsw.company.model.entity.User;

public interface OrderDAO {
	boolean insert(Order order);
	boolean deleteById(Integer id);
	Order getById(Integer id);
	List<Order> getAll();
	List<Order> getByUser(User user);
	List<Order> searchByClient(String client);
}
