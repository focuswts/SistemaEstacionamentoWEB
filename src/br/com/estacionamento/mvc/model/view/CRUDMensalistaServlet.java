package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.model.crud.CRUDMensalista;
import br.com.estacionamento.mvc.model.persistent_object.POMensalista;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/CRUDMensalistaServlet")
public class CRUDMensalistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CRUDMensalistaServlet() {
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
			listMembers(response);
			break;

		case "insert":
			System.out.println("CRUD Insert Operation");
			createMember(request);
			break;

		case "update":
			System.out.println("CRUD Update Operation");
			updateMember(request);
			break;

		case "delete":
			System.out.println("CRUD Delete Operation");
			removeMember(request);
			break;

		}

	}

	// LIST
	private JSONArray listMembers(HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			CRUDMensalista crud = new CRUDMensalista();
			ArrayList<POMensalista> result = crud.list("");

			if (result.size() > 0) {
				for (POMensalista m : result) {
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
			System.out.println("Erro Pegar Lista Mensalistas");
			e.printStackTrace();
		}
		return arr;

	}

	// INSERT
	private void createMember(HttpServletRequest request) {
		String nomeMensalista = request.getParameter("nome-mensalista");
		String cpfMensalista = request.getParameter("cpf-mensalista");
		String statusMensalista = request.getParameter("status-mensalista");
		try {
			POMensalista mensalista = new POMensalista();
			CRUDMensalista crud = new CRUDMensalista();

			// Defini��o De Dados Para O OBJ Usuario
			mensalista.setNomeMensalista(nomeMensalista);
			mensalista.setCpfMensalista(cpfMensalista);
			
			switch (statusMensalista) {
			case "0":
				mensalista.setStatusMensalista(EnumStatus.ATIVO);
				break;
			case "1":
				mensalista.setStatusMensalista(EnumStatus.INATIVO);
				break;
			}

			crud.insert(mensalista);

			if (mensalista.getIdMensalista() > 0) {
				System.out.println("Mensalista Criado Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir Mensalista");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir Mensalista");
			e.printStackTrace();
		}

	}

	// Update
	private void updateMember(HttpServletRequest request) {
		int idMensalista = Integer.valueOf(request.getParameter("id-mensalista"));
		String nomeMensalista = request.getParameter("nome-mensalista");
		String cpfMensalista = request.getParameter("cpf-mensalista");
		String statusMensalista = request.getParameter("status-mensalista");
		try {
			POMensalista mensalista = new POMensalista();
			CRUDMensalista crud = new CRUDMensalista();

			// Defini��o De Dados Para O OBJ Usuario
			mensalista.setIdMensalista(idMensalista);
			mensalista.setNomeMensalista(nomeMensalista);
			mensalista.setCpfMensalista(cpfMensalista);

			switch (statusMensalista) {
			case "0":
				mensalista.setStatusMensalista(EnumStatus.ATIVO);
				break;
			case "1":
				mensalista.setStatusMensalista(EnumStatus.INATIVO);
				break;
			}

			crud.update(mensalista);

			if (mensalista.getIdMensalista() > 0) {
				System.out.println("Mensalista Atualizado Com Sucesso!");
			} else {
				System.out.println("Erro Ao Atualizar Mensalista");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Mensalista");
			e.printStackTrace();
		}

	}

	private void removeMember(HttpServletRequest request) {
		int idMensalista = Integer.valueOf(request.getParameter("id-mensalista"));

		try {
			POMensalista mensalista = new POMensalista();
			CRUDMensalista crud = new CRUDMensalista();

			mensalista.setIdMensalista(idMensalista);

			crud.delete(mensalista);
			System.out.println("Mensalista Removido Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Mensalista");
			e.printStackTrace();
		}

	}

}
