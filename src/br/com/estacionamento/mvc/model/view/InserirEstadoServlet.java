package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InserirEstadoServlet
 */
@WebServlet("/InserirEstadoServlet")
public class InserirEstadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InserirEstadoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inserindo O Estado " + request.getParameter("nome-estado"));
	}

}
