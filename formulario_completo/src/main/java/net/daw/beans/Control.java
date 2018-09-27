package net.daw.beans;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.daw.clases.Celdas;
import net.daw.clases.Fila;

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
		processRequest(request, response);

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

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		Fila fila;
		Celdas celdas;

		ArrayList<Fila> tablaJSON = new ArrayList<Fila>();
		ArrayList filas;
		
		ArrayList<ArrayList> errores = new ArrayList<ArrayList>();
		ArrayList<String> lineaerrores;

		if (request.getParameter("ancho").equals("")) {
			lineaerrores = new ArrayList<String>();
			lineaerrores.add("Error: Ancho vacio");
			errores.add(lineaerrores);
		}
		if (request.getParameter("alto").equals("")) {
			lineaerrores = new ArrayList<String>();
			lineaerrores.add("Error: Alto vacio");
			errores.add(lineaerrores);
		}
		if (!request.getParameter("alto").matches("^[0-9][0-9]*$") && !request.getParameter("alto").equals("")) {
			lineaerrores = new ArrayList<String>();
			lineaerrores.add("Error: Introduce caracteres numericos o positivos en alto");
			errores.add(lineaerrores);
		} 
		if (!request.getParameter("ancho").matches("^[0-9][0-9]*$") && !request.getParameter("ancho").equals("")) {
			lineaerrores = new ArrayList<String>();
			lineaerrores.add("Error: Introduce caracteres numericos o positivos en ancho");
			errores.add(lineaerrores);
		}
		if(errores.isEmpty() == false ) {
			response.setStatus(404);
			Gson gSon = new Gson();
			String str = gSon.toJson(errores);
			response.getWriter().write(str);
		}else {
			//SI LO QUE HE INTRODUCIDO SON NUMEROS ENTRARA AQUI
			
			if (!request.getParameter("ancho").matches("^([1-9][0-9]?|100)$")) {
				if (Integer.parseInt(request.getParameter("ancho")) == 0
						|| Integer.parseInt(request.getParameter("ancho")) < 0) {
					lineaerrores = new ArrayList<String>();
					lineaerrores.add("Error: Introduce un valor mayor que 0 en ancho");
					errores.add(lineaerrores);
				}else {
					lineaerrores = new ArrayList<String>();
					lineaerrores.add("Error: Introduce un valor menor que 101 en ancho");
					errores.add(lineaerrores);						
				}
			}
			if (!request.getParameter("alto").matches("^([1-9][0-9]?|100)$")) {
				if (Integer.parseInt(request.getParameter("alto")) == 0
						|| Integer.parseInt(request.getParameter("alto")) < 0) {
					lineaerrores = new ArrayList<String>();
					lineaerrores.add("Error: Introduce un valor mayor que 0 en alto");
					errores.add(lineaerrores);
				}else {
					lineaerrores = new ArrayList<String>();
					lineaerrores.add("Error: Introduce un valor menor que 101 en alto");
					errores.add(lineaerrores);						
				}
			}
			if(errores.isEmpty() == false ) {
				response.setStatus(404);
				Gson gSon = new Gson();
				String str = gSon.toJson(errores);
				response.getWriter().write(str);
				
			}else {
				
				//SI HA PASADO LAS VALIDACIONES DE ARRIBA QUIERE DECIR QUE LO QUE HE INTRODUCIDO SIN NUMEROS
				//CORRECTOS ASI QUE CREARA LA TABLA
				
				for (int i = 1; i <= Integer.parseInt(request.getParameter("alto")); i++) {

					filas = new ArrayList<Celdas>();

					fila = new Fila();

					for (int j = 1; j <= Integer.parseInt(request.getParameter("ancho")); j++) {
						celdas = new Celdas();
						celdas.setI(i);
						celdas.setJ(j);
						celdas.setR(j * i);

						filas.add(celdas);

					}

					fila.setA(filas);
					fila.setI(i);
					tablaJSON.add(fila);
				}
				Gson gSon = new Gson();
				String str = gSon.toJson(tablaJSON);

				response.getWriter().append(str);
	
			}
						}
	
	}

}
