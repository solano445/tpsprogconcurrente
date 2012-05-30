package trenes;

public class Pasajero extends Thread {

	//Variables
	public String nombre;
	public EstacionConcreta estacionDestino; //Esta es la estacion hacia la que se dirije
	public EstacionConcreta estacionOrigen; //Esta es la estacion donde sube
	
	public Pasajero(String nombre ,EstacionConcreta estacionOrigen, EstacionConcreta estacionDestino) {
		this.nombre=nombre;
		this.estacionDestino=estacionDestino;
		this.estacionOrigen=estacionOrigen;
	}
	
		public void run() {
			//pide a la estacion el tren que llegara mas rapido a la estacion que quiere ir
			//Tren tren=estacionDestino.viajarHasta(estacionDestino); //Terminar
			//Los muchachos peronistas, todos unidos triunfaremos y...
		}
}
