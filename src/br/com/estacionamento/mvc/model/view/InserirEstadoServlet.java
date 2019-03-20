package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.estacionamento.mvc.model.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

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
		String nome = request.getParameter("nome-estado"); //Nome Do Parametro Do JS
		String uf = request.getParameter("sigla-estado");
		
	
		System.out.println(request.getParameter("nome-estado"));
		
		try {
			POEstado estado = new POEstado();
			CRUDEstado crudEstado = new CRUDEstado();
			estado.setNomeEstado(nome);
			estado.setSiglaEstado(uf);

			//Define O Valor DO EnumStatus
			switch (request.getParameter("status-estado")) {
			case "0":
				estado.setStatusEstado(EnumStatus.ATIVO);
				break;
			case "1":
				estado.setStatusEstado(EnumStatus.INATIVO);
				break;
			}
			System.out.println(request.getParameter("status-estado"));
			crudEstado.insert(estado);

			if (estado.getIdEstado() > 0) {
				//response.sendRedirect(request.getContextPath() + "/sucesso.jsp");
			System.out.println("Estado Inserido Com Sucesso!");
			} else {
				
				response.sendRedirect(request.getContextPath() + "/erro.jsp");
			}

		} catch (Exception e) {
			System.out.println("Algum Erro Ocorreu Ao Inserir Estado");
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/erro.jsp");
		}
	}

}
