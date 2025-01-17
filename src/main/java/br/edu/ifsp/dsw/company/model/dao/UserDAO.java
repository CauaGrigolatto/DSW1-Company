package br.edu.ifsp.dsw.company.model.dao;

import br.edu.ifsp.dsw.company.model.entity.User;

public interface UserDAO {
	boolean insert(User user);
	boolean authenticate(User user);
}
