package br.edu.ifsp.dsw.company.controller;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.dsw.company.model.dao.UserDAO;
import br.edu.ifsp.dsw.company.model.dao.UserDAOFactory;
import br.edu.ifsp.dsw.company.model.entity.User;
import br.edu.ifsp.dsw.company.model.enums.DataAccessImplementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

class UserCommand extends SessionChecker implements Command {
	private UserDAO userDAO;
	
	UserCommand() {
		this.userDAO = UserDAOFactory.getDAO(DataAccessImplementation.MYSQL);
	}
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String action = req.getParameter("action");
			HttpSession session = req.getSession(false);
			
			if ("login".equals(action)) {
				return login(req);
			}
			else if ("register".equals(action)) {
				super.checkSession(session);
				return register(req);
			}
			else if ("logout".equals(action)) {
				return logout(req);
			}			
		}
		catch(Exception e) {
			System.err.println("Access denied.");
		}
		
		return "/index.jsp";
	}
	
	private String login(HttpServletRequest req) {
		User user = toUser(req);
		
		if (userDAO.authenticate(user)) {
			createSession(user, req);
			return "/restrict/home.jsp";
		}
		
		return "index.jsp";
	}
	
	private String register(HttpServletRequest req) {
		try {
			User user = toUser(req);
			checkUser(user);
			userDAO.insert(user);
			req.setAttribute("successMessage", "Usuário inserido com sucesso.");
		}
		catch(RuntimeException e) {
			req.setAttribute("errorMessage", e.getMessage());
			System.err.println("Erro ao registrar usuário.");
			e.printStackTrace();
		}
		
		return "/restrict/register.jsp";
	}
	
	private String logout(HttpServletRequest req) {
		removeSession(req);
		return "/index.jsp";
	}
	
	private void checkUser(User user) {
		if (StringUtils.isBlank(user.getUsername())) {
			throw new RuntimeException("Username cannot be empty.");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			throw new RuntimeException("Password cannot be empty.");
		}
		if (userDAO.existsUsername(user.getUsername())) {
			throw new RuntimeException("Username is already being used.");
		}
	}
	
	public void removeSession(HttpServletRequest req) {
		var session = req.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
	}
	
	public HttpSession createSession(User user, HttpServletRequest request) {
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
