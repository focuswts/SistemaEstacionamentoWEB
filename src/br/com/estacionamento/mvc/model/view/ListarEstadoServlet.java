package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.model.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;

@WebServlet("/ListarEstadoServlet")
public class ListarEstadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListarEstadoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CRUDEstado crud = new CRUDEstado();
		ArrayList<POEstado> result = crud.list("");

		JSONArray arr = new JSONArray();
		if (result.size() > 0) {
			for (POEstado e : result) {
				try {
					arr.put(e.toJSON());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arr.toString());
		

	}

}
