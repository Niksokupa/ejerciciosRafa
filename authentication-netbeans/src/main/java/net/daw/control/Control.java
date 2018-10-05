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
                            respuesta(200,"Usuario loggeado correctamente",MakePairs(request.getQueryString()),response,request);
                            break;
                        } else {
                            respuesta(401,"Authentication error",MakePairs(request.getQueryString()),response,request);
                            break;
                        }
                    } else {
                        respuesta(401,"Usuario o contraseña vacio",MakePairs(request.getQueryString()),response,request);
                        break;
                    }

                case "logout":
                    sesion.invalidate();
                    respuesta(200,"Sesion cerrada correctamente",MakePairs(request.getQueryString()),response,request);
                    break;

                case "check":
                    if (sesion.getAttribute("sesionUsuario") != null) {
                        respuesta(200,"Usuario loggeado: " + sesion.getAttribute("sesionUsuario").toString(),MakePairs(request.getQueryString()),response,request);
                        break;
                    } else {
                        respuesta(401,"Logeate para ver la sesion",MakePairs(request.getQueryString()),response,request);
                        break;
                    }

                case "secret":
                    if (sesion.getAttribute("sesionUsuario") != null) {
                        respuesta(200,"1236512368512635",MakePairs(request.getQueryString()),response,request);
                        break;
                    } else {
                        respuesta(401,"Logeate para ver codigo secreto",MakePairs(request.getQueryString()),response,request);
                        break;
                    }

                default:
                    respuesta(401, "Opciones validas: loggin/check/secret/logout", MakePairs(request.getQueryString()), response, request);
                    break;

            }
        } else {
            respuesta(401, "Añade una opcion", MakePairs(request.getQueryString()), response, request);
        }
    }
    public void respuesta(Integer status, String msg, Map<String, String> querys,HttpServletResponse response,HttpServletRequest request ) throws IOException {
        Gson gSon = new Gson();
        Json json = new Json();
        
        json.setStatus(status);
        json.setMsg(msg);
        json.setParams(querys);
        response.setStatus(status);
        response.getWriter().append(gSon.toJson(json));
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
        while (it.hasNext()) {
            Map.Entry res = (Map.Entry) it.next();
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
