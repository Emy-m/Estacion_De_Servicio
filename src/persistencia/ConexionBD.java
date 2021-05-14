package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/est_servicio_ypzw";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";

	public static Connection conectar() throws PersistenciaException {
		Connection conexion = null;
		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);

			return conexion;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException("Hubo un error con la base de datos");
		}
	}

	public static Connection conectar(Boolean commit) throws PersistenciaException {
		Connection conexion = null;
		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			conexion.setAutoCommit(commit);

			return conexion;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException("Hubo un error con la base de datos");
		}
	}
}
