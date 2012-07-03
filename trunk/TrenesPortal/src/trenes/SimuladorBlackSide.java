package trenes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import portal.Portal;

import java.util.List;

import vista.Pantalla;

public class SimuladorBlackSide {
	
	public static Boolean terminaSimulacion = false;
	public static JFrame frame;
	
	
	public static void main(String[] args) {
		createWindow();
		Pantalla pantalla = Pantalla.getInstance();
		Recorrido recorrido = Recorrido.getRecorridoB();
		Recorrido.getRecorridoA();
		List<Tren> trenes = Tren.getTrenes(recorrido);
		//agregar a las personas
		
		//aca se crea el portal ya qeu a partir de aqui se pueden agregar personas
		Portal.setInstance(new Portal(recorrido, 1235, 1234));
		Portal.getInstance().start();
		
		
		//Viajan De Ezpeleta A Berazategui		
		for (int i = 0; i < 200; i++) {
			new Pasajero("DarthVader" , Recorrido.estacionesB[0] ,Recorrido.estacionesB[3]).start();
			
		}
		//Viajan De Berazategui A Ezpeleta
		for (int i = 0; i < 200; i++) {
			new Pasajero("BlackDomingo" , Recorrido.estacionesB[3] ,Recorrido.estacionesB[0]).start();
			
		}
		
		/**TODO Este pasajero esta en cualquiera**/
		new Pasajero("JackBlack" , Recorrido.estacionesB[0] , Recorrido.estacionesA[0]).start();
		
		for(Tren tren:trenes){
			tren.start();
		}
		pantalla.start();
		 	
	}
	
	private static void createWindow() {		 
		frame = new JFrame("Simulador");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		
		JButton terminarAplicacion = new JButton("Terminar");
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
