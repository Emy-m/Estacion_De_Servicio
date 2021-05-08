package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IAlmacenamiento {
	abstract void guardarVenta(Venta venta);
	abstract boolean ventaGuardada(Venta venta);
	abstract ArrayList<String> obtenerVentas();
	abstract ArrayList<String> obtenerVentasEnRango(LocalDate fechaValida, LocalDate fechaValida2);
}
