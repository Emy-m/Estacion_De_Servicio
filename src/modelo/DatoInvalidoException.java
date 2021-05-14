package modelo;

public class DatoInvalidoException extends RuntimeException {
	public DatoInvalidoException(String nombreDatoInvalido, Throwable exception) {
		super("Dato invalido: " + nombreDatoInvalido, exception);
	}

	public DatoInvalidoException(String nombreDatoInvalido) {
		super("Dato invalido: " + nombreDatoInvalido);
	}
}
