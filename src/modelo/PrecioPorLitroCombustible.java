package modelo;

public class PrecioPorLitroCombustible {
	private double precioPorLitro;

	public PrecioPorLitroCombustible(double precio) {
		if (precioPorLitroInvalido(precio)) {
			throw new DatoInvalidoException("precio por litro menor o igual a cero");
		}

		this.precioPorLitro = precio;
	}

	private boolean precioPorLitroInvalido(double precioPorLitro) {
		return precioPorLitro <= 0;
	}

	public double devolverPrecioPorLitro() {
		return this.precioPorLitro;
	}
}
