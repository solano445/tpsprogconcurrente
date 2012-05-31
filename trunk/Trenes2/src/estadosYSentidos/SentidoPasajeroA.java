package estadosYSentidos;

import java.util.concurrent.locks.Lock;

import trenes.EstacionConcreta;
import trenes.Tren;

public class SentidoPasajeroA extends SentidoPasajero {
	@Override
	public Lock getLockAnden(EstacionConcreta estacion) {
		return estacion.lockAndenA;
	}

	@Override
	public boolean hayTrenesEnAnden(EstacionConcreta estacionOrigen) {
		return estacionOrigen.cantAndenesOcupadosSentidoA>0;
	}

	@Override
	public void dormirEnCondicionDeEseAnden(EstacionConcreta estacionOrigen) {
		try {estacionOrigen.andenPasajerosA.await();} catch (InterruptedException e) {e.printStackTrace();}
	}

	@Override
	public Tren seleccionarTrenEnAnden(EstacionConcreta estacionOrigen) {
		return estacionOrigen.trenesAndenA.get(0);
	}
	
	
	
}
