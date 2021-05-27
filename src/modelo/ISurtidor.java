package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ISurtidor {
	void agregarCombustible(Combustible combustible);

	ArrayList<Combustible> devolverCombustibles();

	ArrayList<String> devolverNombresCombustibles();

	double consultarMontoAPagar(String nombreCombustible, String litros, LocalDateTime tiempoDeHoy);

	void confirmarVenta(String nombreCombustible, String litros, LocalDateTime tiempoDeHoy, String email);

	boolean ventaGuardada(double monto, double litrosCargados, LocalDateTime tiempoDeHoy, String email);

	ArrayList<String> obtenerVentas();

	ArrayList<String> obtenerVentasEnRango(String fechaInicial, String fechaFin);
}
