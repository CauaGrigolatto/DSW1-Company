package br.edu.ifsp.dsw.company.controller;

import br.edu.ifsp.dsw.company.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface SessionManager {
	HttpSession createSession(User user, HttpServletRequest request);
	void removeSession(HttpServletRequest req);
	void checkSession(HttpSession session) throws Exception;
}
