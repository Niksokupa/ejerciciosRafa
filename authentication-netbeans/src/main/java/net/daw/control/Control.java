/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.control;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.daw.clases.Json;

/**
 *
 * @author jesus
 */
public class Control extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession();
        Gson gSon = new Gson();

        Json json = new Json();

        String opcion = request.getParameter("opcion");
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");

        HttpSession sesionUsuario;

        if (opcion != null) {
            switch (opcion) {
                case "loggin":
                    if (usuario != null && pass != null) {
                        if (usuario.equals("jesus") && pass.equals("jesus")) {
                            //Creo la sesion
                            sesion.setAttribute("sesionUsuario", usuario);

                            //Añado el mensaje al json
                            json.setStatus(200);
                            json.setMsg("Usuario loggeado correctamente");
                            response.getWriter().append(gSon.toJson(json));
                            break;
                        } else {
                            json.setStatus(401);
                            json.setMsg("Authentication error");
                            response.getWriter().append(gSon.toJson(json));
                            break;
                        }
                    } else {
                        json.setStatus(401);
                        json.setMsg("Usuario o contraseña vacio");
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    }

                case "logout":
                    sesion.invalidate();
                    json.setStatus(200);
                    json.setMsg("Sesion cerrada correctamente");
                    response.getWriter().append(gSon.toJson(json));
                    break;

                case "check":
                    if (sesion.getAttribute("sesionUsuario") != null) {
                        json.setStatus(200);
                        json.setMsg(sesion.getAttribute("sesionUsuario").toString());
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    } else {
                        json.setStatus(401);
                        json.setMsg("Logeate para ver la sesion");
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    }

                case "secret":
                    if (sesion.getAttribute("sesionUsuario") != null) {
                        json.setStatus(200);
                        json.setMsg("1236512368512635");
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    } else {
                        json.setStatus(401);
                        json.setMsg("Logeate para ver codigo secreto");
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    }

                default:
                    json.setStatus(401);
                    json.setMsg("Selecciona una opcion");
                    response.getWriter().append(gSon.toJson(json));
                    break;

            }
        } else {
            json.setStatus(401);
            json.setMsg("Añade una opcion");
            response.getWriter().append(gSon.toJson(json));
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
