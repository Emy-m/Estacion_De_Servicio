package modelo;

public interface IEMailObserver {
	void enviarMensaje(String direccionReceptor, Venta venta);
}
