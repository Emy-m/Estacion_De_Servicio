package modelo;

public class DatoInvalidoException extends RuntimeException {
	public DatoInvalidoException(String nombreDatoInvalido) {
		super("Dato invalido: " + nombreDatoInvalido);
	}
}
