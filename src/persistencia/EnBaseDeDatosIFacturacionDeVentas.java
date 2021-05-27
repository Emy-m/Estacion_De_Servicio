package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.IFacturacionDeVentas;
import modelo.Venta;

public class EnBaseDeDatosIFacturacionDeVentas implements IFacturacionDeVentas {

	private final String ESTRUCTURA_TABLA = "('ventas_id', 'fecha_hora', 'litros', 'monto', 'email_comprador')";
	private static final String SQL_INSERT = "insert into ventas values (null, ?, ?, ?, ?)";
	private static final String SQL_FIND = "select * from ventas where fecha_hora = ? and email_comprador = ?";
	private static final String SQL_FIND_ALL = "select * from ventas";

	@Override
	public void guardarVenta(Venta venta) {
		Connection conexion = null;
		PreparedStatement query = null;

		try {
			conexion = ConexionBD.conectar();
			query = conexion.prepareStatement(SQL_INSERT);

			query.setTimestamp(1, Timestamp.valueOf(venta.devolverFechaYHora()));
			query.setDouble(2, venta.devolverLitrosCargados());
			query.setDouble(3, venta.devolverMonto());
			query.setString(4, venta.devolverEmailComprador());

			query.executeUpdate();
		} catch (SQLException exception) {
			throw new PersistenciaException("Hubo un error con la base de datos", exception);
		} finally {
			try {
				query.close();
				conexion.close();
			} catch (Exception e) {
			} // Por si es nula (no se llego a concretar) o si ocurre un error con la base de
				// datos
		}
	}

	@Override
	public boolean ventaGuardada(Venta venta) {
		Connection conexion = null;
		PreparedStatement query = null;
		ResultSet resultado = null;
		Venta laVenta;

		try {
			conexion = ConexionBD.conectar();
			query = conexion.prepareStatement(SQL_FIND);

			query.setTimestamp(1, Timestamp.valueOf(venta.devolverFechaYHora()));
			query.setDouble(2, venta.devolverLitrosCargados());
			query.setDouble(3, venta.devolverMonto());

			resultado = query.executeQuery();

			if (resultado.next()) {
				laVenta = new Venta(resultado.getDouble("litros"), resultado.getDouble("monto"),
						resultado.getTimestamp("fecha_hora").toLocalDateTime(), resultado.getString("email_comprador"));
				return laVenta.equals(venta);
			}

			return false;
		} catch (SQLException exception1) {
			throw new PersistenciaException("Hubo un error con la base de datos", exception1);
		} finally {
			try {
				resultado.close();
				query.close();
				conexion.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public ArrayList<String> obtenerVentas() {
		Connection conexion = null;
		PreparedStatement query = null;
		ResultSet resultado = null;

		try {
			ArrayList<String> ventas = new ArrayList<String>();
			conexion = ConexionBD.conectar();
			query = conexion.prepareStatement(SQL_FIND_ALL);
			resultado = query.executeQuery();

			Venta venta;

			while (resultado.next()) {
				venta = new Venta(resultado.getDouble("litros"), resultado.getDouble("monto"),
						resultado.getTimestamp("fecha_hora").toLocalDateTime(), resultado.getString("email_comprador"));
				ventas.add(venta.toString());
			}

			return ventas;
		} catch (SQLException exception1) {
			throw new PersistenciaException("Hubo un error con la base de datos", exception1);
		} finally {
			try {
				resultado.close();
				query.close();
				conexion.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public ArrayList<String> obtenerVentasEnRango(LocalDate fechaInicial, LocalDate fechaFin) {
		Connection conexion = null;
		PreparedStatement query = null;
		ResultSet resultado = null;

		try {
			ArrayList<String> ventas = new ArrayList<String>();
			conexion = ConexionBD.conectar();
			query = conexion.prepareStatement(SQL_FIND_ALL);
			resultado = query.executeQuery();

			Venta venta;

			while (resultado.next()) {
				venta = new Venta(resultado.getDouble("litros"), resultado.getDouble("monto"),
						resultado.getTimestamp("fecha_hora").toLocalDateTime(), resultado.getString("email_comprador"));

				if (fechaEnRango(venta.devolverFechaYHora().toLocalDate(), fechaInicial, fechaFin)) {
					ventas.add(venta.toString());
				}
			}

			return ventas;
		} catch (SQLException exception1) {
			throw new PersistenciaException("Hubo un error con la base de datos", exception1);
		} finally {
			try {
				resultado.close();
				query.close();
				conexion.close();
			} catch (Exception e) {
			}
		}
	}

	private boolean fechaEnRango(LocalDate fecha, LocalDate fechaInicial, LocalDate fechaFin) {
		return (fechaInicial.isBefore(fecha) || fechaInicial.isEqual(fecha))
				&& (fechaFin.isEqual(fecha) || fechaFin.isAfter(fecha));
	}
}
