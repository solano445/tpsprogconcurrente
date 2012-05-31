package estadosYSentidos;

import java.util.concurrent.locks.Lock;

import trenes.EstacionConcreta;
import trenes.Tren;

public abstract class SentidoPasajero {

	public abstract Lock getLockAnden(EstacionConcreta estacion);

	public abstract boolean hayTrenesEnAnden(EstacionConcreta estacionOrigen);
	
	public abstract void dormirEnCondicionDeEseAnden(EstacionConcreta estacionOrigen);

	public abstract Tren seleccionarTrenEnAnden(EstacionConcreta estacionOrigen);
	
}
