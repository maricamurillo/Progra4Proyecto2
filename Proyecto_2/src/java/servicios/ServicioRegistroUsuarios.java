package servicios;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import modelo.entidades.Usuario;
import modelo.gestor.GestorDatos;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@WebServlet(name = "ServicioRegistroUsuarios", urlPatterns = {"/ServicioRegistroUsuarios"})
@MultipartConfig()
public class ServicioRegistroUsuarios extends HttpServlet {

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
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        List<Usuario> usuarios = new ArrayList<>();

        try {
            for (Part part : request.getParts()) {
                String nombreArchivo = part.getSubmittedFileName();

                if (nombreArchivo.isEmpty()) {
                    getServletContext().getRequestDispatcher("/registrarUsuarios.jsp?status=3").forward(request, response);
                    break;
                }

                if (GestorDatos.obtenerInstancia().validarFormatoArchivo(nombreArchivo)) {
                    try {
                        if (part.getContentType().contains("csv")) {
                            InputStreamReader input = new InputStreamReader(part.getInputStream());
                            CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);

                            for (CSVRecord record : csvParser) {
                                usuarios.add(new Usuario(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), 0));
                            }
                        } else {
                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder = factory.newDocumentBuilder();
                            Document doc = builder.parse(part.getInputStream());
                            doc.getDocumentElement().normalize();
                            NodeList nodeList = doc.getElementsByTagName("usuario");
                            
                            int length = nodeList.getLength();

                            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                                Node node = nodeList.item(temp);

                                if (node.getNodeType() == Node.ELEMENT_NODE) {
                                    Element element = (Element) node;
                                    usuarios.add(new Usuario(element.getElementsByTagName("cedula").item(0).getTextContent(),
                                            element.getElementsByTagName("nombre").item(0).getTextContent(),
                                            element.getElementsByTagName("apellido1").item(0).getTextContent(),
                                            element.getElementsByTagName("apellido2").item(0).getTextContent(),
                                            element.getElementsByTagName("cedula").item(0).getTextContent(),
                                            0));
                                }
                            }
                        }
                    } catch (Exception ex) {
                        getServletContext().getRequestDispatcher("/registrarUsuarios.jsp?status=2").forward(request, response);
                        break;
                    }

                    GestorDatos.obtenerInstancia().insertarUsuarios(usuarios);
                    getServletContext().getRequestDispatcher("/registrarUsuarios.jsp?status=1").forward(request, response);
                    break;

                } else {
                    getServletContext().getRequestDispatcher("/registrarUsuarios.jsp?status=4").forward(request, response);
                    break;
                }
            }
        } catch (IOException | ServletException ex) {
            getServletContext().getRequestDispatcher("/registrarUsuarios.jsp?status=2").forward(request, response);
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
            Logger.getLogger(ServicioRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicioRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
