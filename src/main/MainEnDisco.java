package main;

import java.awt.EventQueue;
import java.time.DayOfWeek;
import java.util.ArrayList;

import email.EMail;
import modelo.CombustibleComun;
import modelo.CombustibleSuper;
import modelo.DiaDeDescuento;
import modelo.HorarioDeDescuento;
import modelo.SurtidorDeCombustible;
import persistencia.EnDiscoIFacturacionDeVentas;
import ui.PantallaPrincipal;

public class MainEnDisco {

	private static final String DIRECCION_ARCHIVO = "C:\\Users\\Emy_m\\Desktop\\Venta.txt";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HorarioDeDescuento descuentoParaCombustibleComun = new HorarioDeDescuento(8, 10, 0.05);
					DiaDeDescuento descuentoSabadoSuper = new DiaDeDescuento(DayOfWeek.SATURDAY, 0.12, 20);
					DiaDeDescuento descuentoDomingoSuper = new DiaDeDescuento(DayOfWeek.SUNDAY, 0.1, 0);
					ArrayList<DiaDeDescuento> descuentoParaCombustibleSuper = new ArrayList<DiaDeDescuento>();
					descuentoParaCombustibleSuper.add(descuentoSabadoSuper);
					descuentoParaCombustibleSuper.add(descuentoDomingoSuper);

					CombustibleComun combustibleComun = new CombustibleComun(70, descuentoParaCombustibleComun);
					CombustibleSuper combustibleSuper = new CombustibleSuper(90, descuentoParaCombustibleSuper);
					EnDiscoIFacturacionDeVentas almacenamiento;

					if (args.length > 0) {
						almacenamiento = new EnDiscoIFacturacionDeVentas(args[0]);
					} else {
						almacenamiento = new EnDiscoIFacturacionDeVentas(DIRECCION_ARCHIVO);
					}

					EMail servicioEmail = new EMail();

					SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento, servicioEmail);
					surtidor.agregarCombustible(combustibleComun);
					surtidor.agregarCombustible(combustibleSuper);

					new PantallaPrincipal(surtidor);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
	}

}
