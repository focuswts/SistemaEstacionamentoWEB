package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.model.crud.CRUDMarca;
import br.com.estacionamento.mvc.model.persistent_object.POMarca;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/CRUDMarcaServlet")
public class CRUDMarcaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CRUDMarcaServlet() {
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
			listBrands(response);
			break;

		case "insert":
			System.out.println("CRUD Insert Operation");
			createBrand(request);
			break;

		case "update":
			System.out.println("CRUD Update Operation");
			updateBrand(request);
			break;

		case "delete":
			System.out.println("CRUD Delete Operation");
			removeBrand(request);
			break;

		}
	}

	private JSONArray listBrands(HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			CRUDMarca crud = new CRUDMarca();
			ArrayList<POMarca> result = crud.list("");

			if (result.size() > 0) {
				for (POMarca m : result) {
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
			System.out.println("Erro Pegar Lista Usuários");
			e.printStackTrace();
		}
		return arr;

	}

	private void createBrand(HttpServletRequest request) {
		String descMarca = request.getParameter("desc-marca");
		String statusMarca = request.getParameter("status-marca");
		try {
			POMarca marca = new POMarca();
			CRUDMarca crud = new CRUDMarca();

			// Definição De Dados Para O OBJ Usuario
			marca.setDescMarca(descMarca);

			switch (statusMarca) {
			case "0":
				marca.setStatusMarca(EnumStatus.ATIVO);
				break;
			case "1":
				marca.setStatusMarca(EnumStatus.INATIVO);
				break;
			}

			crud.insert(marca);

			if (marca.getIdMarca() > 0) {
				System.out.println("Marca Inserida Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir A Marca");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir Marca");
			e.printStackTrace();
		}

	}

	private void updateBrand(HttpServletRequest request) {
		int idMarca = Integer.valueOf(request.getParameter("id-marca"));
		String descMarca = request.getParameter("desc-marca");
		String statusMarca = request.getParameter("status-marca");
		try {
			POMarca marca = new POMarca();
			CRUDMarca crud = new CRUDMarca();

			// Definição De Dados Para O OBJ Usuario
			marca.setIdMarca(idMarca);
			marca.setDescMarca(descMarca);

			switch (statusMarca) {
			case "0":
				marca.setStatusMarca(EnumStatus.ATIVO);
				break;
			case "1":
				marca.setStatusMarca(EnumStatus.INATIVO);
				break;
			}

			crud.update(marca);

			if (marca.getIdMarca() > 0) {
				System.out.println("Marca Atualizada Com Sucesso!");
			} else {
				System.out.println("Erro Ao Atualizar A Marca");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Marca");
			e.printStackTrace();
		}

	}

	private void removeBrand(HttpServletRequest request) {
		int idMarca = Integer.valueOf(request.getParameter("id-marca"));

		try {
			POMarca marca = new POMarca();
			CRUDMarca crud = new CRUDMarca();

			marca.setIdMarca(idMarca);

			crud.delete(marca);
			System.out.println("Marca Removida Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Marca");
			e.printStackTrace();
		}

	}

}
