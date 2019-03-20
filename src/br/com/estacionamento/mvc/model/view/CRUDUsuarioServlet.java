package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.model.crud.CRUDUsuario;
import br.com.estacionamento.mvc.model.persistent_object.POUsuario;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/CRUDUsuarioServlet")
public class CRUDUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CRUDUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		// System.out.println("Operation: " + request.getParameter("operation"));

		switch (operation) {
		case "list":
			System.out.println("CRUD List Operation");
			listUsers(response);
			break;

		case "insert":
			System.out.println("CRUD Insert Operation");
			createUser(request);
			break;

		case "update":
			System.out.println("CRUD Update Operation");
			updateUser(request);
			break;

		case "delete":
			System.out.println("CRUD Delete Operation");
			removeUser(request);
			break;

		}

	}

	private JSONArray listUsers(HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			CRUDUsuario crud = new CRUDUsuario();
			ArrayList<POUsuario> result = crud.list("");

			if (result.size() > 0) {
				for (POUsuario u : result) {
					try {
						arr.put(u.toJSON());
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

	private void createUser(HttpServletRequest request) {
		String nomeUsuario = request.getParameter("nome-usuario");
		String senhaUsuario = request.getParameter("senha-usuario");
		String statusUsuario = request.getParameter("status-usuario");
		try {
			POUsuario usuario = new POUsuario();
			CRUDUsuario crud = new CRUDUsuario();

			// Definição De Dados Para O OBJ Usuario
			usuario.setNomeUsuario(nomeUsuario);
			usuario.setSenhaUsuario(senhaUsuario);

			switch (statusUsuario) {
			case "0":
				usuario.setStatusUsuario(EnumStatus.ATIVO);
				break;
			case "1":
				usuario.setStatusUsuario(EnumStatus.INATIVO);
				break;
			}

			crud.insert(usuario);

			if (usuario.getIdUsuario() > 0) {
				System.out.println("Usuário Inserido Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir O Usuário");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir Usuário");
			e.printStackTrace();
		}

	}

	private void updateUser(HttpServletRequest request) {
		int idUsuario = Integer.valueOf(request.getParameter("id-usuario"));
		String nomeUsuario = request.getParameter("nome-usuario");
		String senhaUsuario = request.getParameter("senha-usuario");
		String statusUsuario = request.getParameter("status-usuario");
		try {
			POUsuario usuario = new POUsuario();
			CRUDUsuario crud = new CRUDUsuario();

			// Definição De Dados Para O OBJ Usuario
			usuario.setIdUsuario(idUsuario);
			usuario.setNomeUsuario(nomeUsuario);
			usuario.setSenhaUsuario(senhaUsuario);

			switch (statusUsuario) {
			case "0":
				usuario.setStatusUsuario(EnumStatus.ATIVO);
				break;
			case "1":
				usuario.setStatusUsuario(EnumStatus.INATIVO);
				break;
			}

			crud.update(usuario);

			if (usuario.getIdUsuario() > 0) {
				System.out.println("Usuário Atualizado Com Sucesso!");
			} else {
				System.out.println("Erro Ao Atualizar O Usuário");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Usuário");
			e.printStackTrace();
		}

	}

	private void removeUser(HttpServletRequest request) {
		int idUsuario = Integer.valueOf(request.getParameter("id-usuario"));
		String nomeUsuario = request.getParameter("nome-usuario");

		try {
			POUsuario usuario = new POUsuario();
			CRUDUsuario crud = new CRUDUsuario();

			usuario.setIdUsuario(idUsuario);

			crud.delete(usuario);
			System.out.println("Usuário Removido Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Usuário");
			e.printStackTrace();
		}

	}

}
