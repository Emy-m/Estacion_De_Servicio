package modelo;

import java.time.LocalDateTime;

public class CombustibleComun extends Combustible {
	private HorarioDeDescuento horarioDeDescuento;

	public CombustibleComun(double precioPorLitroComun, HorarioDeDescuento horarioDeDescuento) {
		super("Comun", precioPorLitroComun);
		this.horarioDeDescuento = horarioDeDescuento;
	}

	@Override
	public double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy) {
		if (horarioDeDescuento.hayDescuentoEsaHora(fechaDeHoy)) {
			return horarioDeDescuento.devolverDescuento();
		}
		return 0;
	}

	@Override
	public double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy, double litrosCargados) {
		return devolverDescuentoDeHoy(fechaDeHoy);
	}

	@Override
	public double calcularMonto(LocalDateTime fechaDeHoy, double litrosCargados) {
		return litrosCargados * precioPorLitro.devolverPrecioPorLitro() * (1 - devolverDescuentoDeHoy(fechaDeHoy));
	}
}
