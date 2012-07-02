package estadosYSentidos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import trenes.EstacionConcreta;
import trenes.Tren;

public abstract class SentidoPasajero {

	public abstract Lock getLockAndenEstacion(EstacionConcreta estacion);

	public abstract boolean hayTrenEnAnden(EstacionConcreta estacionOrigen);
	
	public abstract void dormirEnCondicionDeEseAnden(EstacionConcreta estacionOrigen);

	public abstract Tren seleccionarTrenEnAnden(EstacionConcreta estacionOrigen);

	public abstract Condition getConditionAndenPasajeros(EstacionConcreta estacionOrigen);

	public abstract void incrementarPasajerosAnden(EstacionConcreta estacionOrigen);

	public abstract void decrementarPasajerosAnden(EstacionConcreta estacionOrigen);
	
}
