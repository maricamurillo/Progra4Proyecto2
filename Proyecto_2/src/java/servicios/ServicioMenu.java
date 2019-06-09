
/*

    ServicioMenu.java

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez

 */


package servicios;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "servicioMenu", urlPatterns = {"/servicioMenu","/salir","/registrarUsuarios","/registrarPartidos","/registrarVotaciones","/registrarCandidatos","/registrarAdministradores","/reportes","/voto"})
public class ServicioMenu extends HttpServlet {

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
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getServletPath().equals("/salir")){
            HttpSession sesion = request.getSession(true);
            String usuario = (String)sesion.getAttribute("usuario");
            sesion.invalidate();
            response.sendRedirect("index.jsp");
        }
        if(request.getServletPath().equals("/registrarUsuarios")){
            response.sendRedirect("registrarUsuarios.jsp");
        }
        if(request.getServletPath().equals("/registrarPartidos")){
             response.sendRedirect("registrarPartido.jsp");
        }
        if(request.getServletPath().equals("/registrarVotaciones")){
             response.sendRedirect("registrarVotacion.jsp");
        }
        if(request.getServletPath().equals("/registrarCandidatos")){
             response.sendRedirect("registrarCandidato.jsp");
        }
        if(request.getServletPath().equals("/registrarAdministradores")){
             response.sendRedirect("registrarAdministrador.jsp");
        }
        if(request.getServletPath().equals("/reportes")){
             response.sendRedirect("reportes.jsp");
        }
        if(request.getServletPath().equals("/voto")){
             response.sendRedirect("voto.jsp");
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
        } catch (InstantiationException ex) {
            Logger.getLogger(ServicioMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioMenu.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (InstantiationException ex) {
            Logger.getLogger(ServicioMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioMenu.class.getName()).log(Level.SEVERE, null, ex);
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
