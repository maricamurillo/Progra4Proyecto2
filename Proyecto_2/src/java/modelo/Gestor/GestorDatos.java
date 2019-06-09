package modelo.gestor;

import cr.ac.database.managers.DBManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Administrador;
import modelo.entidades.Partido;
import modelo.entidades.Votacion;
import modelo.entidades.Usuario;
import modelo.entidades.VotacionPartido;
import modelo.entidades.IOUtilities;
import org.json.JSONArray;
import org.json.JSONObject;

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
        try (PreparedStatement stm = cnx.prepareStatement(CMD_VOTACION_ESTADO)) {
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

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> datos = new ArrayList<>();
        Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);

        try (PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_USUARIOS)) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                datos.add(new Usuario(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), "", rs.getInt("activo")));
            }
        }

        return datos;
    }

    public JSONObject obtenerTablaUsuarios() throws SQLException {
        JSONObject r = new JSONObject();
        JSONArray a = new JSONArray();
        List<Usuario> datos = listarUsuarios();

        for (Usuario dato : datos) {
            JSONObject j = new JSONObject();
            j.put("cedula", dato.getCedula());
            j.put("apellidos", dato.getApellido1() + " " + dato.getApellido2());
            j.put("nombre", dato.getNombre());
            if (dato.getEstado() == 1) {
                j.put("estado", "Activo");
            } else {
                j.put("estado", "Inactivo");
            }
            a.put(j);
        }
        r.put("datos", a);
        return r;
    }

    public List<Votacion> listarVotaciones() throws SQLException {
        List<Votacion> datos = new ArrayList<>();
        Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);

        try (PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_VOTACIONES)) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                datos.add(new Votacion(0, rs.getDate("fecha_inicio"), rs.getDate("fecha_apertura"), rs.getDate("fecha_cierre"), rs.getDate("fecha_final"), rs.getInt("estado")));
            }
        }

        return datos;
    }

    public JSONObject obtenerTablaVotaciones() throws SQLException {
        JSONObject r = new JSONObject();
        JSONArray a = new JSONArray();
        List<Votacion> datos = listarVotaciones();

        for (Votacion dato : datos) {
            JSONObject j = new JSONObject();
            j.put("fecha_inicio", dato.getFechaInicio());
            j.put("fecha_apertura", dato.getFechaApertura());
            j.put("fecha_cierre", dato.getFechaCierre());
            j.put("fecha_final", dato.getFechaFinal());
            if (dato.getEstado() == 1) {
                j.put("estado", "Activa");
            } else {
                j.put("estado", "Inactiva");
            }
            a.put(j);
        }
        r.put("datos", a);
        return r;
    }

    public List<Administrador> listarAdministradores() throws SQLException {
        List<Administrador> datos = new ArrayList<>();
        Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);

        try (PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_ADMINISTRADORES)) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                datos.add(new Administrador(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("usuario"), ""));
            }
        }

        return datos;
    }

    public JSONObject obtenerTablaAdministradores() throws SQLException {
        JSONObject r = new JSONObject();
        JSONArray a = new JSONArray();
        List<Administrador> datos = listarAdministradores();

        for (Administrador dato : datos) {
            JSONObject j = new JSONObject();
            j.put("cedula", dato.getCedula());
            j.put("apellidos", dato.getApellido1() + " " + dato.getApellido2());
            j.put("nombre", dato.getNombre());
            j.put("usuario", dato.getUsuario());
            a.put(j);
        }
        r.put("datos", a);
        return r;
    }

    public List<Partido> listarPartidos() throws SQLException {
        List<Partido> datos = new ArrayList<>();
        Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);

        try (PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_PARTIDOS)) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                datos.add(new Partido(rs.getString("siglas"), rs.getString("nombre"), "", "", rs.getString("observaciones")));
            }
        }

        return datos;
    }

    public JSONObject obtenerTablaPartidos() throws SQLException {
        JSONObject r = new JSONObject();
        JSONArray a = new JSONArray();
        List<Partido> datos = listarPartidos();

        for (Partido dato : datos) {
            JSONObject j = new JSONObject();
            j.put("siglas", dato.getSiglas());
            j.put("nombre", dato.getNombre());
            j.put("bandera", dato.getBandera());
            j.put("observaciones", dato.getObservaciones());
            a.put(j);
        }
        r.put("datos", a);
        return r;
    }

    public List<VotacionPartido> listarCandidatos() throws SQLException {
        List<VotacionPartido> datos = new ArrayList<>();
        Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);

        try (PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_CANDIDATOS)) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                VotacionPartido votacionPartido = new VotacionPartido();
                Usuario candidato = new Usuario(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), "", rs.getInt("activo"));
                Partido partido = new Partido(rs.getString("siglas"), rs.getString("nombre_partido"), "", "", "");
                votacionPartido.setCandidato(candidato);
                votacionPartido.setPartido(partido);
                datos.add(votacionPartido);
            }
        }

        return datos;
    }

    public JSONObject obtenerTablaCandidatos() throws SQLException {
        JSONObject r = new JSONObject();
        JSONArray a = new JSONArray();
        List<VotacionPartido> datos = listarCandidatos();

        for (VotacionPartido dato : datos) {
            JSONObject j = new JSONObject();
            j.put("cedula", dato.getCandidato().getCedula());
            j.put("apellidos", dato.getCandidato().getApellido1() + " " + dato.getCandidato().getApellido2());
            j.put("nombre", dato.getCandidato().getNombre());
            if (dato.getCandidato().getEstado() == 1) {
                j.put("estado", "Activo");
            } else {
                j.put("estado", "Inactivo");
            }
            j.put("foto", dato.getFotoCandidato());
            j.put("partido", dato.getPartido().getSiglas() + " - " + dato.getPartido().getNombre());
            a.put(j);
        }
        r.put("datos", a);
        return r;
    }

    public void cargarFotoCandidato(HttpServletResponse response, String cedulaCandidato) throws IOException, SQLException {

        try (OutputStream out = response.getOutputStream();
                Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement stm = cnx.prepareCall(CMD_OBTENER_FOTO_CANDIDATO)) {
            stm.setString(1, cedulaCandidato);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                response.setContentType(rs.getString("tipo_imagen"));
                IOUtilities.copy(rs.getBinaryStream(1), out);
            }
        }
    }
    
    public void cargarBanderaPartido(HttpServletResponse response, String siglasPartido) throws IOException, SQLException {

        try (OutputStream out = response.getOutputStream();
                Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement stm = cnx.prepareCall(CMD_OBTENER_BANDERA_PARTIDO)) {
            stm.setString(1, siglasPartido);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                response.setContentType(rs.getString("tipo_imagen"));
                IOUtilities.copy(rs.getBinaryStream(1), out);
            }
        }
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
    private static final String CMD_VOTACION_ESTADO = "SELECT id_votacion\n"
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
    private static final String CMD_LISTAR_USUARIOS = "SELECT cedula, apellido1, apellido2, nombre, activo\n"
            + "FROM bd_votaciones.usuario\n"
            + "ORDER BY apellido1, apellido2 ASC";
    private static final String CMD_LISTAR_VOTACIONES = "SELECT fecha_inicio, fecha_apertura, fecha_cierre, fecha_final, estado\n"
            + "FROM bd_votaciones.votacion\n"
            + "ORDER BY fecha_inicio";
    private static final String CMD_LISTAR_ADMINISTRADORES = "SELECT cedula, apellido1, apellido2, nombre, usuario\n"
            + "FROM bd_votaciones.administrador\n"
            + "ORDER BY apellido1, apellido2";
    private static final String CMD_LISTAR_PARTIDOS = "SELECT siglas, nombre, bandera, tipo_imagen, observaciones\n"
            + "FROM bd_votaciones.partido\n"
            + "ORDER BY siglas";
    private static final String CMD_LISTAR_CANDIDATOS = "SELECT u.cedula, u.apellido1, u.apellido2, u.nombre, u.activo, \n"
            + "vp.foto_candidato, vp.tipo_imagen, p.siglas, p.nombre nombre_partido\n"
            + "FROM bd_votaciones.usuario u\n"
            + "INNER JOIN bd_votaciones.votacion_partido vp\n"
            + "ON vp.cedula_candidato = u.cedula\n"
            + "INNER JOIN bd_votaciones.partido p\n"
            + "ON p.siglas = vp.partido_siglas\n";
    private static final String CMD_OBTENER_FOTO_CANDIDATO = "SELECT foto_candidato, tipo_imagen\n"
            + "FROM bd_votaciones.votacion_partido\n"
            + "WHERE cedula_candidato = ?";
    private static final String CMD_OBTENER_BANDERA_PARTIDO = "SELECT bandera, tipo_imagen\n"
            + "FROM bd_votaciones.partido\n"
            + "WHERE siglas = ?";
}
