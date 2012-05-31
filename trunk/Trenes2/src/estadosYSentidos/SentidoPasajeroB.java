package estadosYSentidos;

import java.util.concurrent.locks.Lock;

import trenes.EstacionConcreta;
import trenes.Tren;

public class SentidoPasajeroB extends SentidoPasajero {

	@Override
	public Lock getLockAnden(EstacionConcreta estacion) {
		return estacion.lockAndenB;
	}
	
	@Override
	public boolean hayTrenesEnAnden(EstacionConcreta estacionOrigen) {
		return estacionOrigen.cantAndenesOcupadosSentidoB>0;
	}
	
	@Override
	public void dormirEnCondicionDeEseAnden(EstacionConcreta estacionOrigen) {
		try {estacionOrigen.andenPasajerosB.await();} catch (InterruptedException e) {e.printStackTrace();}
	}

	@Override
	public Tren seleccionarTrenEnAnden(EstacionConcreta estacionOrigen) {
		return estacionOrigen.trenesAndenB.get(0);
	}
	
}
