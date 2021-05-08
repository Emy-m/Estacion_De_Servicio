package ui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.SurtidorDeCombustible;

public class PantallaDeConsultasDeVentas extends JFrame {
	private SurtidorDeCombustible surtidor;
	private DefaultTableModel modeloTabla;
	private JTable tablaVentas;
	private JTextField fechaInicio;
	private JTextField fechaFin;
	
	public PantallaDeConsultasDeVentas(SurtidorDeCombustible surtidor) {
		this.surtidor = surtidor;
		setupUIComponents();
	}
	
	private void setupUIComponents() {
		setTitle("Consultas");
		setSize(400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		
		JPanel panelArriba = new JPanel(new FlowLayout());
		JPanel panelAbajo = new JPanel(new FlowLayout());
		
		JLabel labelIngresoFechas = new JLabel("Ingrese rango de fechas inicio/fin (8/05/2021):");
		fechaInicio = new JTextField(10);
		fechaFin = new JTextField(10);
		
		JButton botonBuscarVentasEnRango = new JButton("Buscar");
		botonBuscarVentasEnRango.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarModeloEnRango(fechaInicio.getText(), fechaFin.getText());
			}
		});
		
		modeloTabla = new DefaultTableModel(new String[] {"Fecha y Hora", "Litros", "Monto"}, 0);
		tablaVentas = new JTable(modeloTabla);
		
		JScrollPane scrollPanel = new JScrollPane(tablaVentas);
		
		panelArriba.add(labelIngresoFechas);
		panelArriba.add(fechaInicio);
		panelArriba.add(fechaFin);
		panelArriba.add(botonBuscarVentasEnRango);
		panelAbajo.add(scrollPanel);
		contentPane.add(panelArriba, BorderLayout.NORTH);
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		setContentPane(contentPane);
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pack();
		
		actualizarModelo();
		
		setVisible(true);
	}

	private void actualizarModelo() {
		try {
			for(String venta : surtidor.obtenerVentas()) {
				String fechaVenta = venta.split(",")[0];
				String litrosVenta = venta.split(",")[1];
				String montoVenta = venta.split(",")[2];
				modeloTabla.addRow(new Object[] {fechaVenta, litrosVenta, montoVenta});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void actualizarModeloEnRango(String fechaInicial, String fechaFin) {
		try {
			modeloTabla.setRowCount(0);
			
			for(String venta : surtidor.obtenerVentasEnRango(fechaInicial, fechaFin)) {
				String fechaVenta = venta.split(",")[0];
				String litrosVenta = venta.split(",")[1];
				String montoVenta = venta.split(",")[2];
				modeloTabla.addRow(new Object[] {fechaVenta, litrosVenta, montoVenta});
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}
}