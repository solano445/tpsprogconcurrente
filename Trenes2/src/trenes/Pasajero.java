package trenes;

import estadosYSentidos.SentidoPasajero;

public class Pasajero extends Thread {

	//Variables
	public String nombre;
	public EstacionConcreta estacionDestino; //Esta es la estacion hacia la que se dirije
	public EstacionConcreta estacionOrigen; //Esta es la estacion donde sube
	public SentidoPasajero sentido;
	
	public Pasajero(String nombre ,EstacionConcreta estacionOrigen, EstacionConcreta estacionDestino) {
		this.nombre=nombre;
		this.estacionDestino=estacionDestino;
		this.estacionOrigen=estacionOrigen;
	}
	
	public void run() {		
		this.sentido = this.estacionOrigen.sentidoMasCortoHasta(estacionDestino);
		boolean llegueAdestino = false;
		while(!llegueAdestino){
			//seguimoos mañana
		//pide a la estacion el tren que llegara mas rapido a la estacion que quiere ir
		this.estacionOrigen.viajarHasta(estacionDestino); //Terminar
		//Los muchachos peronistas, todos unidos triunfaremos y...
		}
	}
}
