package modelo;

public class LitrosDeCombustible {
	public double litros;

	public LitrosDeCombustible(double litros) {
		if (cantidadDeLitrosInvalido(litros)) {
			throw new DatoInvalidoException("cantidad de litros menor a cero");
		}

		this.litros = litros;
	}

	public LitrosDeCombustible(String litros) {
		try {
			if (litros.isBlank()) {
				throw new DatoInvalidoException("cantidad de litros en blanco");
			}
			if (cantidadDeLitrosInvalido(Double.parseDouble(litros))) {
				throw new DatoInvalidoException("cantidad de litros menor a cero");
			}

			this.litros = Double.parseDouble(litros);
		} catch (NullPointerException e) {
			throw new DatoInvalidoException("cantidad de litros nula", e);
		} catch (NumberFormatException e) {
			throw new DatoInvalidoException("cantidad de litros no es un numero", e);
		}
	}

	private boolean cantidadDeLitrosInvalido(double litros) {
		return litros < 0;
	}

	public double devolverCantidadDeLitros() {
		return this.litros;
	}
}
