package persistencia;

public class PersistenciaException extends RuntimeException {
	public PersistenciaException(String detalle) {
		super(detalle);
	}

	public PersistenciaException(String detalle, Throwable exception) {
		super(detalle, exception);
	}
}
