package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.model.crud.CRUDCidade;
import br.com.estacionamento.mvc.model.crud.CRUDMarca;
import br.com.estacionamento.mvc.model.persistent_object.POCidade;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;
import br.com.estacionamento.mvc.model.persistent_object.POMarca;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/CRUDCidadeServlet")
public class CRUDCidadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CRUDCidadeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operation = request.getParameter("operation");

		switch (operation) {
		case "list":
			System.out.println("CRUD List Operation");
			listCities(response);
			break;

		case "insert":
			System.out.println("CRUD Insert Operation");
			createCity(request);
			break;

		case "update":
			System.out.println("CRUD Update Operation");
			updateCity(request);
			break;

		case "delete":
			System.out.println("CRUD Delete Operation");
			removeCity(request);
			break;

		}

	}

	// LIST
	private JSONArray listCities(HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			CRUDCidade crud = new CRUDCidade();
			ArrayList<POCidade> result = crud.list("");

			if (result.size() > 0) {
				for (POCidade c : result) {
					try {
						arr.put(c.toJSON());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(arr.toString());

		} catch (Exception e) {
			System.out.println("Erro Pegar Lista Cidades");
			e.printStackTrace();
		}
		return arr;

	}

	// INSERT
	private void createCity(HttpServletRequest request) {
		String nomeCidade = request.getParameter("nome-cidade");
		int idEstado = Integer.valueOf(request.getParameter("id-estado"));
		String statusCidade = request.getParameter("status-cidade");
		try {
			POEstado estado = new POEstado();
			POCidade cidade = new POCidade();
			CRUDCidade crud = new CRUDCidade();

			estado.setIdEstado(idEstado);
			// Definição De Dados Para O OBJ Usuario
			cidade.setNomeCidade(nomeCidade);
			cidade.setIdEstado(estado);

			switch (statusCidade) {
			case "0":
				cidade.setStatusCidade(EnumStatus.ATIVO);
				break;
			case "1":
				cidade.setStatusCidade(EnumStatus.INATIVO);
				break;
			}

			crud.insert(cidade);

			if (cidade.getIdCidade() > 0) {
				System.out.println("Cidade Inserida Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir A Cidade");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir Cidade");
			e.printStackTrace();
		}

	}

	// UPDATE
	private void updateCity(HttpServletRequest request) {
		int idCidade = Integer.valueOf(request.getParameter("id-cidade"));
		String nomeCidade = request.getParameter("nome-cidade");
		int idEstado = Integer.valueOf(request.getParameter("id-estado"));
		String statusCidade = request.getParameter("status-cidade");
		try {
			POEstado estado = new POEstado();
			POCidade cidade = new POCidade();
			CRUDCidade crud = new CRUDCidade();

			estado.setIdEstado(idEstado);
			cidade.setIdCidade(idCidade);
			cidade.setNomeCidade(nomeCidade);
			cidade.setIdEstado(estado);

			switch (statusCidade) {
			case "0":
				cidade.setStatusCidade(EnumStatus.ATIVO);
				break;
			case "1":
				cidade.setStatusCidade(EnumStatus.INATIVO);
				break;
			}

			crud.update(cidade);

			if (cidade.getIdCidade() > 0) {
				System.out.println("Cidade Atualizada Com Sucesso!");
			} else {
				System.out.println("Erro Ao Atualizar A Cidade");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Cidade");
			e.printStackTrace();
		}

	}

	private void removeCity(HttpServletRequest request) {
		int idCidade = Integer.valueOf(request.getParameter("id-cidade"));

		try {
			POCidade cidade = new POCidade();
			CRUDCidade crud = new CRUDCidade();

			cidade.setIdCidade(idCidade);

			crud.delete(cidade);
			System.out.println("Cidade Removida Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Cidade");
			e.printStackTrace();
		}

	}

}
