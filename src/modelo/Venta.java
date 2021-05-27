package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {
	private LocalDateTime fechaYHora;
	private double litrosCargados;
	private double monto;
	private DireccionEmail direccionCorreoComprador;

	public Venta(double litrosCargados, double monto, LocalDateTime fechaYHora, String direccionCorreoComprador) {
		if (litrosInvalidos(litrosCargados)) {
			throw new DatoInvalidoException("litros cargados");
		}
		if (montoInvalido(monto)) {
			throw new DatoInvalidoException("monto");
		}

		this.direccionCorreoComprador = new DireccionEmail(direccionCorreoComprador);
		this.fechaYHora = fechaYHora;
		this.litrosCargados = litrosCargados;
		this.monto = monto;
	}

	private boolean montoInvalido(double monto) {
		return monto <= 0;
	}

	private boolean litrosInvalidos(double litrosCargados) {
		return litrosCargados <= 0;
	}

	private String fechaYHoraFormateada(LocalDateTime unaFecha) {
		return unaFecha.format(DateTimeFormatter.ofPattern("d/MM/yyyy '-' H:m:s"));
	}

	public LocalDateTime devolverFechaYHora() {
		return this.fechaYHora;
	}

	public double devolverLitrosCargados() {
		return this.litrosCargados;
	}

	public double devolverMonto() {
		return this.monto;
	}

	public String devolverEmailComprador() {
		return this.direccionCorreoComprador.obtenerEmail();
	}

	@Override
	public String toString() {
		return fechaYHoraFormateada(fechaYHora) + "," + litrosCargados + "," + monto + ","
				+ direccionCorreoComprador.obtenerEmail();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		if (direccionCorreoComprador == null) {
			if (other.direccionCorreoComprador != null)
				return false;
		} else if (!direccionCorreoComprador.equals(other.direccionCorreoComprador))
			return false;
		if (fechaYHora == null) {
			if (other.fechaYHora != null)
				return false;
		} else if (!fechaYHora.equals(other.fechaYHora))
			return false;
		return true;
	}

}
