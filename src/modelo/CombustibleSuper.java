package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class CombustibleSuper extends Combustible {

	private ArrayList<DiaDeDescuento> diasDeDescuentos = new ArrayList<DiaDeDescuento>();

	public CombustibleSuper(double precioPorLitroSuper, ArrayList<DiaDeDescuento> diasDeDescuentos) {
		super("Super", precioPorLitroSuper);

		if (diasDeDescuentosInvalidos(diasDeDescuentos)) {
			throw new DatoInvalidoException("Mas de un descuento por dia");
		}

		this.diasDeDescuentos = diasDeDescuentos;
	}

	@Override
	public double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy) {
		for (DiaDeDescuento diaDescuento : diasDeDescuentos) {
			if (diaDescuento.hayDescuentoEseDia(fechaDeHoy)) {
				return diaDescuento.devolverDescuento();
			}
		}
		return 0;
	}

	@Override
	public double devolverDescuentoDeHoy(LocalDateTime fechaDeHoy, double litrosCargados) {
		for (DiaDeDescuento diaDescuento : diasDeDescuentos) {
			if (diaDescuento.hayDescuentoEnEseDiaYLitros(fechaDeHoy, new LitrosDeCombustible(litrosCargados))) {
				return diaDescuento.devolverDescuento();
			}
		}
		return 0;
	}

	@Override
	public double calcularMonto(LocalDateTime fechaDeHoy, double litrosCargados) {
		return litrosCargados * precioPorLitro.devolverPrecioPorLitro()
				* (1 - devolverDescuentoDeHoy(fechaDeHoy, litrosCargados));
	}

	private boolean diasDeDescuentosInvalidos(ArrayList<DiaDeDescuento> diasDeDescuentos) {
		// Si paso la lista a un HashSet elimina estos dias repetidos
		HashSet<DiaDeDescuento> diasDescuentosUnicos = new HashSet<DiaDeDescuento>(diasDeDescuentos);
		return diasDeDescuentos.size() != diasDescuentosUnicos.size();
	}
}
