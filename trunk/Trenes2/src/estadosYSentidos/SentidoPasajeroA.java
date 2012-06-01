package estadosYSentidos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import trenes.EstacionConcreta;
import trenes.Tren;

public class SentidoPasajeroA extends SentidoPasajero {
	@Override
	public Lock getLockAndenEstacion(EstacionConcreta estacion) {
		return estacion.lockAndenA;
	}

	@Override
	public boolean hayTrenEnAnden(EstacionConcreta estacionOrigen) {
		return estacionOrigen.cantAndenesOcupadosSentidoA>0;
	}

	@Override
	public void dormirEnCondicionDeEseAnden(EstacionConcreta estacionOrigen) {
		try {estacionOrigen.pasajerosEsperandoAndenA.await();} catch (InterruptedException e) {e.printStackTrace();}
	}

	@Override
	public Tren seleccionarTrenEnAnden(EstacionConcreta estacionOrigen) {
		return estacionOrigen.trenesAndenA.get(0);
	}

	@Override
	public Condition getConditionAndenPasajeros(EstacionConcreta estacionOrigen) {
		return estacionOrigen.pasajerosEsperandoAndenA;
	}

	@Override
	public void incrementarPasajerosAnden(EstacionConcreta estacionOrigen) {
		estacionOrigen.cantPasajerosEsperandoAndenA++;
	}
	
	
	
}
