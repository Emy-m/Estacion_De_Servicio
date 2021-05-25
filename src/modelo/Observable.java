package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
	private List<Observer> observadores;

	protected Observable() {
		observadores = new ArrayList<>();
	}

	public void agregarObservador(Observer obs) {
		this.observadores.add(obs);
	}

	protected void notificarVenta(String direccion, Venta venta) {
		for (Observer observer : observadores) {
			observer.enviarMensaje(direccion, venta);
		}
	}
}
