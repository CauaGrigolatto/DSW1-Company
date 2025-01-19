package br.edu.ifsp.dsw.company.controller.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/restrict/orders.jsp")
public class OrdersFilter extends HttpFilter {
	private final String URL = "/controller?targetCommand=OrderCommand&action=orders";
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request.getAttribute("orders") == null) {
            response.sendRedirect(request.getContextPath() + URL);
            return;
        }
		else {			
			chain.doFilter(request, response);
		}
	}
}
