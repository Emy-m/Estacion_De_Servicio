package modelo;

public class Descuento {
	private double porcentajeDeDescuento;

	public Descuento(double porcentajeDeDescuento) {
		if (porcentajeDeDescuentoInvalido(porcentajeDeDescuento)) {
			throw new DatoInvalidoException("porcentaje de decuento");
		}

		this.porcentajeDeDescuento = porcentajeDeDescuento;
	}

	private boolean porcentajeDeDescuentoInvalido(double porcentajeDeDescuento) {
		return porcentajeDeDescuento <= 0;
	}

	public double devolverPorcentajeDeDescuento() {
		return this.porcentajeDeDescuento;
	}
}
