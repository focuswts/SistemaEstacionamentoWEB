package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.model.crud.CRUDModelo;
import br.com.estacionamento.mvc.model.persistent_object.POMarca;
import br.com.estacionamento.mvc.model.persistent_object.POModelo;
import br.com.estacionamento.mvc.model.persistent_object.POTipoVeiculo;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/CRUDModeloServlet")
public class CRUDModeloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CRUDModeloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operation = request.getParameter("operation");

		switch (operation) {
		case "list":
			System.out.println("CRUD List Operation");
			listModels(response);
			break;

		case "insert":
			System.out.println("CRUD Insert Operation");
			createModel(request);
			break;

		case "update":
			System.out.println("CRUD Update Operation");
			updateModel(request);
			break;

		case "delete":
			System.out.println("CRUD Delete Operation");
			removeModel(request);
			break;

		}

	}

	// LIST
	private JSONArray listModels(HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			CRUDModelo crud = new CRUDModelo();
			ArrayList<POModelo> result = crud.list("");

			if (result.size() > 0) {
				for (POModelo m : result) {
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
			System.out.println("Erro Pegar Lista Modelos");
			e.printStackTrace();
		}
		return arr;

	}

	// INSERT
	private void createModel(HttpServletRequest request) {
		String descModelo = request.getParameter("desc-modelo");
		int idMarca = Integer.valueOf(request.getParameter("id-marca"));
		int idTipoV = Integer.valueOf(request.getParameter("id-tipoV"));
		String statusModelo = request.getParameter("status-modelo");
		try {
			POMarca marca = new POMarca();
			POTipoVeiculo tipoV = new POTipoVeiculo();

			POModelo modelo = new POModelo();
			CRUDModelo crud = new CRUDModelo();

			marca.setIdMarca(idMarca);
			tipoV.setIdTipoV(idTipoV);
			// Definição De Dados Para O OBJ Usuario
			modelo.setDescModelo(descModelo);
			modelo.setIdMarca(marca);
			modelo.setIdTipoV(tipoV);

			switch (statusModelo) {
			case "0":
				modelo.setStatusModelo(EnumStatus.ATIVO);
				break;
			case "1":
				modelo.setStatusModelo(EnumStatus.INATIVO);
				break;
			}

			crud.insert(modelo);

			if (modelo.getIdModelo() > 0) {
				System.out.println("Modelo Inserido Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir O Modelo");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir Modelo");
			e.printStackTrace();
		}

	}

	// UPDATE
	private void updateModel(HttpServletRequest request) {
		int idModelo = Integer.valueOf(request.getParameter("id-modelo"));
		String descModelo = request.getParameter("desc-modelo");
		int idMarca = Integer.valueOf(request.getParameter("id-marca"));
		int idTipoV = Integer.valueOf(request.getParameter("id-tipoV"));
		String statusModelo = request.getParameter("status-modelo");
		try {
			POMarca marca = new POMarca();
			POTipoVeiculo tipoV = new POTipoVeiculo();

			POModelo modelo = new POModelo();
			CRUDModelo crud = new CRUDModelo();

			marca.setIdMarca(idMarca);
			tipoV.setIdTipoV(idTipoV);

			modelo.setIdModelo(idModelo);
			modelo.setDescModelo(descModelo);
			modelo.setIdMarca(marca);
			modelo.setIdTipoV(tipoV);

			switch (statusModelo) {
			case "0":
				modelo.setStatusModelo(EnumStatus.ATIVO);
				break;
			case "1":
				modelo.setStatusModelo(EnumStatus.INATIVO);
				break;
			}

			crud.update(modelo);

			System.out.println("Modelo Atualizado Com Sucesso!");

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Modelo");
			e.printStackTrace();
		}

	}

	private void removeModel(HttpServletRequest request) {
		int idModelo = Integer.valueOf(request.getParameter("id-modelo"));

		try {
			POModelo modelo = new POModelo();
			CRUDModelo crud = new CRUDModelo();

			modelo.setIdModelo(idModelo);
			crud.delete(modelo);
			System.out.println("Modelo Removido Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Modelo");
			e.printStackTrace();
		}

	}

}
