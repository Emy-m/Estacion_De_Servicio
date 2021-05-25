package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
	private List<IEMailObserver> observadores;

	protected Observable() {
		observadores = new ArrayList<>();
	}

	public void agregarObservador(IEMailObserver obs) {
		this.observadores.add(obs);
	}

	protected void notificarVenta(String direccion, Venta venta) {
		for (IEMailObserver observer : observadores) {
			observer.enviarMensaje(direccion, venta);
		}
	}
}
