package trenes;

import java.util.concurrent.locks.Condition;

import estadosYSentidos.SentidoPasajero;

public class Pasajero extends Thread {

	//Variables
	public String nombre;
	public EstacionConcreta estacionDestino; //Esta es la estacion hacia la que se dirije
	public EstacionConcreta estacionOrigen; //Esta es la estacion donde sube
	public EstacionConcreta estacionDestinoTemporal;
	public SentidoPasajero sentido;
	public boolean llegoADestino;
	public boolean abordo;
	public boolean teletransportar;
	
	public Pasajero(String nombre ,EstacionConcreta estacionOrigen, EstacionConcreta estacionDestino) {
		this.nombre=nombre;
		this.estacionDestino=estacionDestino;
		this.estacionOrigen=estacionOrigen;
		this.llegoADestino = false;
		this.abordo = false;
		this.teletransportar = false;
	}
	
	public void run() {	
		//TODO
		/**Primero se fija si la estacion que busca esta en el recorrido**/
		if(!this.estacionOrigen.estaEnRecorrido(estacionDestino)){
			/**Debe dirijirse a la estacion [[portal]]**/
			this.teletransportar = true;
			this.estacionDestinoTemporal = this.estacionDestino;
			this.estacionDestino = this.estacionOrigen.getEstacioPortal();
			System.out.println("el usuario " + nombre + " debe ser transportado a " + this.estacionDestinoTemporal.nombre + " a travez de la estacion " + this.estacionDestino.nombre );
			//throw new RuntimeException("Pasajero sin destino");
		}
		
		/**si existe continua la ejecucion normal**/		
		this.sentido = this.estacionOrigen.sentidoMasCortoHasta(estacionDestino);
		
		this.sentido.getLockAndenEstacion(this.estacionOrigen).lock();
		this.sentido.incrementarPasajerosAnden(this.estacionOrigen);
		this.sentido.getLockAndenEstacion(this.estacionOrigen).unlock();
		
		while(!abordo){
			this.sentido.getLockAndenEstacion(this.estacionOrigen).lock();
			//usa el lock que utilizan los trenes
			//para entrar o salir a la estacion
			
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
					if(tren.cantPasajerosAbordo==tren.cantPasajerosMax){//si soy el ultimo lo aviso asi sale ;) TODO
						tren.lockTrenEsperandoSalir.lock();
						tren.trenEsperandoSalir.signal();
						tren.lockTrenEsperandoSalir.unlock();
					}
						
					this.sentido.decrementarPasajerosAnden(this.estacionOrigen);
				}
				
				//libera el lock del tren tras modificar su estado
				tren.lockTrenViaje.unlock();
				
			}
			else{
				Condition esperarEnAnden = this.sentido.getConditionAndenPasajeros(this.estacionOrigen);
				try {esperarEnAnden.await();} catch (InterruptedException e) {e.printStackTrace();}
				
			}
			//libera el permiso para que el tren pueda circular
			
			//si no hay trenes duerme en el anden			
			this.sentido.getLockAndenEstacion(this.estacionOrigen).unlock();
		}
		
	}
}












