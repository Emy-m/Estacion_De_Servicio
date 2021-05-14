package modelo;

import java.time.LocalDateTime;

public abstract class Combustible {
	protected String nombreCombustible;
	protected PrecioPorLitroCombustible precioPorLitro;

	protected Combustible(String nombreCombustible, double precioPorLitro) {
		if (stringVacio(nombreCombustible) || stringNulo(nombreCombustible)) {
			throw new DatoInvalidoException("nombre del combustible");
		}

		this.nombreCombustible = nombreCombustible;
		this.precioPorLitro = new PrecioPorLitroCombustible(precioPorLitro);
	}

	public String obtenerNombreCombustible() {
		return nombreCombustible;
	}

	private boolean stringVacio(String string) {
		return string.isBlank();
	}

	private boolean stringNulo(String string) {
		return string == null;
	}

	public abstract double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy);

	public abstract double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy, double litrosCargados);

	public abstract double calcularMonto(LocalDateTime fechaDeHoy, double litrosCargados);

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combustible other = (Combustible) obj;
		if (nombreCombustible == null) {
			if (other.nombreCombustible != null)
				return false;
		} else if (!nombreCombustible.equals(other.nombreCombustible))
			return false;
		return true;
	}

}
