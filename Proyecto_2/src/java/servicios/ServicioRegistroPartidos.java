package servicios;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.entidades.Partido;
import modelo.gestor.GestorDatos;

@WebServlet(name = "ServicioRegistroPartidos", urlPatterns = {"/ServicioRegistroPartidos"})
@MultipartConfig()
public class ServicioRegistroPartidos extends HttpServlet {

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

        String nombre = request.getParameter("nombre");
        String siglas = request.getParameter("siglas");
        String observaciones = request.getParameter("observaciones");

        try {
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    String nombreArchivo = part.getSubmittedFileName();

                    if (nombreArchivo.isEmpty()) {
                        request.setAttribute("mensaje",
                                "Se omitió la selección del archivo.");
                        break;
                    }

                    if (GestorDatos.obtenerInstancia().validarFormatoImagen(nombreArchivo)) {
                        try {
                            GestorDatos.obtenerInstancia().insertarPartido(new Partido(siglas, nombre, "", part.getContentType(), observaciones), part.getInputStream(), (int) part.getSize());
                            break;
                        } catch (Exception ex) {
                            request.setAttribute("mensaje",
                                    String.format("Excepción: '%s'", ex.getMessage()));
                        }
                    } else {
                        request.setAttribute("mensaje",
                                "El formato del archivo es incorrecto.");
                        break;
                    }
                }
            }
        } catch (IOException | ServletException ex) {
            request.setAttribute("mensaje",
                    String.format("Ocurrió una excepción: '%s'", ex.getMessage()));
        }

        getServletContext().getRequestDispatcher("/registrarPartidos.jsp?status=1").forward(request, response);
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
            Logger.getLogger(ServicioRegistroPartidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioRegistroPartidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioRegistroPartidos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioRegistroPartidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioRegistroPartidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioRegistroPartidos.class.getName()).log(Level.SEVERE, null, ex);
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
