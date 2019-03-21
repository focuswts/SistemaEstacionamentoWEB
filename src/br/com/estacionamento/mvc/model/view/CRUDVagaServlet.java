package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.model.crud.CRUDVaga;
import br.com.estacionamento.mvc.model.persistent_object.POVaga;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/CRUDVagaServlet")
public class CRUDVagaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CRUDVagaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operation = request.getParameter("operation");

		switch (operation) {
		case "list":
			System.out.println("CRUD List Operation");
			listSpot(response);
			break;

		case "insert":
			System.out.println("CRUD Insert Operation");
			createSpot(request);
			break;

		case "update":
			System.out.println("CRUD Update Operation");
			updateSpot(request);
			break;

		case "delete":
			System.out.println("CRUD Delete Operation");
			removeSpot(request);
			break;

		}
		
	}
	
	private JSONArray listSpot(HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			CRUDVaga crud = new CRUDVaga();
			ArrayList<POVaga> result = crud.list("");

			if (result.size() > 0) {
				for (POVaga m : result) {
					try {
						arr.put(m.toJSON());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(arr.toString());

		} catch (Exception e) {
			System.out.println("Erro Pegar Lista Usu�rios");
			e.printStackTrace();
		}
		return arr;

	}

	private void createSpot(HttpServletRequest request) {
		String descVaga = request.getParameter("desc-vaga");
		String statusVaga = request.getParameter("status-vaga");
		try {
			POVaga vaga = new POVaga();
			CRUDVaga crud = new CRUDVaga();

			// Defini��o De Dados Para O OBJ Usuario
			vaga.setDescVaga(descVaga);

			switch (statusVaga) {
			case "0":
				vaga.setStatusVaga(EnumStatus.ATIVO);
				break;
			case "1":
				vaga.setStatusVaga(EnumStatus.INATIVO);
				break;
			}

			crud.insert(vaga);

			if (vaga.getIdVaga() > 0) {
				System.out.println("Vaga Inserida Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir A Vaga");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir Vaga");
			e.printStackTrace();
		}

	}

	private void updateSpot(HttpServletRequest request) {
		int idVaga = Integer.valueOf(request.getParameter("id-vaga"));
		String descVaga = request.getParameter("desc-vaga");
		String statusVaga = request.getParameter("status-vaga");
		try {
			POVaga vaga = new POVaga();
			CRUDVaga crud = new CRUDVaga();

			// Defini��o De Dados Para O OBJ Usuario
			vaga.setIdVaga(idVaga);
			vaga.setDescVaga(descVaga);

			switch (statusVaga) {
			case "0":
				vaga.setStatusVaga(EnumStatus.ATIVO);
				break;
			case "1":
				vaga.setStatusVaga(EnumStatus.INATIVO);
				break;
			}

			crud.update(vaga);

			if (vaga.getIdVaga() > 0) {
				System.out.println("Vaga Atualizada Com Sucesso!");
			} else {
				System.out.println("Erro Ao Atualizar A Vaga");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Vaga");
			e.printStackTrace();
		}

	}

	private void removeSpot(HttpServletRequest request) {
		int idVaga = Integer.valueOf(request.getParameter("id-vaga"));

		try {
			POVaga vaga = new POVaga();
			CRUDVaga crud = new CRUDVaga();

			vaga.setIdVaga(idVaga);

			crud.delete(vaga);
			System.out.println("Vaga Removida Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Vaga");
			e.printStackTrace();
		}

	}
	
	

}
