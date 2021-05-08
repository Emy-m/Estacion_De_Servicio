package modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class CombustibleSuper extends Combustible {
	
	private static double PRECIO_POR_LITRO_SUPER = 90;
	private static double PORCENTAJE_DESCUENTO_DOMINGO = 0.1;
	private static double PORCENTAJE_DESCUENTO_SABADO = 0.12;
	private static double MINIMO_LITROS_DESCUENTO_SABADO = 20;

	public CombustibleSuper() {
		super("Super", PRECIO_POR_LITRO_SUPER);
	}

	@Override
	public double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy) {
		if(esDomingo(fechaDeHoy)) {
			return PORCENTAJE_DESCUENTO_DOMINGO;
		}
		return 0;
	}

	@Override
	public double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy, double litrosCargados) {
		if(esSabado(fechaDeHoy)) {
			if(litrosNecesariosParaDescuento(litrosCargados)) {
				return PORCENTAJE_DESCUENTO_SABADO;
			}
			return 0;
		}
		else {
			return devolverDescuentoDeHoy(fechaDeHoy);
		}
	}

	public static void cambiarPrecioPorLitro(double precioPorLitro) {
		PRECIO_POR_LITRO_SUPER = precioPorLitro;
	}

	@Override
	public double calcularMonto(LocalDateTime fechaDeHoy, double litrosCargados) {
		return litrosCargados * PRECIO_POR_LITRO_SUPER - (litrosCargados * PRECIO_POR_LITRO_SUPER) * devolverDescuentoDeHoy(fechaDeHoy, litrosCargados);
	}
	
	public static void cambiarPorcentajeDescuentoDomingo(double porcentaje) {
		PORCENTAJE_DESCUENTO_DOMINGO = porcentaje;
	}
	
	public static void cambiarPorcentajeDescuentoSabado(double porcentaje) {
		PORCENTAJE_DESCUENTO_SABADO = porcentaje;
	}
	
	public static void cambiarLitrosParaDescuento(double litros) {
		MINIMO_LITROS_DESCUENTO_SABADO = litros;
	}
	
	private boolean esDomingo(LocalDateTime fechaDeHoy) {
		return fechaDeHoy.toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY;
	}
	
	private boolean esSabado(LocalDateTime fechaDeHoy) {
		return fechaDeHoy.toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY;
	}
	
	private boolean litrosNecesariosParaDescuento(double litrosCargados) {
		return litrosCargados > MINIMO_LITROS_DESCUENTO_SABADO;
	}
}
