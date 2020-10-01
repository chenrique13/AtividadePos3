package m02aula06.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m02aula06.basica.Cliente;
import m02aula06.model.RNClienteAlterar;
import m02aula06.model.RNClienteApagar;
import m02aula06.model.RNClienteConsultar;
import m02aula06.model.RNClienteListar;
import m02aula06.model.RNClienteSalvar;

@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at GET: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Salvar os dados no BD
		if (request.getParameter("action").equals("salvar")) {
			Cliente cliente = new Cliente();
			cliente.setNome(request.getParameter("nome"));
			cliente.setCpf(request.getParameter("cpf"));
			cliente.setEmail(request.getParameter("email"));

			try {
				// Salva os dados
				RNClienteSalvar rnSalva = new RNClienteSalvar();
				rnSalva.salvar(cliente);

				// Pego a lista de clientes para exibir na saída
				RNClienteListar rnLista = new RNClienteListar();
				ArrayList<Cliente> lista = (ArrayList) rnLista.listar();
				request.setAttribute("clientes", lista);

				// Redireciona a saída
				RequestDispatcher view = request.getRequestDispatcher("Listar_Cliente.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				response.getWriter().append("Erro: No ClienteController em Salvar" + e.getMessage());
			}
		}

		if (request.getParameter("action").equals("listar")) {
			try {
				RNClienteListar lista = new RNClienteListar();
				ArrayList<Cliente> nLista = (ArrayList) lista.listar();
				request.setAttribute("clientes", nLista);

				RequestDispatcher view = request.getRequestDispatcher("Listar_Cliente.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				response.getWriter().append("Erro: ClienteController em Listar " + e.getMessage());
			}
		}

		if (request.getParameter("action").equals("consultar")) {
			Cliente cliente = new Cliente();

			cliente.setCpf(request.getParameter("cpf"));

			try {
				Cliente consulta = new RNClienteConsultar().consultar(cliente);

				request.setAttribute("consulta", consulta);

				RequestDispatcher view = request.getRequestDispatcher("Consultar_Cliente.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				response.getWriter().append("Erro: ClienteController consultar " + e.getMessage());
			}
		}

		if (request.getParameter("action").equals("apagar")) {
			Cliente cliente = new Cliente();

			cliente.setCpf(request.getParameter("cpf"));
			cliente.setEmail(request.getParameter("email"));

			try {
				RNClienteApagar apaga = new RNClienteApagar();
				apaga.apagar(cliente);

				request.setAttribute("exclusao", cliente);

				RequestDispatcher view = request.getRequestDispatcher("Apagar_Cliente.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				response.getWriter().append("Erro: ClienteController apagar " + e.getMessage());
			}
		}

		if (request.getParameter("action").equals("alterar")) {
			Cliente cliente = new Cliente();

			cliente.setCpf(request.getParameter("cpf"));

			try {
				Cliente consulta = new RNClienteConsultar().consultar(cliente);

				request.setAttribute("consulta", consulta);

				RequestDispatcher view = request.getRequestDispatcher("Alterar_Cliente.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				response.getWriter().append("Erro: ClienteController em alterar " + e.getMessage());
			}
		}

		if (request.getParameter("action").equals("atualizar")) {
			Cliente cliente = new Cliente();

			cliente.setId(Integer.parseInt(request.getParameter("id")));
			cliente.setCpf(request.getParameter("cpf"));
			cliente.setNome(request.getParameter("nome"));
			cliente.setEmail(request.getParameter("email"));

			try {
				Cliente atualiza = new RNClienteAlterar().atualizar(cliente);

				request.setAttribute("consulta", atualiza);

				RequestDispatcher view = request.getRequestDispatcher("Consultar_Cliente.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				response.getWriter().append("Erro: ClienteController em atualizar " + e.getMessage());
			}
		}

	}
}
