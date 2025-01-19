package br.edu.ifsp.dsw.company.controller.filter;

import java.io.IOException;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/restrict/orders.jsp", "/restrict/my-orders.jsp"})
public class OrdersFilter extends HttpFilter {
	private final String ORDERS_URL = "/controller?targetCommand=OrderCommand&action=orders";
	private final String ORDERS_PAGE = "/orders.jsp";
	
	private final String MY_ORDERS_URL = "/company/controller?targetCommand=OrderCommand&action=myOrders";
	private final String MY_ORDERS_PAGE = "/my-orders.jsp";
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String uri = request.getRequestURI();
		
		if (StringUtils.isNotBlank(uri)) {
			if (uri.contains(ORDERS_PAGE)) {
				response.sendRedirect(ORDERS_URL);
			}
			else if (uri.contains(MY_ORDERS_PAGE)) {
				response.sendRedirect(MY_ORDERS_URL);
			}
		}
		else {			
			chain.doFilter(request, response);
		}
	}
}
