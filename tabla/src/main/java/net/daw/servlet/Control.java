package net.daw.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

			Integer primero = Integer.parseInt(request.getParameter("primero"));
			Integer segundo = Integer.parseInt(request.getParameter("segundo"));

			Integer resultado = 0;

			BigDecimal value = new BigDecimal(0);

			arrayResultado = new ArrayList();

			switch (accion) {
			default:
				try
				{
				    Thread.sleep((int)(Math.random()*2000));
				    lineaerrores = new ArrayList<String>();
					resultado = primero * segundo;
					lineaerrores.add(resultado.toString());
					errores.add(lineaerrores);
					str = gSon.toJson(errores);
					response.getWriter().write(str);
					break;
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
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
