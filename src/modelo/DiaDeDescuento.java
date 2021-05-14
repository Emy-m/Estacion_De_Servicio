package modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DiaDeDescuento {
	// La intencion de esta clase es que marque un dia para un descuento.
	// Si se quisiera hacer un dia y fecha se usaria otra clase que contenga el dia
	// y la fecha. O el patron de mamoshca o decorador?

	private DayOfWeek diaDelDescuento;
	private Descuento descuento;
	private LitrosDeCombustible litrosMinimosDescuento;

	public DiaDeDescuento(DayOfWeek diaDelDescuento, double porcentajeDeDescuento, double litrosMinimosDescuento) {
		this.diaDelDescuento = diaDelDescuento;
		this.descuento = new Descuento(porcentajeDeDescuento);
		this.litrosMinimosDescuento = new LitrosDeCombustible(litrosMinimosDescuento);
	}

	public DiaDeDescuento(String diaDelDescuento, double porcentajeDeDescuento, double litrosMinimosDescuento) {
		try {
			DayOfWeek.valueOf(diaDelDescuento);
		} catch (IllegalArgumentException e) {
			throw new DatoInvalidoException("dia del descuento", e);
		} catch (NullPointerException e) {
			throw new DatoInvalidoException("no existe el dia del desuento", e);
		}

		this.diaDelDescuento = DayOfWeek.valueOf(diaDelDescuento);
		this.descuento = new Descuento(porcentajeDeDescuento);
		this.litrosMinimosDescuento = new LitrosDeCombustible(litrosMinimosDescuento);
	}

	public boolean hayDescuentoEseDia(LocalDateTime dia) {
		return diaDelDescuento.equals(dia.getDayOfWeek());
	}

	public boolean hayDescuentoEnEseDiaYLitros(LocalDateTime dia, LitrosDeCombustible litros) {
		if (litros.devolverCantidadDeLitros() > litrosMinimosDescuento.devolverCantidadDeLitros()) {
			return hayDescuentoEseDia(dia);
		}

		return false;
	}

	public double devolverDescuento() {
		return this.descuento.devolverPorcentajeDeDescuento();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiaDeDescuento other = (DiaDeDescuento) obj;
		if (diaDelDescuento != other.diaDelDescuento)
			return false;
		return true;
	}
}
