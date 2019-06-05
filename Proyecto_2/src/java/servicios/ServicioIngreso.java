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


@WebServlet(name = "ServicioIngreso", urlPatterns = {"/ServicioIngreso", "/ServicioIngresoUsuario", "/ServicioIngresoAdministrador"})
public class ServicioIngreso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {
        response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
        if (request.getServletPath().equals("/ServicioIngresoUsuario")) {
            boolean usuarioValido = false;
            String usuario = request.getParameter("campoId");
            String password = request.getParameter("campoClave");

            if (usuario != null && password != null) {
                usuarioValido = GestorDatos.obtenerInstancia().verificarUsuario(usuario, password, "usuario");
            }

            if (usuarioValido) {
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuario", usuario);
                sesion.setAttribute("control", 1);
                request.getSession(true).setMaxInactiveInterval(60 * 5);
                response.sendRedirect("voto.jsp");
            } else {
                response.sendRedirect("errorIngreso.jsp");
            }

        }
        if (request.getServletPath().equals("/ServicioIngresoAdministrador")) {
            boolean usuarioValido = false;
            String usuario = request.getParameter("campoId");
            String password = request.getParameter("campoClave");

            if (usuario != null && password != null) {
                usuarioValido = GestorDatos.obtenerInstancia().verificarUsuario(usuario, password, "administrador");
            }

            if (usuarioValido) {
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuario", usuario);
                sesion.setAttribute("control", 1);
                request.getSession(true).setMaxInactiveInterval(60 * 5);
                response.sendRedirect("indexAdmin.jsp");
            } else {
                response.sendRedirect("errorIngreso.jsp");
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
        // No permite que los datos del usuario sean recibidos por
        // medio de GET.
        response.sendRedirect("errorIngreso.jsp");
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
            Logger.getLogger(ServicioIngreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioIngreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioIngreso.class.getName()).log(Level.SEVERE, null, ex);
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
