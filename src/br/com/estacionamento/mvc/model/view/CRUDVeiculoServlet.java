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
import br.com.estacionamento.mvc.model.crud.CRUDVeiculo;
import br.com.estacionamento.mvc.model.persistent_object.POMarca;
import br.com.estacionamento.mvc.model.persistent_object.POModelo;
import br.com.estacionamento.mvc.model.persistent_object.POTipoVeiculo;
import br.com.estacionamento.mvc.model.persistent_object.POVeiculo;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumCor;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/CRUDVeiculoServlet")
public class CRUDVeiculoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CRUDVeiculoServlet() {
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
			listVehicles(response);
			break;

		case "insert":
			System.out.println("CRUD Insert Operation");
			createVehicle(request);
			break;

		case "update":
			System.out.println("CRUD Update Operation");
			updateVehicle(request);
			break;

		case "delete":
			System.out.println("CRUD Delete Operation");
			removeVehicle(request);
			break;

		}

	}

	// LIST
	private JSONArray listVehicles(HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			CRUDVeiculo crud = new CRUDVeiculo();
			ArrayList<POVeiculo> result = crud.list("");

			if (result.size() > 0) {
				for (POVeiculo v : result) {
					try {
						arr.put(v.toJSON());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(arr.toString());

		} catch (Exception e) {
			System.out.println("Erro Pegar Lista Veiculos");
			e.printStackTrace();
		}
		return arr;

	}

	// INSERT
	private void createVehicle(HttpServletRequest request) {
		String placaVeiculo = request.getParameter("placa-veiculo");
		int idModelo = Integer.valueOf(request.getParameter("id-modelo"));
		String corVeiculo = request.getParameter("cor-veiculo");
		String statusVeiculo = request.getParameter("status-veiculo");
		try {
			POVeiculo veiculo = new POVeiculo();
			POModelo modelo = new POModelo();

			CRUDVeiculo crud = new CRUDVeiculo();

			modelo.setIdModelo(idModelo);
			// Definição De Dados Para O OBJ Usuario
			veiculo.setPlacaVeiculo(placaVeiculo);
			veiculo.setIdModelo(modelo);

			switch (statusVeiculo) {
			case "0":
				veiculo.setStatusVeiculo(EnumStatus.ATIVO);
				break;
			case "1":
				veiculo.setStatusVeiculo(EnumStatus.INATIVO);
				break;
			}

			switch (corVeiculo) {
			case "0":
				veiculo.setCorVeiculo(EnumCor.PRETO);
				break;
			case "1":
				veiculo.setCorVeiculo(EnumCor.BRANCO);
				break;
			case "2":
				veiculo.setCorVeiculo(EnumCor.PRATA);
				break;
			case "3":
				veiculo.setCorVeiculo(EnumCor.VERMELHO);
				break;
			}

			crud.insert(veiculo);

			if (modelo.getIdModelo() > 0) {
				System.out.println("Veiculo Inserido Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir O Veiculo");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir Veiculo");
			e.printStackTrace();
		}

	}

	// UPDATE
	private void updateVehicle(HttpServletRequest request) {
		int idVeiculo = Integer.valueOf(request.getParameter("id-veiculo"));
		String placaVeiculo = request.getParameter("placa-veiculo");
		int idModelo = Integer.valueOf(request.getParameter("id-modelo"));
		String corVeiculo = request.getParameter("cor-veiculo");
		String statusVeiculo = request.getParameter("status-veiculo");

		try {
			POVeiculo veiculo = new POVeiculo();
			POModelo modelo = new POModelo();

			CRUDVeiculo crud = new CRUDVeiculo();

			modelo.setIdModelo(idModelo);

			veiculo.setIdVeiculo(idVeiculo);
			veiculo.setIdModelo(modelo);
			veiculo.setPlacaVeiculo(placaVeiculo);

			switch (statusVeiculo) {
			case "0":
				veiculo.setStatusVeiculo(EnumStatus.ATIVO);
				break;
			case "1":
				veiculo.setStatusVeiculo(EnumStatus.INATIVO);
				break;
			}

			switch (corVeiculo) {
			case "0":
				veiculo.setCorVeiculo(EnumCor.PRETO);
				break;
			case "1":
				veiculo.setCorVeiculo(EnumCor.BRANCO);
				break;
			case "2":
				veiculo.setCorVeiculo(EnumCor.PRATA);
				break;
			case "3":
				veiculo.setCorVeiculo(EnumCor.VERMELHO);
				break;
			}

			crud.update(veiculo);

			System.out.println("Veiculo Atualizado Com Sucesso!");

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Veiculo");
			e.printStackTrace();
		}

	}

	private void removeVehicle(HttpServletRequest request) {
		int idVeiculo = Integer.valueOf(request.getParameter("id-veiculo"));

		try {
			POVeiculo veiculo = new POVeiculo();
			CRUDVeiculo crud = new CRUDVeiculo();

			veiculo.setIdVeiculo(idVeiculo);
			crud.delete(veiculo);
			System.out.println("Veiculo Removido Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Veiculo");
			e.printStackTrace();
		}

	}

}
