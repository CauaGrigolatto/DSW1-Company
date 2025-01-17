package br.edu.ifsp.dsw.company.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
	String execute(HttpServletRequest req, HttpServletResponse resp);
}
