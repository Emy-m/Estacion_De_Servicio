package main;

import java.awt.EventQueue;

import modelo.CombustibleComun;
import modelo.CombustibleSuper;
import modelo.SurtidorDeCombustible;
import persistencia.EnBaseDeDatosIAlmacenamiento;
import ui.PantallaPrincipal;

public class MainEnBD {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CombustibleComun combustibleComun = new CombustibleComun();
					CombustibleSuper combustibleSuper = new CombustibleSuper();
					EnBaseDeDatosIAlmacenamiento almacenamiento = new EnBaseDeDatosIAlmacenamiento();
					SurtidorDeCombustible surtidor = new SurtidorDeCombustible(almacenamiento);
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
