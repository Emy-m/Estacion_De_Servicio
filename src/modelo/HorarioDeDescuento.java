package modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class HorarioDeDescuento {
	private LocalTime horaDeInicio;
	private LocalTime horaDeFin;
	private Descuento descuento;

	public HorarioDeDescuento(int horaInicio, int horaFin, double porcentajeDescuento) {
		if (horarioInvalido(horaInicio)) {
			throw new DatoInvalidoException("hora de inicio fuera del rango 0-23");
		}
		if (horarioInvalido(horaFin)) {
			throw new DatoInvalidoException("hora de fin fuera del rango 0-23");
		}
		if (rangoInvalido(horaInicio, horaFin)) {
			throw new DatoInvalidoException("rango horario");
		}

		this.horaDeInicio = LocalTime.of(horaInicio, 0);
		this.horaDeFin = LocalTime.of(horaFin, 0);
		this.descuento = new Descuento(porcentajeDescuento);
	}

	private boolean horarioInvalido(int hora) {
		return hora < 0 || hora > 23;
	}

	private boolean rangoInvalido(int horaInicio, int horaFin) {
		return horaInicio >= horaFin;
	}

	// Que NO este antes de la hora de inicio. Que NO este luego de la hora de fin.
	// Que NO sea igual a la hora de fin -> [8 a 10) por ejemplo
	public boolean hayDescuentoEsaHora(LocalTime hora) {
		return !(hora.isBefore(horaDeInicio) || hora.isAfter(horaDeFin) || hora.equals(horaDeFin));
	}

	public boolean hayDescuentoEsaHora(LocalDateTime hora) {
		return hayDescuentoEsaHora(hora.toLocalTime());
	}

	public double devolverDescuento() {
		return this.descuento.devolverPorcentajeDeDescuento();
	}
}
