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
import modelo.gestor.GestorDatos;

@WebServlet(name = "ServicioBorrarDatos", urlPatterns = {"/ServicioBorrarDatos"})
public class ServicioBorrarDatos extends HttpServlet {

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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {

        HttpSession session = request.getSession();
        String datosExportados = (String) session.getAttribute("datosExportados");

        try {
            if (datosExportados.equals("1")) {
                GestorDatos.obtenerInstancia().borrarVotacionUsuarios();
                GestorDatos.obtenerInstancia().borrarVotacionPartidos();
                GestorDatos.obtenerInstancia().borrarUsuarios();
                GestorDatos.obtenerInstancia().borrarVotaciones();
                GestorDatos.obtenerInstancia().borrarPartidos();
                response.sendRedirect("indexAdmin.jsp?status=1");
            } else {
                response.sendRedirect("indexAdmin.jsp?status=2");
            }
        } catch (Exception ex) {
            response.sendRedirect("indexAdmin.jsp?status=3");
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
            Logger.getLogger(ServicioBorrarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioBorrarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioBorrarDatos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioBorrarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioBorrarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioBorrarDatos.class.getName()).log(Level.SEVERE, null, ex);
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
