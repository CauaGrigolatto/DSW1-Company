package br.edu.ifsp.dsw.company.model.dao;

import br.edu.ifsp.dsw.company.model.connection.ConnectionFactory;
import br.edu.ifsp.dsw.company.model.enums.DataAccessImplementation;

public class UserDAOFactory {
	public static UserDAO getDAO(DataAccessImplementation impl) {
		if (DataAccessImplementation.MYSQL.equals(impl)) {
			return new UserDAOImpl(ConnectionFactory.getConnection());
		}
		
		return null;
	}
}
