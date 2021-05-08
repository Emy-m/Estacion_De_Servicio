package modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import persistencia.EnBaseDeDatosIAlmacenamiento;
import persistencia.EnDiscoIAlmacenamiento;
import persistencia.EnMemoriaIAlmacenamiento;

class TestVenta {

	@Test
	void testVentaEnMemoria() {
		CombustibleComun combustibleComun = new CombustibleComun();
		CombustibleSuper combustibleSuper = new CombustibleSuper();
		EnMemoriaIAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleComun);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.now();
		
		surtidor.confirmarVenta(combustibleComun.obtenerNombreCombustible(), "10", tiempoDeHoy);
		assertTrue(surtidor.ventaGuardada(700, 10, tiempoDeHoy));
	}
	
	@Test
	void testVentaSuperSabadoConDescuento() {
		CombustibleSuper combustibleSuper = new CombustibleSuper();
		EnMemoriaIAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 8, 4, 12); //Sabado 8 de mayo.
		
		assertEquals(3960, combustibleSuper.calcularMonto(tiempoDeHoy, 50)); // 50 * 90 = 4500 - 12% -> 3960
	}
	
	@Test
	void testVentaSuperSabadoSinDescuento() {
		CombustibleSuper combustibleSuper = new CombustibleSuper();
		EnMemoriaIAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 8, 4, 12); //Sabado 8 de mayo.
		
		assertEquals(1800, combustibleSuper.calcularMonto(tiempoDeHoy, 20)); // 20 * 90 = 1800 sin descuento
	}
	
	@Test
	void testVentaSuperDomingo() {
		CombustibleSuper combustibleSuper = new CombustibleSuper();
		EnMemoriaIAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 9, 4, 12); //Domingo 9 de mayo.
		
		assertEquals(1620, combustibleSuper.calcularMonto(tiempoDeHoy, 20)); // 20 * 90 = 1800 - 10% -> 1620
	}
	
	@Test
	void testVentaComunConDescuento() {
		CombustibleComun combustibleComun = new CombustibleComun();
		EnMemoriaIAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleComun);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 9, 9, 12); //Dia cualquiera a las 9am, dentro de 8 a 10am
		
		assertEquals(1330, combustibleComun.calcularMonto(tiempoDeHoy, 20)); // 20 * 70 = 1400 - 5% -> 1330
	}
	
	@Test
	void testVentaComunSinDescuento() {
		CombustibleComun combustibleComun = new CombustibleComun();
		EnMemoriaIAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleComun);
		LocalDateTime tiempoDeHoy = LocalDateTime.of(2021, 5, 9, 11, 12); //Dia cualquiera a las 11am, fuera de 8 a 10am
		
		assertEquals(1400, combustibleComun.calcularMonto(tiempoDeHoy, 20)); // 20 * 70 = 1400 -> 1400
	}
	
	@Test
	void testVentaEnDisco() {
		CombustibleComun combustibleComun = new CombustibleComun();
		CombustibleSuper combustibleSuper = new CombustibleSuper();
		EnDiscoIAlmacenamiento almacenamiento = new EnDiscoIAlmacenamiento("C:\\Users\\Emy_m\\Desktop\\Venta.txt");
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleComun);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.now();
		
		surtidor.confirmarVenta(combustibleComun.obtenerNombreCombustible(), "10", tiempoDeHoy);
		assertTrue(surtidor.ventaGuardada(700, 10, tiempoDeHoy));
	}
	
	@Test
	void testVentaEnBD() {
		CombustibleComun combustibleComun = new CombustibleComun();
		CombustibleSuper combustibleSuper = new CombustibleSuper();
		EnBaseDeDatosIAlmacenamiento almacenamiento = new EnBaseDeDatosIAlmacenamiento();
		SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
		surtidor.agregarCombustible(combustibleComun);
		surtidor.agregarCombustible(combustibleSuper);
		LocalDateTime tiempoDeHoy = LocalDateTime.now();
		
		surtidor.confirmarVenta(combustibleComun.obtenerNombreCombustible(), "10", tiempoDeHoy);
		assertTrue(surtidor.ventaGuardada(700, 10, tiempoDeHoy));
	}

}
