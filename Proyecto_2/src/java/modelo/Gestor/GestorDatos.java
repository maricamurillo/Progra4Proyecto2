package modelo.gestor;

import cr.ac.database.managers.DBManager;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelo.entidades.Administrador;
import modelo.entidades.Partido;
import modelo.entidades.Votacion;
import modelo.entidades.Usuario;
import modelo.entidades.VotacionPartido;

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

    public boolean verificaEstatusUsuario(String usuario) {
        boolean activo = false;
        try {
            Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            try (PreparedStatement stm = cnx.prepareStatement(CMD_ESTATUS_VOTANTE)) {
                stm.clearParameters();
                stm.setString(1, usuario);
                ResultSet rs = stm.executeQuery();
                activo = rs.next();

            }

        } catch (SQLException e) {
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

    public List<Votacion> listarVotaciones(String estados) throws SQLException {
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
                Votacion x = new Votacion(id, fechaInicio, fechaApertura, fechaCierre, fechaFinal, estado);
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
                System.out.println("modelo.gestor.GestorDatos.cambiarClave()" + stm.toString());
                return (stm.executeUpdate() == 1);
            }
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return false;
        }
    }

    public boolean insertarUsuarios(List<Usuario> usuarios) {
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement statement = cnx.prepareStatement(CMD_INSERTAR_USUARIO)) {
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
        return false;
    }

    public boolean insertarPartido(Partido partido, InputStream in, int size) {
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement statement = cnx.prepareStatement(CMD_INSERTAR_PARTIDO)) {

            statement.setString(1, partido.getSiglas());
            statement.setString(2, partido.getNombre());
            statement.setBinaryStream(3, in, size);
            statement.setString(4, partido.getTipoImagen());
            statement.setString(5, partido.getObservaciones());

            return (statement.executeUpdate() == 1);

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
        return false;
    }

    public static boolean validarFormatoImagen(final String fileName) {
        Matcher matcher = PATTERN.matcher(fileName);
        return matcher.matches();
    }

    public boolean insertarVotacion(Votacion votacion) {
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement statement = cnx.prepareStatement(CMD_INSERTAR_VOTACION)) {

            statement.setDate(1, new java.sql.Date(votacion.getFechaInicio().getTime()));
            statement.setDate(2, new java.sql.Date(votacion.getFechaApertura().getTime()));
            statement.setDate(3, new java.sql.Date(votacion.getFechaCierre().getTime()));
            statement.setDate(4, new java.sql.Date(votacion.getFechaFinal().getTime()));
            statement.setInt(5, votacion.getEstado());

            return (statement.executeUpdate() == 1);

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
        return false;
    }

    public boolean insertarUsuario(Usuario usuario) {
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement statement = cnx.prepareStatement(CMD_INSERTAR_USUARIO)) {
            
            statement.setString(1, usuario.getCedula());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getApellido1());
            statement.setString(4, usuario.getApellido2());
            statement.setString(5, usuario.getClave());
            statement.setInt(6, 0);
            return (statement.executeUpdate() == 1);

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
        return false;
    }

    public boolean insertarVotacionPartido(VotacionPartido votacionPartido, InputStream in, int size) {
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement statement = cnx.prepareStatement(CMD_INSERTAR_VOTACION_PARTIDO)) {

            statement.setInt(1, votacionPartido.getVotacion().getId());
            statement.setString(2, votacionPartido.getPartido().getSiglas());
            statement.setString(3, votacionPartido.getCandidato().getCedula());
            statement.setBinaryStream(4, in, size);
            statement.setString(5, votacionPartido.getTipoImagen());
            return (statement.executeUpdate() == 1);

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
        return false;
    }
    
    public boolean insertarAdministrador(Administrador administrador) {
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement statement = cnx.prepareStatement(CMD_INSERTAR_ADMINISTRADOR)) {
            
            statement.setString(1, administrador.getCedula());
            statement.setString(2, administrador.getNombre());
            statement.setString(3, administrador.getApellido1());
            statement.setString(4, administrador.getApellido2());
            statement.setString(5, administrador.getUsuario());
            statement.setString(6, administrador.getCedula());
            return (statement.executeUpdate() == 1);

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
        return false;
    }

    private static GestorDatos instancia = null;
    private DBManager db = null;
    private String URL_Servidor = "localhost";
    private static final String BASE_DATOS = "bd_votaciones";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin1234";
    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    private static final Pattern PATTERN = Pattern.compile(IMAGE_PATTERN);
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
    private static final String CMD_INSERTAR_USUARIO = "INSERT INTO USUARIO\n"
            + "(cedula, nombre, apellido1, apellido2, clave, activo)\n"
            + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CMD_INSERTAR_PARTIDO = "INSERT INTO PARTIDO\n"
            + "(siglas, nombre, bandera, tipo_imagen, observaciones)\n"
            + "VALUES(?,?,?,?,?)";
    private static final String CMD_INSERTAR_VOTACION = "INSERT INTO VOTACION\n"
            + "(fecha_inicio, fecha_apertura, fecha_cierre, fecha_final, estado)\n"
            + "VALUES(?,?,?,?,?)";
    private static final String CMD_INSERTAR_VOTACION_PARTIDO = "INSERT INTO VOTACION_PARTIDO\n"
            + "(votacion_id, partido_siglas, cedula_candidato, foto_candidato, tipo_imagen)\n"
            + "VALUES(?,?,?,?,?)";
    private static final String CMD_INSERTAR_ADMINISTRADOR = "INSERT INTO ADMINISTRADOR\n"
            + "(cedula, nombre, apellido1, apellido2, usuario, clave)\n"
            + "VALUES (?,?,?,?,?,?)";
}
