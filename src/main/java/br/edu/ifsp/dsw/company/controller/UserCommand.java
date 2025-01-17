package br.edu.ifsp.dsw.company.controller;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.dsw.company.model.connection.ConnectionFactory;
import br.edu.ifsp.dsw.company.model.dao.UserDAO;
import br.edu.ifsp.dsw.company.model.dao.UserDAOImpl;
import br.edu.ifsp.dsw.company.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

class UserCommand implements Command {
	private UserDAO userDAO;
	
	UserCommand() {
		this.userDAO = new UserDAOImpl(ConnectionFactory.getConnection());
	}
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String action = req.getParameter("action");
		
		if ("login".equals(action)) {
			return login(req);
		}
		
		return null;
	}
	
	private String login(HttpServletRequest req) {
		User user = toUser(req);
		
		if (userDAO.authenticate(user)) {
			createSession(user, req);
			return "/restrict/home.jsp";
		}
		
		return "index.jsp";
	}
	
	private HttpSession createSession(User user, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("username", user.getUsername());
		return session;
	}
	
	private User toUser(HttpServletRequest req) {
		String idStr = req.getParameter("id");
		Integer id = StringUtils.isNotBlank(idStr) ? Integer.parseInt(idStr) : null;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User(id, username, password);
		return user;
	}
}
