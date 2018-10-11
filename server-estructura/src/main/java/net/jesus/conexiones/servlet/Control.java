/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jesus.conexiones.servlet;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jesus.conexiones.conexionesespecificas.BoneCpConnection;
import net.jesus.conexiones.conexionesespecificas.C3poConnection;
import net.jesus.conexiones.conexionesespecificas.DbcpConnection;
import net.jesus.conexiones.conexionesespecificas.HikariConnection;
import net.jesus.conexiones.connectioninterface.ConnectionInterface;

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
        response.setContentType("application/json");
        Gson gSon = new Gson();

        String opcion = request.getParameter("opcion");

        ConnectionInterface conexion = null;
        Connection conn;
        switch (opcion) {
            case "bonecp":
                try {
                    conexion = new BoneCpConnection();
                    conn = conexion.newConnection();
                    response.getWriter().append("Conectado con bonecp");
                } catch (Exception e) {
                    response.getWriter().append(e.toString());
                }
                break;

            case "hikari":
                try {
                    conexion = new HikariConnection();
                    conn = conexion.newConnection();
                    response.getWriter().append("Conectado con hikari");
                } catch (Exception e) {
                    response.getWriter().append(e.toString());
                }
                break;
            case "c3po":
                try {
                    conexion = new C3poConnection();
                    conn = conexion.newConnection();
                    response.getWriter().append("Conectado con c3po");
                } catch (Exception e) {
                    response.getWriter().append(e.toString());
                }
                break;

            case "dbcp":
                try {
                    conexion = new DbcpConnection();
                    conn = conexion.newConnection();
                    response.getWriter().append("Conectado con dbcp");

                } catch (Exception e) {
                    response.getWriter().append(e.toString());
                }
                break;
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
