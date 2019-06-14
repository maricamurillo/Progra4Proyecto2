package servicios;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import modelo.entidades.Administrador;
import modelo.entidades.Datos;
import modelo.entidades.Partido;
import modelo.entidades.Usuario;
import modelo.entidades.Votacion;
import modelo.entidades.VotacionPartido;
import modelo.entidades.VotacionUsuario;
import modelo.gestor.GestorDatos;

@WebServlet(name = "ServicioExportarDatos", urlPatterns = {"/ServicioExportarDatos"})
public class ServicioExportarDatos extends HttpServlet {

    private final int ARBITARY_SIZE = 1048;

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
     * @throws javax.xml.bind.JAXBException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException, SQLException, JAXBException {

        List<Partido> partidos = GestorDatos.obtenerInstancia().listarPartidos();
        List<Usuario> usuarios = GestorDatos.obtenerInstancia().listarUsuarios();
        List<Votacion> votaciones = GestorDatos.obtenerInstancia().listarVotaciones();
        List<Administrador> administradores = GestorDatos.obtenerInstancia().listarAdministradores();
        List<VotacionUsuario> votacionUsuario = GestorDatos.obtenerInstancia().listarVotacionUsuario();
        List<VotacionPartido> votacionPartido = GestorDatos.obtenerInstancia().listarVotacionPartido();
        Datos datos = new Datos();
        datos.setPartidos(partidos);
        datos.setUsuarios(usuarios);
        datos.setVotaciones(votaciones);
        datos.setAdministradores(administradores);
        datos.setVotacionUsuario(votacionUsuario);
        datos.setVotacionPartido(votacionPartido);

        JAXBContext jaxbContext = JAXBContext.newInstance(Datos.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(datos, new File(getServletContext().getRealPath("") + "datos.xml"));

        response.setContentType("text/xml");
        response.setHeader("Content-disposition", "attachment; filename=datos.xml");
 
        try(InputStream in = request.getServletContext().getResourceAsStream("datos.xml");
          OutputStream out = response.getOutputStream()) {
 
            byte[] buffer = new byte[ARBITARY_SIZE];
         
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
        
        HttpSession session = request.getSession();  
        session.setAttribute("datosExportados", "1"); 
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
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ServicioExportarDatos.class.getName()).log(Level.SEVERE, null, ex);
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
