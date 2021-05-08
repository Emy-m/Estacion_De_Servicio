package modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CombustibleComun extends Combustible {
	
	private static double PRECIO_POR_LITRO_COMUN = 70;
	private static double PORCENTAJE_DESCUENTO = 0.05;
	
	private static int HORA_MINIMA_DESCUENTO = 8;
	private static int HORA_MAXIMA_DESCUENTO = 10;

	public CombustibleComun() {
		super("Comun", PRECIO_POR_LITRO_COMUN);
	}

	@Override
	public double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy) {
		if(horaValidaParaDescuento(fechaDeHoy)) {
			return PORCENTAJE_DESCUENTO;
		}
		return 0;
	}

	@Override
	public double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy, double litrosCargados) {
		return devolverDescuentoDeHoy(fechaDeHoy);
	}

	@Override
	public double calcularMonto(LocalDateTime fechaDeHoy, double litrosCargados) {
		return litrosCargados * PRECIO_POR_LITRO_COMUN - (litrosCargados * PRECIO_POR_LITRO_COMUN) * devolverDescuentoDeHoy(fechaDeHoy);
	}
	
	public static void cambiarPrecioPorLitro(double precioPorLitro) {
		PRECIO_POR_LITRO_COMUN = precioPorLitro;
	}
	
	public static void cambiarPorcentajeDescuento(double porcentaje) {
		PORCENTAJE_DESCUENTO = porcentaje;
	}

	private boolean horaValidaParaDescuento(LocalDateTime fechaDeHoy) {
		LocalTime minimaHora = LocalTime.of(HORA_MINIMA_DESCUENTO, 0);
		LocalTime maximaHora = LocalTime.of(HORA_MAXIMA_DESCUENTO, 0);
		LocalTime horaDeHoy = fechaDeHoy.toLocalTime();
		
		return horaDeHoy.isAfter(minimaHora) && horaDeHoy.isBefore(maximaHora);
	}
}
