package modelo.gestor;

import cr.ac.database.managers.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.entidades.Votacion;
import modelo.entidades.Usuario;

public class GestorDatos {

    private GestorDatos()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }

    public static GestorDatos obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorDatos();
        }
        return instancia;
    }
    public boolean verificaEstatusUsuario(String usuario){
        boolean activo=false;
         try {
            Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            try (PreparedStatement stm = cnx.prepareStatement(CMD_ESTATUS_VOTANTE)) {
                stm.clearParameters();
                stm.setString(1, usuario);
                ResultSet rs = stm.executeQuery();
                activo = rs.next();
               
            }
            
         }catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        } 
        return activo;
        
    }
    public boolean verificarUsuario(String usuario, String clave, String tipo) {
        boolean encontrado = false;
        try {
            Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            String x = "";
            if ("usuario".equals(tipo)) {
                x = CMD_VERIFICAR_USUARIO;
            }
            if ("administrador".equals(tipo)) {
                x = CMD_VERIFICAR_ADMINISTRADOR;
            }
            try (PreparedStatement stm = cnx.prepareStatement(x)) {
                stm.clearParameters();
                stm.setString(1, usuario);
                stm.setString(2, clave);
                ResultSet rs = stm.executeQuery();
              
                encontrado = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
        return encontrado;
    }
    public List<Votacion> listarVotaciones(String estados) throws SQLException{
        List<Votacion> lista = new ArrayList<>();
        Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
         try (PreparedStatement stm = cnx.prepareStatement(CMD_VOTACIO_ESTADO)) {
                stm.clearParameters();
                stm.setString(1, estados);
                ResultSet rs = stm.executeQuery();
    
                while (rs.next()) {
                    int id = rs.getInt("id_votacion");
                    Date fechaInicio = rs.getDate("fecha_inicio");
                    Date fechaApertura = rs.getDate("fecha_apertura");
                    Date fechaCierre = rs.getDate("fecha_cierre");
                    Date fechaFinal = rs.getDate("fecha_final");
                    int estado = rs.getInt("estado");
                    Votacion x = new Votacion(id, fechaInicio, fechaApertura, fechaCierre, fechaFinal,estado);
                   
                    
                    lista.add(x);
                }
        }
        
        return lista;
       
    }
     public boolean cambiarClave(String id, String clave) {
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_CAMBIAR_CLAVE)) {
                        stm.clearParameters();
                        stm.setString(1, clave);
                        stm.setString(2, id);
                        System.out.println("modelo.gestor.GestorDatos.cambiarClave()"+stm.toString());
                    return (stm.executeUpdate() == 1);
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
    }
    public void insertarUsuarios(List<Usuario> usuarios) {
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement statement = cnx.prepareStatement(CMD_INSERTAR_USUARIOS)) {
            int i = 0;
            for (Usuario v : usuarios) {
                statement.setString(1, v.getCedula());
                statement.setString(2, v.getNombre());
                statement.setString(3, v.getApellido1());
                statement.setString(4, v.getApellido2());
                statement.setString(5, v.getClave());
                statement.setInt(6, 0);
                statement.addBatch();
                i++;
                if (i % 1000 == 0 || i == usuarios.size()) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
    }

    private static GestorDatos instancia = null;
    private DBManager db = null;
    private String URL_Servidor = "localhost";
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String CMD_ESTATUS_VOTANTE = "SELECT cedula\n"
            + "FROM usuario\n"
            + "WHERE cedula = ? AND activo = 1";
    private static final String CMD_VERIFICAR_USUARIO = "SELECT cedula\n"
            + "FROM usuario\n"
            + "WHERE cedula = ? AND clave= ?;";
    private static final String CMD_VERIFICAR_VOTO = "SELECT voto_completado\n"
            + "FROM votacion_usuario \n"
            + "WHERE votacion_id = ? AND usuario_cedula= ?;";
    private static final String CMD_VERIFICAR_ADMINISTRADOR = "SELECT cedula\n"
            + "FROM administrador\n"
            + "WHERE usuario = ? AND clave= ?;";
    private static final String CMD_VOTACIO_ESTADO = "SELECT id_votacion\n"
            + "FROM votacion\n"
            + "WHERE estado=?";
    private static final String CMD_CAMBIAR_CLAVE
            = "UPDATE usuario "
            + "SET clave = ?,activo=1 "
            + "WHERE cedula = ? ";
    
    private static final String CMD_INSERTAR_USUARIOS = "INSERT INTO USUARIO\n"
            + "(cedula,\n"
            + "nombre,\n"
            + "apellido1,\n"
            + "apellido2,\n"
            + "clave,\n"
            + "activo)\n"
            + "VALUES\n"
            + "(?,\n"
            + "?,\n"
            + "?,\n"
            + "?,\n"
            + "?,\n"
            + "?)";
}
