package ui;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ISurtidor;

public class PantallaPrincipal extends JFrame {

	private ISurtidor surtidor;

	public PantallaPrincipal(ISurtidor surtidor) {
		this.surtidor = surtidor;
		setupUIComponents();
	}

	private void setupUIComponents() {
		setTitle("Estaciones de servicio YPZW");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());

		JButton botonDePantallaDeCargaDeCombustible = new JButton("Carga de Combustible");
		botonDePantallaDeCargaDeCombustible.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PantallaDeCargaDeCombustible(surtidor);
			}
		});

		JButton botonDePantallaDeConsultaDeVentas = new JButton("Consulta de Ventas");
		botonDePantallaDeConsultaDeVentas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PantallaDeConsultasDeVentas(surtidor);
			}
		});

		contentPane.add(botonDePantallaDeCargaDeCombustible);
		contentPane.add(botonDePantallaDeConsultaDeVentas);
		setContentPane(contentPane);
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pack();
		setVisible(true);
	}
}