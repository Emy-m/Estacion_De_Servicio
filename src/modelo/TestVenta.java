package modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import email.EMail;
import persistencia.EnMemoriaIFacturacionDeVentas;

class TestVenta {

	@Test
	void testVentaEnMemoria() {
		HorarioDeDescuento descuentoParaCombustibleComun = new HorarioDeDescuento(8, 10, 0.05);
		DiaDeDescuento descuentoSabadoSuper = new DiaDeDescuento(DayOfWeek.SATURDAY, 0.12, 20);
		DiaDeDescuento descuentoDomingoSuper = new DiaDeDescuento(DayOfWeek.SUNDAY, 0.1, 0);
		ArrayList<DiaDeDescuento> descuentoParaCombustibleSuper = new ArrayList<DiaDeDescuento>();
		descuentoParaCombustibleSuper.add(descuentoSabadoSuper);
		descuentoParaCombustibleSuper.add(descuentoDomingoSuper);

		CombustibleComun combustibleComun = new CombustibleComun(70, descuentoParaCombustibleComun);
		CombustibleSuper combustibleSuper = new CombustibleSuper(90, descuentoParaCombustibleSuper);
		EnMemoriaIFacturacionDeVentas almacenamiento = new EnMemoriaIFacturacionDeVentas();

		EMail servicioEmail = new EMail();

		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento, servicioEmail);
		surtidor.agregarCombustible(combustibleComun);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.now();

