package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class SurtidorDeCombustible extends Observable implements ISurtidor {
	private ArrayList<Combustible> combustibles = new ArrayList<Combustible>();
	private IFacturacionDeVentas almacenamiento;

	public SurtidorDeCombustible(IFacturacionDeVentas almacenamiento, IEMailObserver servicioEmail) {
		this.almacenamiento = almacenamiento;
		agregarObservador(servicioEmail);
	}

	public void agregarCombustible(Combustible combustible) {
		if (!combustibles.contains(combustible)) {
			combustibles.add(combustible);
		}
	}

	public ArrayList<Combustible> devolverCombustibles() {
		return combustibles;
	}

	public ArrayList<String> devolverNombresCombustibles() {
		ArrayList<String> nombres = new ArrayList<String>();
		for (Combustible combustible : combustibles) {
			nombres.add(combustible.obtenerNombreCombustible());
		}
		return nombres;
	}

	public double consultarMontoAPagar(String nombreCombustible, String litros, LocalDateTime tiempoDeHoy) {
		double monto = 0;
		for (Combustible combustible : combustibles) {
			if (combustible.obtenerNombreCombustible().equals(nombreCombustible)) {
				monto = combustible.calcularMonto(tiempoDeHoy, Double.parseDouble(litros));
			}
		}
		return monto;
	}

	public void confirmarVenta(String nombreCombustible, String litros, LocalDateTime tiempoDeHoy, String email) {
		Venta venta = new Venta(Double.parseDouble(litros),
				consultarMontoAPagar(nombreCombustible, litros, tiempoDeHoy), tiempoDeHoy);
		DireccionEmail direccionEmail = new DireccionEmail(email);
		almacenamiento.guardarVenta(venta);
		notificarVenta(direccionEmail.obtenerEmail(), venta);
	}

	public boolean ventaGuardada(double monto, double litrosCargados, LocalDateTime tiempoDeHoy) {
		return almacenamiento.ventaGuardada(new Venta(litrosCargados, monto, tiempoDeHoy));
	}

	public ArrayList<String> obtenerVentas() {
		return almacenamiento.obtenerVentas();
	}

	public ArrayList<String> obtenerVentasEnRango(String fechaInicial, String fechaFin) {
		LocalDate fechaDeInicio = formatoValido(fechaInicial);
		LocalDate fechaDeFin = formatoValido(fechaFin);

		if (fechaDeInicio.isBefore(fechaDeFin) || fechaDeInicio.isEqual(fechaDeFin)) {
			return almacenamiento.obtenerVentasEnRango(fechaDeInicio, fechaDeFin);
		} else {
			throw new DatoInvalidoException("fechas");
		}
	}

	private LocalDate formatoValido(String fecha) {
		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
			return LocalDate.parse(fecha, formato);
		} catch (IllegalArgumentException | DateTimeParseException e) {
			throw new DatoInvalidoException("fechas");
		}
	}
}
