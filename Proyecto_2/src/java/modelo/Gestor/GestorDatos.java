package modelo.gestor;

import cr.ac.database.managers.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.entidades.Votante;

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

    public boolean verificarUsuario(String usuario, String clave, String tipo) {
        boolean encontrado = false;

        try {
            Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            String x = "";
            if (tipo == "usuario") {
                x = CMD_VERIFICAR_VOTANTES;
            }
            if (tipo == "administrador") {
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

    public void insertarVotantes(List<Votante> votantes) {
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                PreparedStatement statement = cnx.prepareStatement(CMD_INSERTAR_VOTANTES)) {
            int i = 0;

            for (Votante v : votantes) {
                statement.setString(1, v.getCedula());
                statement.setString(2, v.getNombre());
                statement.setString(3, v.getApellido1());
                statement.setString(4, v.getApellido2());
                statement.setString(5, v.getClave());
                statement.setInt(6, 0);
                statement.setInt(7, 1);
                statement.addBatch();
                i++;

                if (i % 1000 == 0 || i == votantes.size()) {
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
    private static final String BASE_DATOS = "proyecto_2";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin1234";
    private static final String CMD_VERIFICAR_VOTANTES = "SELECT cedula\n"
            + "FROM votantes\n"
            + "WHERE cedula = ? AND clave= ?;";
    private static final String CMD_VERIFICAR_ADMINISTRADOR = "SELECT cedula\n"
            + "FROM administradores\n"
            + "WHERE usuario = ? AND clave= ?;";
    private static final String CMD_INSERTAR_VOTANTES = "INSERT INTO votantes\n"
            + "(cedula,\n"
            + "nombre,\n"
            + "apellido1,\n"
            + "apellido2,\n"
            + "clave,\n"
            + "cambio_clave,\n"
            + "estado)\n"
            + "VALUES\n"
            + "(?,\n"
            + "?,\n"
            + "?,\n"
            + "?,\n"
            + "?,\n"
            + "?,\n"
            + "?)";
}
