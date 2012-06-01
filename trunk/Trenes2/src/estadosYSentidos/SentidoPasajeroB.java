package estadosYSentidos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import trenes.EstacionConcreta;
import trenes.Tren;

public class SentidoPasajeroB extends SentidoPasajero {

	@Override
	public Lock getLockAndenEstacion(EstacionConcreta estacion) {
		return estacion.lockAndenB;
	}
	
	@Override
	public boolean hayTrenEnAnden(EstacionConcreta estacionOrigen) {
		return estacionOrigen.cantAndenesOcupadosSentidoB>0;
	}
	
	@Override
	public void dormirEnCondicionDeEseAnden(EstacionConcreta estacionOrigen) {
		try {estacionOrigen.pasajerosEsperandoAndenB.await();} catch (InterruptedException e) {e.printStackTrace();}
	}

	@Override
	public Tren seleccionarTrenEnAnden(EstacionConcreta estacionOrigen) {
		return estacionOrigen.trenesAndenB.get(0);
	}
	
	@Override
	public Condition getConditionAndenPasajeros(EstacionConcreta estacionOrigen) {
		return estacionOrigen.pasajerosEsperandoAndenB;
	}
	
	@Override
	public void incrementarPasajerosAnden(EstacionConcreta estacionOrigen) {
		estacionOrigen.cantPasajerosEsperandoAndenB++;
	}
	
}
