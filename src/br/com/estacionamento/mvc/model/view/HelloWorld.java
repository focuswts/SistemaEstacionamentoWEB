package br.com.estacionamento.mvc.model.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.estacionamento.mvc.model.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HelloWorld() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String nome = request.getParameter("tf_estado");
		String uf = request.getParameter("tf_uf");

		out.println("<html>");
		out.println("<body>");

		try {
			POEstado e = new POEstado();
			CRUDEstado crudEstado = new CRUDEstado();
			e.setNomeEstado(nome);
			e.setSiglaEstado(uf);

			switch (request.getParameter("slc_status")) {
			case "0":
				e.setStatusEstado(EnumStatus.ATIVO);
				break;
			case "1":
				e.setStatusEstado(EnumStatus.INATIVO);
				break;
			}

			crudEstado.insert(e);

			if (e.getIdEstado() > 0) {
				response.sendRedirect(request.getContextPath() + "/sucesso.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/erro.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/erro.jsp");
		}
		// out.println("<h1> Sua Nome é: " + nome + "</h1>" );
		// out.println("<h1> Sua idade é: " + (2019 - idade) + " anos</h1>" );
		out.println("</body>");
		out.println("</html>");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}