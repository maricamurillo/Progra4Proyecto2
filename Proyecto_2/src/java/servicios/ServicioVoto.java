/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.Votacion;
import modelo.gestor.GestorDatos;

/**
 *
 * @author Rodrigo
 */
@WebServlet(name = "ServicioVoto", urlPatterns = {"/ServicioVoto","/ServicioUsuarioEstatus","/ServicioVotacionEstatus","/ServicioVotoEstatus","/ServicioVotoPartidos"})
public class ServicioVoto extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
         if (request.getServletPath().equals("/ServicioVoto")) {
            String usuario = (String)request.getSession(true).getAttribute("usuario");
            String votacion = (String)request.getSession(true).getAttribute("votacion");
            String partido = request.getParameter("partido");
            GestorDatos.obtenerInstancia().insertarUsuarioVoto(votacion, usuario, 1);
            GestorDatos.obtenerInstancia().actualizaVoto(votacion, partido);
            response.sendRedirect("voto.jsp");
            
         }
        if (request.getServletPath().equals("/ServicioUsuarioEstatus")) {
            try (PrintWriter out = response.getWriter()) {
                String cedula = (String)request.getSession(true).getAttribute("usuario");
                boolean activo= GestorDatos.obtenerInstancia().verificaEstatusUsuario(cedula);
                out.println(activo);      
            }
        }
        if(request.getServletPath().equals("/ServicioVotacionEstatus")){
            try (PrintWriter out = response.getWriter()) {
                boolean activo = true;
                String f = GestorDatos.obtenerInstancia().controlFecha();
               
                /////////////////////////////////////////////////
                   GestorDatos.obtenerInstancia().obtenerReporte("3");
                   //////////////////////////////////////////////////
                List<Votacion> lista = GestorDatos.obtenerInstancia().listarVotaciones("2");
                if(lista.isEmpty()){
                    activo=false;
                }
                else{
                    for (Votacion votacion : lista) {
                        HttpSession sesion = request.getSession(true);
                        String id = String.valueOf(votacion.getId());
                        sesion.setAttribute("votacion",id);
                        
                    }
                }
             
                out.println(activo);      
            }
        }
        if(request.getServletPath().equals("/ServicioVotoEstatus")){
             boolean voto = false;
       
            try (PrintWriter out = response.getWriter()) {
                 String usuario = (String)request.getSession(true).getAttribute("usuario");
            
                 String votacion = (String)request.getSession(true).getAttribute("votacion");
                 
                 String v="1";
                 voto = GestorDatos.obtenerInstancia().verificarVoto(usuario, votacion, v);
          
                out.println(voto);      
            }
        }
        if(request.getServletPath().equals("/ServicioVotoPartidos")){
            try (PrintWriter out = response.getWriter()) {
                out.println(GestorDatos.obtenerInstancia().obtenerTablaVotar("1"));      
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
            Logger.getLogger(ServicioVoto.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioVoto.class.getName()).log(Level.SEVERE, null, ex);
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
