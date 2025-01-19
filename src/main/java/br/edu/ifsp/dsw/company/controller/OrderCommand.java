package br.edu.ifsp.dsw.company.controller;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.dsw.company.model.connection.ConnectionFactory;
import br.edu.ifsp.dsw.company.model.dao.OrderDAO;
import br.edu.ifsp.dsw.company.model.dao.OrderDAOImpl;
import br.edu.ifsp.dsw.company.model.dao.UserDAO;
import br.edu.ifsp.dsw.company.model.dao.UserDAOImpl;
import br.edu.ifsp.dsw.company.model.entity.Order;
import br.edu.ifsp.dsw.company.model.entity.User;
import br.edu.ifsp.dsw.company.model.exception.OrderNotFoundException;
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
			super.checkSession(session);
			
			if ("create".equals(action)) {
				return createOrder(req);
			}	
			else if ("orders".equals(action)) {
				return getOrders(req);
			}
			else if ("myOrders".equals(action)) {
				return getUserOrders(req);
			}
			else if ("delete".equals(action)) {
				return deleteOrder(req);
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
	
	private String getOrders(HttpServletRequest req) {
		req.setAttribute("orders", orderDAO.getAll());
		return "/restrict/orders.jsp";
	}
	
	private String getUserOrders(HttpServletRequest req) {
		String username = super.getSessionUsername(req.getSession(false));
		
		if (StringUtils.isNotBlank(username)) {
			User user = userDAO.getByUsername(username);
			req.setAttribute("orders", orderDAO.getByUser(user));
		}
		
		return "/restrict/my-orders.jsp";
	}
	
	private String deleteOrder(HttpServletRequest req) {
		try {
			String idStr = req.getParameter("id");
			
			if (StringUtils.isNotBlank(idStr)) {
				Integer id = Integer.parseInt(idStr);
				
				checkDeletionIntegrity(id, req);
				
				orderDAO.deleteById(id);
			}			
		}
		catch(OrderNotFoundException e) {
			req.setAttribute("errorMessage", e.getMessage());
		}
		catch(Throwable t) {
			System.err.println("Erro ao deletar Order.");
			t.printStackTrace();
			return "/index.jsp";
		}
		
		return getUserOrders(req);
	}
	
	private void checkDeletionIntegrity(Integer orderId, HttpServletRequest req) {
		String username = super.getSessionUsername(req.getSession(false));
		
		if (StringUtils.isBlank(username)) {
			throw new RuntimeException("Access denied.");
		}
		
		Order order = orderDAO.getById(orderId);
		
		if (order == null) {
			throw new OrderNotFoundException("No order found.");
		}

		User user = userDAO.getByUsername(username);
		
		if (user.getId() != order.getUser().getId()) {
			throw new RuntimeException("Access denied.");
		}
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
