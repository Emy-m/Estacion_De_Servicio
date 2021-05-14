package modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import persistencia.EnBaseDeDatosIFacturacionDeVentas;
import persistencia.EnDiscoIFacturacionDeVentas;

public class TestIntegracion {
	@Test
	void testVentaEnDisco() {
		HorarioDeDescuento descuentoParaCombustibleComun = new HorarioDeDescuento(8, 10, 0.05);
		DiaDeDescuento descuentoSabadoSuper = new DiaDeDescuento(DayOfWeek.SATURDAY, 0.12, 20);
		DiaDeDescuento descuentoDomingoSuper = new DiaDeDescuento(DayOfWeek.SUNDAY, 0.1, 0);
		ArrayList<DiaDeDescuento> descuentoParaCombustibleSuper = new ArrayList<DiaDeDescuento>();

		CombustibleComun combustibleComun = new CombustibleComun(70, descuentoParaCombustibleComun);
		CombustibleSuper combustibleSuper = new CombustibleSuper(90, descuentoParaCombustibleSuper);
		EnDiscoIFacturacionDeVentas almacenamiento = new EnDiscoIFacturacionDeVentas(
				"C:\\Users\\Emy_m\\Desktop\\Venta.txt");
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleComun);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.now();

		surtidor.confirmarVenta(combustibleComun.obtenerNombreCombustible(), "10", tiempoDeHoy);
		assertTrue(surtidor.ventaGuardada(700, 10, tiempoDeHoy));
	}

	@Test
	void testVentaEnBD() {
		HorarioDeDescuento descuentoParaCombustibleComun = new HorarioDeDescuento(8, 10, 0.05);
		DiaDeDescuento descuentoSabadoSuper = new DiaDeDescuento(DayOfWeek.SATURDAY, 0.12, 20);
		DiaDeDescuento descuentoDomingoSuper = new DiaDeDescuento(DayOfWeek.SUNDAY, 0.1, 0);
		ArrayList<DiaDeDescuento> descuentoParaCombustibleSuper = new ArrayList<DiaDeDescuento>();

		CombustibleComun combustibleComun = new CombustibleComun(70, descuentoParaCombustibleComun);
		CombustibleSuper combustibleSuper = new CombustibleSuper(90, descuentoParaCombustibleSuper);
		EnBaseDeDatosIFacturacionDeVentas almacenamiento = new EnBaseDeDatosIFacturacionDeVentas();
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleComun);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.now();

		surtidor.confirmarVenta(combustibleComun.obtenerNombreCombustible(), "10", tiempoDeHoy);
		assertTrue(surtidor.ventaGuardada(700, 10, tiempoDeHoy));
	}
}