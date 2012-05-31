package trenes;

import estadosYSentidos.SentidoPasajero;

public class Pasajero extends Thread {

	//Variables
	public String nombre;
	public EstacionConcreta estacionDestino; //Esta es la estacion hacia la que se dirije
	public EstacionConcreta estacionOrigen; //Esta es la estacion donde sube
	public SentidoPasajero sentido;
	public boolean llegueAdestino;
	
	public Pasajero(String nombre ,EstacionConcreta estacionOrigen, EstacionConcreta estacionDestino) {
		this.nombre=nombre;
		this.estacionDestino=estacionDestino;
		this.estacionOrigen=estacionOrigen;
		this.llegueAdestino = false;
	}
	
	public void run() {		
		this.sentido = this.estacionOrigen.sentidoMasCortoHasta(estacionDestino);
		while(!llegueAdestino){
			this.sentido.getLockAnden(this.estacionOrigen).lock();
			while(!this.sentido.hayTrenesEnAnden(this.estacionOrigen)){
				this.sentido.dormirEnCondicionDeEseAnden(this.estacionOrigen);
				
				Tren tren= this.sentido.seleccionarTrenEnAnden(this.estacionOrigen);
				
				tren.lockTrenViaje.lock();
				if(tren.cantPasajerosAbordo<tren.cantPasajerosMax){
					tren.cantPasajerosAbordo++;
					tren.pasajerosABordo.add(this);
					try {tren.pasajerosViajando.await();} catch (InterruptedException e) {e.printStackTrace();}
				}
				tren.lockTrenViaje.unlock();
			}
			this.sentido.getLockAnden(this.estacionOrigen).unlock();
				
			
				
		
		
		//pide a la estacion el tren que llegara mas rapido a la estacion que quiere ir
		//Los muchachos peronistas, todos unidos triunfaremos y...
		}
	}
}












