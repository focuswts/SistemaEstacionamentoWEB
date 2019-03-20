package br.com.estacionamento.mvc.model.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.estacionamento.mvc.model.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;

@WebServlet("/RemoverEstadoServlet")
public class RemoverEstadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoverEstadoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id-estado"));

		try {

			POEstado estado = new POEstado();
			CRUDEstado crud = new CRUDEstado();

			estado.setIdEstado(id);

			crud.delete(estado);

			System.out.println("Removido Com Sucesso!");
		} catch (Exception e) {
			System.out.println("Ocorreu Um Erro Ao Remover O Estado");
			e.printStackTrace();
		}

	}

}
