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
import modelo.entidades.VotacionPartido;
import modelo.gestor.GestorDatos;

@WebServlet(name = "ServicioRegistroCandidatos", urlPatterns = {"/ServicioRegistroCandidatos"})
@MultipartConfig()
public class ServicioRegistroCandidatos extends HttpServlet {

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
        
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
        String partido = request.getParameter("partido");
        String votacion = request.getParameter("votacion");
        
        VotacionPartido votacionPartido = new VotacionPartido();
        votacionPartido.getCandidato().setCedula(cedula);
        votacionPartido.getCandidato().setNombre(nombre);
        votacionPartido.getCandidato().setApellido1(apellido1);
        votacionPartido.getCandidato().setApellido2(apellido2);
        votacionPartido.getCandidato().setClave(cedula);
        votacionPartido.getPartido().setSiglas(partido);
        votacionPartido.getVotacion().setId(Integer.parseInt(votacion));
        
        try {
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    String nombreArchivo = part.getSubmittedFileName();

                    if (nombreArchivo.isEmpty()) {
                        break;
                    }

                    if (GestorDatos.obtenerInstancia().validarFormatoImagen(nombreArchivo)) {
                        try {
                            votacionPartido.setTipoImagen(part.getContentType());
                            GestorDatos.obtenerInstancia().insertarUsuario(votacionPartido.getCandidato());
                            GestorDatos.obtenerInstancia().insertarVotacionPartido(votacionPartido, part.getInputStream(), (int) part.getSize());
                            getServletContext().getRequestDispatcher("/registrarCandidato.jsp?status=1").forward(request, response);
                        } catch (Exception ex) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException | ServletException ex) {
        }

        getServletContext().getRequestDispatcher("/registrarCandidato.jsp?status=2").forward(request, response);
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
            Logger.getLogger(ServicioRegistroCandidatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioRegistroCandidatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioRegistroCandidatos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioRegistroCandidatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioRegistroCandidatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioRegistroCandidatos.class.getName()).log(Level.SEVERE, null, ex);
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
