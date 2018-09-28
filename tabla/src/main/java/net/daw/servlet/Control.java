package net.daw.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Control
 */
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Control() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json");
		String accion = request.getParameter("accion");

		String regex = "^-?[0-9]+([.][0-9]+)?$";

		ArrayList<ArrayList> errores = new ArrayList<ArrayList>();
		ArrayList<String> lineaerrores;
		ArrayList<Double> arrayResultado;

		if (request.getParameter("primero").equals("")) {
			lineaerrores = new ArrayList<String>();
			lineaerrores.add("Error: primero vacio");
			errores.add(lineaerrores);
		}
		if (request.getParameter("segundo").equals("")) {
			lineaerrores = new ArrayList<String>();
			lineaerrores.add("Error: segundo vacio");
			errores.add(lineaerrores);
		}
		if (!request.getParameter("primero").matches(regex) && !request.getParameter("primero").equals("")) {
			lineaerrores = new ArrayList<String>();
			lineaerrores.add("Error: Introduce caracteres numericos o decimales sin comas en primero");
			errores.add(lineaerrores);
		}
		if (!request.getParameter("segundo").matches(regex) && !request.getParameter("segundo").equals("")) {
			lineaerrores = new ArrayList<String>();
			lineaerrores.add("Error: Introduce caracteres numericos o decimales sin comas en segundo");
			errores.add(lineaerrores);
		}
		if (errores.isEmpty() == false) {
			response.setStatus(404);
			Gson gSon = new Gson();
			String str = gSon.toJson(errores);
			response.getWriter().write(str);
		} else {
			// SI HA PASADO LAS VALIDACIONES DE ARRIBA QUIERE DECIR QUE LO QUE HE
			// INTRODUCIDO SIN NUMEROS
			// CORRECTOS ASI QUE CREARA LA TABLA
			Gson gSon = new Gson();
			String str = "";

			Double primero = Double.parseDouble(request.getParameter("primero"));
			Double segundo = Double.parseDouble(request.getParameter("segundo"));

			Double resultado = 0.0;

			BigDecimal value = new BigDecimal(0);

			arrayResultado = new ArrayList();

			switch (accion) {
			case "Suma":
				resultado = primero + segundo;
				arrayResultado.add(resultado);
				str = gSon.toJson(arrayResultado);
				response.getWriter().append(str);
				break;
			case "Resta":
				resultado = primero - segundo;
				arrayResultado.add(resultado);
				str = gSon.toJson(arrayResultado);
				response.getWriter().append(str);
				break;
			case "Multiplicacion":
				resultado = primero * segundo;
				arrayResultado.add(resultado);
				str = gSon.toJson(arrayResultado);
				response.getWriter().append(str);
				break;
			case "Division":
				if (segundo == 0) {
					response.setStatus(404);
					lineaerrores = new ArrayList<String>();
					lineaerrores.add("Error: El segundo operando no puede ser 0");
					errores.add(lineaerrores);
					str = gSon.toJson(errores);
					response.getWriter().write(str);
					break;
				} else {
					resultado = primero / segundo;
					arrayResultado.add(resultado);
					str = gSon.toJson(arrayResultado);
					response.getWriter().append(str);
					break;
				}

			default:
				response.setStatus(404);
				lineaerrores = new ArrayList<String>();
				lineaerrores.add("Error: Selecciona una accion");
				errores.add(lineaerrores);
				str = gSon.toJson(errores);
				response.getWriter().write(str);
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
