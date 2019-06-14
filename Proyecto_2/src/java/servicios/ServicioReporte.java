/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.gestor.GestorDatos;

/**
 *
 * @author Rodrigo
 */
@WebServlet(name = "ServicioReporte", urlPatterns = {"/ServicioReporte","/ServicioVotacionCerrada"})
public class ServicioReporte extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.InstantiationException
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.IllegalAccessException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getServletPath().equals("/ServicioVotacionCerrada")) {
            try (PrintWriter out = response.getWriter()) {
                   out.println(GestorDatos.obtenerInstancia().obtenerTablaVotacionesCerradas());
            }
        }
         if (request.getServletPath().equals("/ServicioReporte")) {
               try (PrintWriter out = response.getWriter()) {
                   String votacion = request.getParameter("votacion");
                   out.println(GestorDatos.obtenerInstancia().obtenerReporte(votacion));
            }
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
        try {
            processRequest(request, response);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ServicioReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ServicioReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
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
