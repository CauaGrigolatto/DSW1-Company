package br.edu.ifsp.dsw.company.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String PACKAGE_PATH = "br.edu.ifsp.dsw.company.controller.";
	
	@Override
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String commandString = req.getParameter("targetCommand");
			Command command = (Command) Class.forName(PACKAGE_PATH + commandString).newInstance();
			String url = command.execute(req, resp);
			req.getRequestDispatcher(url).forward(req, resp);
		}
		catch(Throwable t) {
			System.err.println("Erro ao carregar Command");
			t.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
