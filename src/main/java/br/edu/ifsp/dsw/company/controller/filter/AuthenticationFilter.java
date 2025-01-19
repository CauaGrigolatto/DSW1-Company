package br.edu.ifsp.dsw.company.controller.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter {
	private final String RESTRICT_PATH = "/restrict/";
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String path = request.getServletPath();
		boolean allowRequest = true;
		
		if (path.startsWith(RESTRICT_PATH)) {
			HttpSession session = request.getSession();
			
			if (session == null || session.getAttribute("username") == null) {
				allowRequest = false;
			}
		}
		
		if (allowRequest) {
			chain.doFilter(request, response);
		}
		else {			
			System.out.println("Access denied.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
