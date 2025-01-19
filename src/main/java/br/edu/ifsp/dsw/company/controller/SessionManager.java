package br.edu.ifsp.dsw.company.controller;

import jakarta.servlet.http.HttpSession;

class SessionChecker {
	void checkSession(HttpSession session) throws Exception {
		if (session == null || session.getAttribute("username") == null) {
			throw new Exception("Access denied.");
		}
	}
	
	String getSessionUsername(HttpSession session) {
		return (String) session.getAttribute("username");
	}
}
