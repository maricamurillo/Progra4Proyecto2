package servicios;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nombrePartido = request.getParameter("nombrePartido");
        String siglasPartido = request.getParameter("siglasPartido");
        String cedulaCandidato = request.getParameter("cedulaCandidato");
        String nombreCandidato = request.getParameter("nombreCandidato");
        String apellido1Candidato = request.getParameter("apellido1Candidato");
        String apellido2Candidato = request.getParameter("apellido2Candidato");
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
                    
                    //nombreArchivo, part.getContentType(), part.getInputStream(), (int) part.getSize()
                }
            }
        } catch (IOException | ServletException ex) {
            request.setAttribute("mensaje",
                    String.format("Ocurrió una excepción: '%s'", ex.getMessage()));
        }

        getServletContext().getRequestDispatcher("/registrarVotantes.jsp").forward(request, response);
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
