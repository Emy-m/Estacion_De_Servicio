package modelo;

public class DireccionEmail {

	private String direccion;

	public DireccionEmail(String direccion) {
		if (stringVacio(direccion) || stringNulo(direccion)) {
			throw new DatoInvalidoException("email vacio");
		}
		if (!validarDireccionEmail(direccion)) {
			throw new DatoInvalidoException("email invalido");
		}
		this.direccion = direccion;
	}

	private boolean stringVacio(String string) {
		return string.isBlank();
	}

	private boolean stringNulo(String string) {
		return string == null;
	}

	private boolean validarDireccionEmail(String direccion) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return direccion.matches(regex);
	}

	public String obtenerEmail() {
		return this.direccion;
	}
}
