package ui;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.SurtidorDeCombustible;

public class PantallaDeCargaDeCombustible extends JFrame {
	private JComboBox<String> combustible;
	private JTextField litros;
	private SurtidorDeCombustible surtidor;
	
	public PantallaDeCargaDeCombustible(SurtidorDeCombustible surtidor) {
		this.surtidor = surtidor;
		setupUIComponents();
	}
	
	private void setupUIComponents() {
		setTitle("Surtidor");
		setSize(400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.combustible = new JComboBox<String>();
		for(String nombreCombustible : surtidor.devolverNombresCombustibles()) {
			this.combustible.addItem(nombreCombustible);
		}
		this.litros = new JTextField(10);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JLabel("Combustible: "));
		contentPane.add(combustible);
		contentPane.add(new JLabel("Litros: "));
		contentPane.add(litros);
		
		JButton botonConsultar = new JButton("Consultar Precio");
		botonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonConsultar();
			}
		});
		contentPane.add(botonConsultar);
		
		JButton botonConfirmar = new JButton("Confirmar");
		botonConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonConfirmar();
			}
		});
		contentPane.add(botonConfirmar);
		setContentPane(contentPane);
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pack();
		setVisible(true);
	}

	private void accionBotonConfirmar() {
		try {
			surtidor.confirmarVenta((String) combustible.getSelectedItem(), litros.getText(), LocalDateTime.now());
			JOptionPane.showMessageDialog(this, "Exito al guardar");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
	}
	
	private void accionBotonConsultar() {
		try {
			JOptionPane.showMessageDialog(this, surtidor.consultarMontoAPagar((String) combustible.getSelectedItem(), litros.getText(), LocalDateTime.now()));
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
	}
}
