package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.model.crud.CRUDTipoVeiculo;
import br.com.estacionamento.mvc.model.persistent_object.POTipoVeiculo;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/CRUDTipoVeiculoServlet")
public class CRUDTipoVeiculoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CRUDTipoVeiculoServlet() {
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
			listVehicleTypes(response);
			break;

		case "insert":
			System.out.println("CRUD Insert Operation");
			createVehicleType(request);
			break;

		case "update":
			System.out.println("CRUD Update Operation");
			updateVehicleType(request);
			break;

		case "delete":
			System.out.println("CRUD Delete Operation");
			removeVehicleType(request);
			break;

		}

	}

	private JSONArray listVehicleTypes(HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			CRUDTipoVeiculo crud = new CRUDTipoVeiculo();
			ArrayList<POTipoVeiculo> result = crud.list("");

			if (result.size() > 0) {
				for (POTipoVeiculo tv : result) {
					try {
						arr.put(tv.toJSON());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(arr.toString());

		} catch (Exception e) {
			System.out.println("Erro Pegar Lista Tipos");
			e.printStackTrace();
		}
		return arr;

	}

	private void createVehicleType(HttpServletRequest request) {
		String descTipoV = request.getParameter("desc-tipoV");
		String statusTipoV = request.getParameter("status-tipoV");
		try {
			POTipoVeiculo tipoV = new POTipoVeiculo();
			CRUDTipoVeiculo crud = new CRUDTipoVeiculo();

			// Definição De Dados Para O OBJ Usuario
			tipoV.setDescTipoV(descTipoV);

			switch (statusTipoV) {
			case "0":
				tipoV.setStatusTipoV(EnumStatus.ATIVO);
				break;
			case "1":
				tipoV.setStatusTipoV(EnumStatus.INATIVO);
				break;
			}

			crud.insert(tipoV);

			if (tipoV.getIdTipoV() > 0) {
				System.out.println("Tipo Inserido Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir o Tipo");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir O Tipo");
			e.printStackTrace();
		}

	}

	private void updateVehicleType(HttpServletRequest request) {
		int idTipoV = Integer.valueOf(request.getParameter("id-tipoV"));
		String descTipoV = request.getParameter("desc-tipoV");
		String statusTipoV = request.getParameter("status-tipoV");
		try {
			POTipoVeiculo tipoV = new POTipoVeiculo();
			CRUDTipoVeiculo crud = new CRUDTipoVeiculo();

			// Definição De Dados Para O OBJ Usuario
			tipoV.setIdTipoV(idTipoV);
			tipoV.setDescTipoV(descTipoV);

			switch (statusTipoV) {
			case "0":
				tipoV.setStatusTipoV(EnumStatus.ATIVO);
				break;
			case "1":
				tipoV.setStatusTipoV(EnumStatus.INATIVO);
				break;
			}

			crud.update(tipoV);

			if (tipoV.getIdTipoV() > 0) {
				System.out.println("Tipo Atualizado Com Sucesso!");
			} else {
				System.out.println("Erro Ao Atualizar o Tipo");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar O Tipo");
			e.printStackTrace();
		}

	}

	private void removeVehicleType(HttpServletRequest request) {
		int idTipoV = Integer.valueOf(request.getParameter("id-tipoV"));

		try {
			POTipoVeiculo tipoV = new POTipoVeiculo();
			CRUDTipoVeiculo crud = new CRUDTipoVeiculo();

			tipoV.setIdTipoV(idTipoV);

			crud.delete(tipoV);
			System.out.println("Tipo Removido Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Tipo");
			e.printStackTrace();
		}

	}

}
