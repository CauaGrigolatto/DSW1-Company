package br.edu.ifsp.dsw.company.controller;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.dsw.company.model.connection.ConnectionFactory;
import br.edu.ifsp.dsw.company.model.dao.OrderDAO;
import br.edu.ifsp.dsw.company.model.dao.OrderDAOImpl;
import br.edu.ifsp.dsw.company.model.dao.UserDAO;
import br.edu.ifsp.dsw.company.model.dao.UserDAOImpl;
import br.edu.ifsp.dsw.company.model.entity.Order;
import br.edu.ifsp.dsw.company.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

class OrderCommand extends SessionChecker implements Command {
	private OrderDAO orderDAO;
	private UserDAO userDAO;
	
	OrderCommand() {
		this.orderDAO = new OrderDAOImpl(ConnectionFactory.getConnection());
		this.userDAO = new UserDAOImpl(ConnectionFactory.getConnection());
	}
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String action = req.getParameter("action");
			HttpSession session = req.getSession(false);
			
			if ("create".equals(action)) {
				super.checkSession(session);
				return createOrder(req);
			}		
		}
		catch(Exception e) {
			System.err.println("Access denied.");
		}
		
		return "/index.jsp";
	}
	
	private String createOrder(HttpServletRequest req) {
		Order order = toOrder(req);
		orderDAO.insert(order);
		return "/restrict/order-creation.jsp";
	}

	private Order toOrder(HttpServletRequest req) {
		String idStr = req.getParameter("id");
		Integer id = StringUtils.isNotBlank(idStr) ? Integer.parseInt(idStr) : null;
		String description = req.getParameter("description");
		String priceStr = req.getParameter("price");
		Double price = StringUtils.isNotBlank(priceStr) ? Double.parseDouble(priceStr) : null;
		String client = req.getParameter("client");
		String address = req.getParameter("address");
		String username = super.getSessionUsername(req.getSession(false));
		User user = userDAO.getByUsername(username);
		return new Order(id, description, price, client, address, user);
	}
	
	
}
