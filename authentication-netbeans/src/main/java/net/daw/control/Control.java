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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.daw.clases.Json;
import org.omg.CORBA.NameValuePair;

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

        MakePairs(request.getQueryString());

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
                            json.setParams(MakePairs(request.getQueryString()));
                            response.setStatus(200);
                            response.getWriter().append(gSon.toJson(json));
                            break;
                        } else {
                            json.setStatus(401);
                            json.setMsg("Authentication error");
                            json.setParams(MakePairs(request.getQueryString()));
                            response.setStatus(401);
                            response.getWriter().append(gSon.toJson(json));
                            break;
                        }
                    } else {
                        json.setStatus(401);
                        json.setMsg("Usuario o contraseña vacio");
                        json.setParams(MakePairs(request.getQueryString()));
                        response.setStatus(401);
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    }

                case "logout":
                    sesion.invalidate();
                    json.setStatus(200);
                    json.setMsg("Sesion cerrada correctamente");
                    json.setParams(MakePairs(request.getQueryString()));
                    response.setStatus(200);
                    response.getWriter().append(gSon.toJson(json));
                    break;

                case "check":
                    if (sesion.getAttribute("sesionUsuario") != null) {
                        json.setStatus(200);
                        json.setMsg("Usuario loggeado: " + sesion.getAttribute("sesionUsuario").toString());
                        json.setParams(MakePairs(request.getQueryString()));
                        response.setStatus(200);
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    } else {
                        json.setStatus(401);
                        json.setMsg("Logeate para ver la sesion");
                        json.setParams(MakePairs(request.getQueryString()));
                        response.setStatus(401);
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    }

                case "secret":
                    if (sesion.getAttribute("sesionUsuario") != null) {
                        json.setStatus(200);
                        json.setMsg("1236512368512635");
                        json.setParams(MakePairs(request.getQueryString()));
                        response.setStatus(200);
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    } else {
                        json.setStatus(401);
                        json.setMsg("Logeate para ver codigo secreto");
                        json.setParams(MakePairs(request.getQueryString()));
                        response.setStatus(401);
                        response.getWriter().append(gSon.toJson(json));
                        break;
                    }

                default:
                    json.setStatus(401);
                    json.setMsg("Añade una opcion");
                    json.setParams(MakePairs(request.getQueryString()));
                    response.setStatus(401);
                    response.getWriter().append(gSon.toJson(json));
                    break;

            }
        } else {
            json.setStatus(401);
            json.setMsg("Añade una opcion");
            json.setParams(MakePairs(request.getQueryString()));
            response.setStatus(401);
            response.getWriter().append(gSon.toJson(json));
        }

    }

    //Fuente:
    // https://codereview.stackexchange.com/questions/175332/splitting-url-query-string-to-key-value-pairs
    public static Map<String, String> MakePairs(String input) {
        ArrayList<String> querys = new ArrayList<>();
        Map<String, String> retVal = new HashMap<>();
        int fromIndex = 0;
        int toIndex = 0;
        while (toIndex != -1) {
            String key = "";
            String value = "";
            toIndex = input.indexOf('=', fromIndex);
            if (toIndex - fromIndex > 1) {
                key = input.substring(fromIndex, toIndex);
                fromIndex = toIndex + 1;
                toIndex = input.indexOf('&', fromIndex);
                if (toIndex == -1) {
                    value = input.substring(fromIndex, input.length());
                } else {
                    value = input.substring(fromIndex, toIndex);
                }
                retVal.put(key, value);
                fromIndex = toIndex + 1;
            } else {
                fromIndex = input.indexOf('&', toIndex) + 1;
            }
        }
        Iterator it = retVal.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry res = (Map.Entry)it.next();
            querys.add(res.getKey() + " = " + res.getValue());
        }
        return retVal;
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
