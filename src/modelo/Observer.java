package modelo;

public interface Observer {
	void enviarMensaje(String direccionReceptor, Venta venta);
}
