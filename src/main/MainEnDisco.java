package main;

import java.awt.EventQueue;

import modelo.CombustibleComun;
import modelo.CombustibleSuper;
import modelo.SurtidorDeCombustible;
import persistencia.EnDiscoIAlmacenamiento;
import ui.PantallaPrincipal;

public class MainEnDisco {
	
	private static final String DIRECCION_ARCHIVO = "C:\\Users\\Emy_m\\Desktop\\Venta.txt";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CombustibleComun combustibleComun = new CombustibleComun();
					CombustibleSuper combustibleSuper = new CombustibleSuper();
					EnDiscoIAlmacenamiento almacenamiento;
					
					if(args.length > 0) {
						almacenamiento = new EnDiscoIAlmacenamiento(args[0]);
					}
					else {
						almacenamiento = new EnDiscoIAlmacenamiento(DIRECCION_ARCHIVO);
					}
					
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