		surtidor.confirmarVenta(combustibleComun.obtenerNombreCombustible(), "10", tiempoDeHoy, "emilio@gmail.com");
		assertTrue(surtidor.ventaGuardada(700, 10, tiempoDeHoy));
	}

	@Test
	void testVentaSuperSabadoConDescuento() {
		DiaDeDescuento descuentoSabadoSuper = new DiaDeDescuento(DayOfWeek.SATURDAY, 0.12, 20);
		DiaDeDescuento descuentoDomingoSuper = new DiaDeDescuento(DayOfWeek.SUNDAY, 0.1, 0);
		ArrayList<DiaDeDescuento> descuentoParaCombustibleSuper = new ArrayList<DiaDeDescuento>();
		descuentoParaCombustibleSuper.add(descuentoSabadoSuper);
		descuentoParaCombustibleSuper.add(descuentoDomingoSuper);

		CombustibleSuper combustibleSuper = new CombustibleSuper(90, descuentoParaCombustibleSuper);
		EnMemoriaIFacturacionDeVentas almacenamiento = new EnMemoriaIFacturacionDeVentas();

		EMail servicioEmail = new EMail();

		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento, servicioEmail);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 8, 4, 12); // Sabado 8 de mayo.

		assertEquals(3960, combustibleSuper.calcularMonto(tiempoDeHoy, 50)); // 50 * 90 = 4500 - 12% -> 3960
	}

	@Test
	void testVentaSuperSabadoSinDescuento() {
		DiaDeDescuento descuentoSabadoSuper = new DiaDeDescuento(DayOfWeek.SATURDAY, 0.12, 20);
		DiaDeDescuento descuentoDomingoSuper = new DiaDeDescuento(DayOfWeek.SUNDAY, 0.1, 0);
		ArrayList<DiaDeDescuento> descuentoParaCombustibleSuper = new ArrayList<DiaDeDescuento>();
		descuentoParaCombustibleSuper.add(descuentoSabadoSuper);
		descuentoParaCombustibleSuper.add(descuentoDomingoSuper);

		CombustibleSuper combustibleSuper = new CombustibleSuper(90, descuentoParaCombustibleSuper);
		EnMemoriaIFacturacionDeVentas almacenamiento = new EnMemoriaIFacturacionDeVentas();

		EMail servicioEmail = new EMail();

		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento, servicioEmail);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 8, 4, 12); // Sabado 8 de mayo.

		assertEquals(1800, combustibleSuper.calcularMonto(tiempoDeHoy, 20)); // 20 * 90 = 1800 sin descuento
	}

	@Test
	void testVentaSuperDomingo() {
		DiaDeDescuento descuentoSabadoSuper = new DiaDeDescuento(DayOfWeek.SATURDAY, 0.12, 20);
		DiaDeDescuento descuentoDomingoSuper = new DiaDeDescuento(DayOfWeek.SUNDAY, 0.1, 0);
		ArrayList<DiaDeDescuento> descuentoParaCombustibleSuper = new ArrayList<DiaDeDescuento>();
		descuentoParaCombustibleSuper.add(descuentoSabadoSuper);
		descuentoParaCombustibleSuper.add(descuentoDomingoSuper);

		CombustibleSuper combustibleSuper = new CombustibleSuper(90, descuentoParaCombustibleSuper);
		EnMemoriaIFacturacionDeVentas almacenamiento = new EnMemoriaIFacturacionDeVentas();

		EMail servicioEmail = new EMail();

		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento, servicioEmail);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 9, 4, 12); // Domingo 9 de mayo.

		assertEquals(1620, combustibleSuper.calcularMonto(tiempoDeHoy, 20)); // 20 * 90 = 1800 - 10% -> 1620
	}

	@Test
	void testVentaSuperDiaDeSemana() {
		DiaDeDescuento descuentoSabadoSuper = new DiaDeDescuento(DayOfWeek.SATURDAY, 0.12, 20);
		DiaDeDescuento descuentoDomingoSuper = new DiaDeDescuento(DayOfWeek.SUNDAY, 0.1, 0);
		ArrayList<DiaDeDescuento> descuentoParaCombustibleSuper = new ArrayList<DiaDeDescuento>();
		descuentoParaCombustibleSuper.add(descuentoSabadoSuper);
		descuentoParaCombustibleSuper.add(descuentoDomingoSuper);

		CombustibleSuper combustibleSuper = new CombustibleSuper(90, descuentoParaCombustibleSuper);
		EnMemoriaIFacturacionDeVentas almacenamiento = new EnMemoriaIFacturacionDeVentas();

		EMail servicioEmail = new EMail();

		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento, servicioEmail);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 10, 4, 12); // Lunes 10 de mayo.

		assertEquals(1800, combustibleSuper.calcularMonto(tiempoDeHoy, 20)); // 20 * 90 = 1800
	}

	@Test
	void testVentaComunConDescuento() {
		HorarioDeDescuento descuentoParaCombustibleComun = new HorarioDeDescuento(8, 10, 0.05);
		CombustibleComun combustibleComun = new CombustibleComun(70, descuentoParaCombustibleComun);
		EnMemoriaIFacturacionDeVentas almacenamiento = new EnMemoriaIFacturacionDeVentas();

		EMail servicioEmail = new EMail();

		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento, servicioEmail);
		surtidor.agregarCombustible(combustibleComun);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 9, 9, 12); // Dia cualquiera a las 9am, dentro de 8 a 10am

		assertEquals(1330, combustibleComun.calcularMonto(tiempoDeHoy, 20), 0.01); // 20 * 70 = 1400 - 5% -> 1330
	}

	@Test
	void testVentaComunSinDescuento() {
		HorarioDeDescuento descuentoParaCombustibleComun = new HorarioDeDescuento(8, 10, 0.05);
		CombustibleComun combustibleComun = new CombustibleComun(70, descuentoParaCombustibleComun);
		EnMemoriaIFacturacionDeVentas almacenamiento = new EnMemoriaIFacturacionDeVentas();

		EMail servicioEmail = new EMail();

		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento, servicioEmail);
		surtidor.agregarCombustible(combustibleComun);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 9, 11, 12); // Un dia a las 11

		assertEquals(1400, combustibleComun.calcularMonto(tiempoDeHoy, 20)); // 20 * 70 = 1400 -> 1400
	}

}
