package estadosYSentidos;

import java.util.LinkedList;
import java.util.List;

import trenes.Pasajero;
import trenes.TimerTren;
import trenes.Tren;
import vista.CambioEnEstacion;

public class EnEstacion extends EstadoTren {

	public EnEstacion(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		
		
				
		//Hacer Cambio enEstacion
		this.pantalla.agregarCambio(new CambioEnEstacion(this.tren));
		
		//nuevo
		this.tren.lockTrenViaje.lock();
		List<Pasajero> pasajerosQueSeBajaron = new LinkedList<Pasajero>();
		for (Pasajero pasajero : this.tren.pasajerosABordo) {
			if(pasajero.estacionDestino.equals(this.tren.getEstacionActual())){
				//pasajero.llegoADestino = true;
				this.tren.cantPasajerosAbordo--;
				pasajerosQueSeBajaron.add(pasajero);				
			}		
		}
		if(this.tren.estActual.esEstacionPortal()){
			for (Pasajero pasajero : this.tren.pasajerosABordo) {
				if(pasajero.teletransportar){
					this.tren.estActual.estacionConcreta.teletransportar(pasajero); //Chequear si revienta para castear.
					//TODO aca hay que portalear al pasajero					
					//la estacion extiende de la concreta
					//e implemeta la teletransportacion
				}		
			}			
		}
		
		this.tren.pasajerosABordo.removeAll(pasajerosQueSeBajaron);
		
		
		this.tren.lockTrenViaje.unlock();
		
		//nuevo
		//Duerme en la estacion hasta que se llene o se acabe el tiempo.
		this.tren.lockTrenEsperandoSalir.lock();
		TimerTren timer=new TimerTren(tren);
		timer.start();
		try {this.tren.trenEsperandoSalir.await();} catch (InterruptedException e) {e.printStackTrace();}
		timer.salioTren = true;
		this.tren.lockTrenEsperandoSalir.unlock();
		
		//se setea la siguiente estacion
		this.tren.siguienteEstacion();
		//en el siguiente estado se libera el anden y se guarda el cambio en la coleccion de la pantalla
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.enMovimiento;
	}

}
