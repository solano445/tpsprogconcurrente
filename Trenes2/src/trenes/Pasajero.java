package trenes;

import java.util.concurrent.locks.Condition;

import estadosYSentidos.SentidoPasajero;

public class Pasajero extends Thread {

	//Variables
	public String nombre;
	public EstacionConcreta estacionDestino; //Esta es la estacion hacia la que se dirije
	public EstacionConcreta estacionOrigen; //Esta es la estacion donde sube
	public SentidoPasajero sentido;
	public boolean llegoADestino;
	public boolean abordo;
	
	public Pasajero(String nombre ,EstacionConcreta estacionOrigen, EstacionConcreta estacionDestino) {
		this.nombre=nombre;
		this.estacionDestino=estacionDestino;
		this.estacionOrigen=estacionOrigen;
		this.llegoADestino = false;
		this.abordo = false;
		System.out.println("Pasajero " + nombre + " de " + estacionOrigen.nombre + " a " + estacionDestino.nombre);
	}
	
	public void run() {		
		this.sentido = this.estacionOrigen.sentidoMasCortoHasta(estacionDestino);
		while(!abordo){
			//usa el lock que utilizan los trenes
			//para entrar o salir a la estacion
			this.sentido.getLockAndenEstacion(this.estacionOrigen).lock();
			
			if(this.sentido.hayTrenEnAnden(this.estacionOrigen)){
				//hay un tren en la estacion y tiene el lock
				
				Tren tren= this.sentido.seleccionarTrenEnAnden(this.estacionOrigen);
				
				//toma el lock del tren para modificar su estado
				tren.lockTrenViaje.lock();
				if(tren.cantPasajerosAbordo<tren.cantPasajerosMax){
					tren.cantPasajerosAbordo++;
					tren.pasajerosABordo.add(this);
					//try {tren.pasajerosViajando.await();} catch (InterruptedException e) {e.printStackTrace();}
					this.abordo = true;
				}
				//libera el lock del tren tras modificar su estado
				tren.lockTrenViaje.unlock();
			}
			else{
				Condition esperarEnAnden = this.sentido.getConditionAndenPasajeros(this.estacionOrigen);
				this.sentido.incrementarPasajerosAnden(this.estacionOrigen);
				try {esperarEnAnden.await();} catch (InterruptedException e) {e.printStackTrace();}
			}
			//libera el permiso para que el tren pueda circular
			this.sentido.getLockAndenEstacion(this.estacionOrigen).unlock();
			//si no hay trenes duerme en el anden			
		}
		
	}
}












