package br.edu.ifsp.dsw.company.model.dao;

import br.edu.ifsp.dsw.company.model.connection.ConnectionFactory;
import br.edu.ifsp.dsw.company.model.enums.DataAccessImplementation;

public class OrderDAOFactory {
	public static OrderDAO getDAO(DataAccessImplementation impl) {
		if (DataAccessImplementation.MYSQL.equals(impl)) {
			return new OrderDAOImpl(ConnectionFactory.getConnection());
		}
		
		return null;
	}
}
