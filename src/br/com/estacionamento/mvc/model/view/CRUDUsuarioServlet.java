package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

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

		case "authentication":
			System.out.println("CRUD Authentication Operation");
			loginAuthentication(request, response);
			break;

		case "checkLogin":
			System.out.println("CRUD Check Login Operation");
			checkSession(request, response);
			break;

		case "logout":
			System.out.println("CRUD Logout Operation");
			logout(request);
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
			System.out.println("Erro Pegar Lista Usuï¿½rios");
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
				System.out.println("Usuï¿½rio Inserido Com Sucesso!");
			} else {
				System.out.println("Erro Ao Inserir O Usuï¿½rio");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Inserir Usuï¿½rio");
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

			// Definiï¿½ï¿½o De Dados Para O OBJ Usuario
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
				System.out.println("Usuï¿½rio Atualizado Com Sucesso!");
			} else {
				System.out.println("Erro Ao Atualizar O Usuï¿½rio");
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Usuï¿½rio");
			e.printStackTrace();
		}

	}

	private void removeUser(HttpServletRequest request) {
		int idUsuario = Integer.valueOf(request.getParameter("id-usuario"));

		try {
			POUsuario usuario = new POUsuario();
			CRUDUsuario crud = new CRUDUsuario();

			usuario.setIdUsuario(idUsuario);

			crud.delete(usuario);
			System.out.println("UsuÃ¡rio Removido Com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro Ao Remover Usuï¿½rio");
			e.printStackTrace();
		}

	}

	private void loginAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("user");
		String password = request.getParameter("password");

		// Cria json para o retorno para o js
		JSONObject json = new JSONObject();

		try {
			POUsuario usuario = new POUsuario();
			CRUDUsuario crud = new CRUDUsuario();

			usuario.setNomeUsuario(username);
			usuario.setSenhaUsuario(password);

			response.setContentType("application/json");
			String message = "";
			String urlPath = request.getContextPath();

			if (crud.login(usuario) == true) {
				System.out.println("Login Efetuado!");
				message = "Login Efetuado!";

				System.out.println("Content URL: " + urlPath);
				String url = urlPath + "/menu.jsp";

				json.put("message", message);
				json.put("url", url);

				// Define Os Valores Na Sessão
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10 * 60);

				session.setAttribute("user", username);

				response.getWriter().write(json.toString());

			} else if (crud.login(usuario) == false) {
				System.out.println("Login E/Ou Senha Incorretos!");
				message = "Login E/Ou Senha Incorretos!";
				String url = urlPath + "/autenticar.jsp";
				json.put("message", message);
				json.put("url", url);

				response.getWriter().write(json.toString());
			}

		} catch (Exception e) {
			System.out.println("Erro Ao Efetuar AutenticaÃ§Ã£o Do UsuÃ¡rio");
			e.printStackTrace();
		}

	}

	private boolean checkSession(HttpServletRequest request, HttpServletResponse response) {
		boolean logged = false;
		if (request.getSession().getAttribute("user") != null) {
			String username = request.getSession().getAttribute("user").toString();
			logged = true;
			try {
				response.getWriter().write(username);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (request.getSession().getAttribute("user") == null) {
			logged = false;
		}

		return logged;
	}

	private void logout(HttpServletRequest request) {
		try {
			request.getSession().invalidate();

		} catch (Exception e) {
			System.out.println("Erro Ao Efetuar Logout");
			e.printStackTrace();
		}

	}

}
