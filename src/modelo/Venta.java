package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {
	private LocalDateTime fechaYHora;
	private double litrosCargados;
	private double monto;

	public Venta(double litrosCargados, double monto, LocalDateTime fechaYHora) {
		if (litrosInvalidos(litrosCargados)) {
			throw new DatoInvalidoException("litros cargados");
		}
		if (montoInvalido(monto)) {
			throw new DatoInvalidoException("monto");
		}

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

	private boolean compararFechaYHora(LocalDateTime unaFecha, LocalDateTime otraFecha) {
		return fechaYHoraFormateada(unaFecha).equals(fechaYHoraFormateada(otraFecha));
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

	@Override
	public String toString() {
		return fechaYHoraFormateada(fechaYHora) + "," + litrosCargados + "," + monto;
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
		if (fechaYHora == null) {
			if (other.fechaYHora != null)
				return false;
		} else if (!compararFechaYHora(fechaYHora, other.fechaYHora)) {
			return false;
		}
		if (Double.doubleToLongBits(litrosCargados) != Double.doubleToLongBits(other.litrosCargados))
			return false;
		if (Double.doubleToLongBits(monto) != Double.doubleToLongBits(other.monto))
			return false;
		return true;
	}
}
