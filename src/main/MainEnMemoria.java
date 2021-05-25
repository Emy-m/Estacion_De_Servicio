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
import persistencia.EnMemoriaIFacturacionDeVentas;
import ui.PantallaPrincipal;

public class MainEnMemoria {
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
					EnMemoriaIFacturacionDeVentas almacenamiento = new EnMemoriaIFacturacionDeVentas();

					EMail servicioEmail = new EMail();

					SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
					surtidor.agregarObservador(servicioEmail);

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
