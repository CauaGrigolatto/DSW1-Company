package br.edu.ifsp.dsw.company.model.dao;

import java.util.List;

import br.edu.ifsp.dsw.company.model.entity.Order;

public interface OrderDAO {
	boolean insert(Order order);
	List<Order> getAll();
}
