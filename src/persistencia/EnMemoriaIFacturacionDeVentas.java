package persistencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.IFacturacionDeVentas;
import modelo.Venta;

public class EnMemoriaIFacturacionDeVentas implements IFacturacionDeVentas {

	private ArrayList<Venta> ventas = new ArrayList<Venta>();

	@Override
	public void guardarVenta(Venta venta) {
		ventas.add(venta);
	}

	@Override
	public boolean ventaGuardada(Venta venta) {
		return ventas.contains(venta);
	}

	@Override
	public ArrayList<String> obtenerVentas() {
		ArrayList<String> stringsVentas = new ArrayList<String>();

		for (Venta venta : ventas) {
			stringsVentas.add(venta.toString());
		}

		return stringsVentas;
	}

	@Override
	public ArrayList<String> obtenerVentasEnRango(LocalDate fechaInicial, LocalDate fechaFin) {
		ArrayList<String> stringsVentas = new ArrayList<String>();

		for (Venta venta : ventas) {
			if (fechaEnRango(venta.devolverFechaYHora().toLocalDate(), fechaInicial, fechaFin)) {
				stringsVentas.add(venta.toString());
			}
		}

		return stringsVentas;
	}

	private boolean fechaEnRango(LocalDate fecha, LocalDate fechaInicial, LocalDate fechaFin) {
		return (fechaInicial.isBefore(fecha) || fechaInicial.isEqual(fecha))
				&& (fechaFin.isEqual(fecha) || fechaFin.isAfter(fecha));
	}

}
