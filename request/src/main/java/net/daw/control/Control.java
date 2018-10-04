package net.daw.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList respuesta = new ArrayList();
		
		respuesta.addAll(
				Arrays.asList(
						"getLocalPort(): " + request.getLocalPort(),
						"getAuthType(): " + request.getAuthType(),
						"getContextPath(): " + request.getContextPath(),
						request.getCookies(), 
						"getLocalAddr(): " + request.getLocalAddr(),
						"getUserPrincipal(): " + request.getUserPrincipal(),
						"getServerName(): " + request.getServerName(),
						"getRequestURL(): " + request.getRequestURL(),
						"getRemoteAddr(): " + request.getRemoteAddr()));
		Gson gSon = new Gson();
		String str = gSon.toJson(respuesta);
		response.getWriter().write(str);
	}

}
