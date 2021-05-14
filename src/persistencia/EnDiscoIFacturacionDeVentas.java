package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.DatoInvalidoException;
import modelo.IFacturacionDeVentas;
import modelo.Venta;

public class EnDiscoIFacturacionDeVentas implements IFacturacionDeVentas {

	private String tuDireccion;

	public EnDiscoIFacturacionDeVentas(String tuDireccion) {
		this.tuDireccion = tuDireccion;
	}

	@Override
	public void guardarVenta(Venta venta) {
		try {
			FileWriter archivo = new FileWriter(tuDireccion, true);
			archivo.write(venta.toString() + "\n");
			archivo.close();
		} catch (IOException e) {
			throw new PersistenciaException("Error de entrada/salida", e);
		}
	}

	@Override
	public boolean ventaGuardada(Venta venta) {
		try {
			return Files.lines(Paths.get(tuDireccion)).anyMatch(linea -> linea.contains(venta.toString()));
		} catch (IOException e) {
			throw new PersistenciaException("Error de entrada/salida", e);
		}
	}

	@Override
	public ArrayList<String> obtenerVentas() {
		BufferedReader bufReader;
		try {
			bufReader = new BufferedReader(new FileReader(tuDireccion));
			ArrayList<String> listOfLines = new ArrayList<String>();

			String line = bufReader.readLine();
			while (line != null) {
				listOfLines.add(line);
				line = bufReader.readLine();
			}

			bufReader.close();
			return listOfLines;
		} catch (IOException e) {
			throw new PersistenciaException("Error de Entrada/Salida", e);
		}
	}

	@Override
	public ArrayList<String> obtenerVentasEnRango(LocalDate fechaInicio, LocalDate fechaFin) {
		Scanner scaner;
		try {
			scaner = new Scanner(new File(tuDireccion));
			ArrayList<String> ventas = new ArrayList<String>();
			while (scaner.hasNext()) {
				LocalDate fecha = formatoValido(scaner.next().split(",")[0]);
				if (fechaEnRango(fecha, fechaInicio, fechaFin)) {
					ventas.add(scaner.next());
				}
			}
			scaner.close();
			return ventas;
		} catch (FileNotFoundException e) {
			throw new PersistenciaException("Error de entrada/salida", e);
		}
	}

	private LocalDate formatoValido(String fecha) {
		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
			return LocalDate.parse(fecha, formato);
		} catch (IllegalArgumentException | DateTimeParseException e) {
			throw new DatoInvalidoException("fechas", e);
		}
	}

	private boolean fechaEnRango(LocalDate fecha, LocalDate fechaInicial, LocalDate fechaFin) {
		return (fechaInicial.isBefore(fecha) || fechaInicial.isEqual(fecha))
				&& (fechaFin.isEqual(fecha) || fechaFin.isAfter(fecha));
	}
}
