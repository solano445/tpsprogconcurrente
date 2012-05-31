package trenes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.List;

import vista.Pantalla;

public class Simulador {
	
	public static Boolean terminaSimulacion = false;
	public static JFrame frame;
	
	
	public static void main(String[] args) {
		createWindow();
		Pantalla pantalla = Pantalla.getInstance();
		Recorrido recorrido = Recorrido.getRecorrido();
		List<Tren> trenes = Tren.getTrenes(recorrido);
		new Pasajero("Pepe" , Recorrido.estaciones[1] ,Recorrido.estaciones[2]).start();
		//Recorrido.testearGetRecorrido(recorrido);
		for(Tren tren:trenes){
			tren.start();
		}
		pantalla.start();
		//agregar a las personas
		 	
	}
	
	private static void createWindow() {		 
		//Create and set up the window. 
		frame = new JFrame("Simulador");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		
		JButton terminarAplicacion = new JButton("Terminar");
		//JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER); 
		terminarAplicacion.setPreferredSize(new Dimension(200, 50));
		terminarAplicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				terminaSimulacion = true;
				Pantalla.getInstance().finalizarSimulacion();
			}
		});
		frame.getContentPane().add(terminarAplicacion, BorderLayout.CENTER); 
		
		//Display the window. 
		frame.setLocationRelativeTo(null); 
		frame.pack();
		frame.setVisible(true); 
	}

}
